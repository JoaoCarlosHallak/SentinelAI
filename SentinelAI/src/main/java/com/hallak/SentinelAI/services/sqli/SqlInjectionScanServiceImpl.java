package com.hallak.SentinelAI.services.sqli;

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
public class SqlInjectionScanServiceImpl implements SqlInjectionScanService {

    private final HttpClientService httpClientService;
    private final LoadPayloadsService loadPayloadsService;
    private final ClientOllamaService clientOllamaService;

    @Autowired
    public SqlInjectionScanServiceImpl(HttpClientService httpClientService, LoadPayloadsService loadPayloadsService, ClientOllamaService clientOllamaService) {
        this.httpClientService = httpClientService;
        this.loadPayloadsService = loadPayloadsService;
        this.clientOllamaService = clientOllamaService;
    }


    @Override
    public Flux<HttpResponseDataDTO> scanAndBasicFilter(String target) throws Exception { // Retorna lista de variso objetos no futuro
        if (!ScanHelperUtils.targetValidation(target)) {
            throw new IllegalArgumentException("Invalid target format. Target must contain '=' and '?'.");
        }

        List<String> payloads = ScanHelperUtils.mixPayloadAndTarget(loadPayloadsService.loadPayloads("payloads/sqli.txt"), target);
        List<String> errorWordList = loadPayloadsService.loadPayloads("payloads/sqliError.txt");





        return Flux.fromIterable(payloads) // Pega lista e transforma em flixo reativo
            .flatMap(httpClientService::sendRequest, 10) // Dispara requests em paralelo, max 10 ao mesmo tempo
            .filter(res -> res.statusCode() < 500) //remove erros de servidor
            .filter(res -> res.body() != null && !res.body().isEmpty()) // remove respostas vazias
            .filter(res -> {
                String body = res.body().toLowerCase();
                return errorWordList.stream().anyMatch(err -> body.contains(err.toLowerCase()));
            }) //Se o corpo contem alguma palavra do lista de erros
            .map(res -> {
                int index = res.url().indexOf("=");
                String payloadExtracted = res.url().substring(index + 1);

                return new HttpResponseDataDTO(
                    res.url(),
                     res.body(),
                      res.statusCode(), 
                    res.responseTime(),
                     res.contentLength(),
                      payloadExtracted);
            })

            .doOnNext(res ->
                System.out.println("SQL Injection possible: " + res.url() +
                                " Status: " + res.statusCode())
            ); // Apenas para printar no console, deposi vou tirar



    }

    @Override
    public Mono<String> handleSqlInjectionScanner(String target) throws Exception { // Retorna string no futuro
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
                        SystemPrompts.buildSQLIAnalysisPrompt(results.get(0))
                    )
                ).subscribeOn(Schedulers.boundedElastic()); // Roda a IA fora da thread principal
            });
    }

    }

