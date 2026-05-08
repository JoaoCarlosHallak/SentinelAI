package com.hallak.SentinelAI.services.idor;

import java.util.List;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;
import com.hallak.SentinelAI.services.HttpClientService;
import com.hallak.SentinelAI.services.dt.DirectoryTransversalScanService;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.hallak.SentinelAI.prompts.SystemPrompts;
import com.hallak.SentinelAI.services.ClientOllamaService;
import com.hallak.SentinelAI.services.LoadPayloadsService;
import com.hallak.SentinelAI.utils.ScanHelperUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;



@Service
public class IdorScanServiceImpl implements IdorScanService {

    private final HttpClientService httpClientService;
    private final LoadPayloadsService loadPayloadsService;
    private final ClientOllamaService clientOllamaService;

    @Autowired
    public IdorScanServiceImpl(HttpClientService httpClientService, LoadPayloadsService loadPayloadsService, ClientOllamaService clientOllamaService) {
        this.httpClientService = httpClientService;
        this.loadPayloadsService = loadPayloadsService;
        this.clientOllamaService = clientOllamaService;
    }


    @Override
    public Flux<HttpResponseDataDTO> scanAndBasicFilter(String target) throws Exception { // Retorna lista de variso objetos no futuro
        if (!ScanHelperUtils.targetValidation(target)) {
            throw new IllegalArgumentException("Invalid target format. Target must contain '=' and '?'.");
        }

        List<String> payloads = ScanHelperUtils.mixPayloadAndTarget(loadPayloadsService.loadPayloads("payloads/idor.txt"), target);
       


         HttpResponseDataDTO originalResponse = httpClientService.sendRequest(target).block();

         if (originalResponse == null) {
            throw new RuntimeException("Failed to get original response from target.");
         }

         String originalBody = originalResponse.body();

        return Flux.fromIterable(payloads) // Pega lista e transforma em flixo reativo
            .flatMap(httpClientService::sendRequest, 10) // Dispara requests em paralelo, max 10 ao mesmo tempo
            .filter(res -> res.statusCode() < 500) //remove erros de servidor
            .filter(res -> res.body() != null && !res.body().isEmpty()) // remove respostas vazias
            .filter(res -> {
                return !res.body().equals(originalBody); // remove respostas iguais a original
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
                System.out.println("Idor will be verified by IA: " + res.url() +
                                " Status: " + res.statusCode())
            ); // Apenas para printar no console, deposi vou tirar



    }

    @Override
    public Mono<String> handleIdorScanner(String target) throws Exception { // Retorna string no futuro
        return scanAndBasicFilter(target)
        .take(3)
            .collectList() // Pega todos os resultado e junta numa lista
            .flatMap(results -> { // Quando a lista chegar exercute isso, mas nao trave a thread
                if (results.isEmpty()) {
                    return Mono.just("Nenhuma vulnerabilidade detectada.");
                }

                // Apenas o Ollama vai para thread separada
                return Mono.fromCallable(() ->
                    clientOllamaService.sendRequest(
                        SystemPrompts.buildIdorAnalysisPrompt(results.get(0), results.get(1))));
                    }
                ).subscribeOn(Schedulers.boundedElastic()); // Roda a IA fora da thread principal
        };
}
