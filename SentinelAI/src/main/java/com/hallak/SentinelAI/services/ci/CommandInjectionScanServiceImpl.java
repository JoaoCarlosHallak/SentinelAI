package com.hallak.SentinelAI.services.ci;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;
import com.hallak.SentinelAI.prompts.SystemPrompts;
import com.hallak.SentinelAI.services.ClientOllamaService;
import com.hallak.SentinelAI.services.HttpClientService;
import com.hallak.SentinelAI.services.LoadPayloadsService;
import com.hallak.SentinelAI.utils.ScanHelperUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class CommandInjectionScanServiceImpl implements CommandInjectionScanService {

    private final HttpClientService httpClientService;
    private final LoadPayloadsService loadPayloadsService;
    private final ClientOllamaService clientOllamaService;

    @Autowired
    public CommandInjectionScanServiceImpl(HttpClientService httpClientService, LoadPayloadsService loadPayloadsService, ClientOllamaService clientOllamaService) {
        this.httpClientService = httpClientService;
        this.loadPayloadsService = loadPayloadsService;
        this.clientOllamaService = clientOllamaService;
    }


    @Override
    public Flux<HttpResponseDataDTO> scanAndBasicFilter(String target) throws Exception { 
        if (!ScanHelperUtils.targetValidation(target)) {
            throw new IllegalArgumentException("Invalid target format. Target must contain '=' and '?'.");
        }

        List<String> payloads = ScanHelperUtils.mixPayloadAndTarget(loadPayloadsService.loadPayloads("payloads/ci.txt"), target);
        List<String> ciContents = loadPayloadsService.loadPayloads("payloads/ciContent.txt");



        


        return Flux.fromIterable(payloads) 
            .flatMap(httpClientService::sendRequest, 10) 
            .filter(res -> res.statusCode() < 500) 
            .filter(res -> res.body() != null && !res.body().isEmpty()) 
            .filter(res -> {
                String body = res.body().toLowerCase();
                return ciContents.stream().anyMatch(err -> body.contains(err.toLowerCase()));
            }) 
            .map(res -> {
                return new HttpResponseDataDTO(
                    res.url(),
                    res.header(),
                     res.body(),
                    res.statusCode(), 
                    res.responseTime(),
                     res.contentLength(),
                      res.payload());
            })

            .doOnNext(res ->
                System.out.println("Command Injection possible: " + res.url() +
                                " Status: " + res.statusCode())
            ); 



    }

    @Override
    public Mono<String> handleCommandInjectionScanner(String target) throws Exception { // Retorna string no futuro
        return scanAndBasicFilter(target)
        .take(3)
        .collectList()
         // Pega todos os resultado e junta numa lista
            .flatMap(results -> { // Quando a lista chegar exercute isso, mas nao trave a thread
                if (results.isEmpty()) {
                    return Mono.just("Nenhuma vulnerabilidade detectada.");
                }

                // Apenas o Ollama vai para thread separada
                return Mono.fromCallable(() ->
                    clientOllamaService.sendRequest(
                        SystemPrompts.buildCommandInjectionAnalysisPrompt(results.get(0))
                    )
                ).subscribeOn(Schedulers.boundedElastic()); // Roda a IA fora da thread principal
            });
    }

    }


