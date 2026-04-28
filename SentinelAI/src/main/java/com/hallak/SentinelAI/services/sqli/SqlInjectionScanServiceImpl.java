package com.hallak.SentinelAI.services.sqli;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;
import com.hallak.SentinelAI.prompts.SystemPrompts;
import com.hallak.SentinelAI.services.ClientOllamaService;
import com.hallak.SentinelAI.services.HttpClientService;
import com.hallak.SentinelAI.services.LoadPayloadsService;
import com.hallak.SentinelAI.utils.ScanHelperUtils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public Flux<HttpResponseDataDTO> scanAndBasicFilter(String target) throws Exception {
        if (!ScanHelperUtils.targetValidation(target)) {
            throw new IllegalArgumentException("Invalid target format. Target must contain '=' and '?'.");
        }

        List<String> payloads = ScanHelperUtils.mixPayloadAndTarget(loadPayloadsService.loadPayloads("payloads/sqli.txt"), target);
        List<String> errorWordList = loadPayloadsService.loadPayloads("payloads/sqliError.txt");





        return Flux.fromIterable(payloads)
            .flatMap(httpClientService::sendRequest, 10)
            .filter(res -> res.statusCode() < 500)
            .filter(res -> res.body() != null && !res.body().isEmpty())
            .filter(res -> {
                String body = res.body();
                return errorWordList.stream().anyMatch(body::contains);

})
            .map(res -> {
                int index = res.url().indexOf("=");
                String payloadExtracted = res.url().substring(index + 1);
                return new HttpResponseDataDTO(res.url(), res.body(), res.statusCode(), 
                    res.responseTime(), res.contentLength(), payloadExtracted);
            })

            .doOnNext(res ->
                System.out.println("SQL Injection possible: " + res.url() +
                                " Status: " + res.statusCode())
            );



    }

    @Override
    public Mono<String> handleSqlInjectionScanner(String target) throws Exception {
        return scanAndBasicFilter(target)
            .collectList()
            .flatMap(results -> {
                if (results.isEmpty()) {
                    return Mono.just("Nenhuma vulnerabilidade detectada.");
                }

                // Apenas o Ollama vai para thread separada
                return Mono.fromCallable(() ->
                    clientOllamaService.sendRequest(
                        SystemPrompts.buildXssAnalysisPrompt(results.get(0), results.get(0).payload())
                    )
                ).subscribeOn(Schedulers.boundedElastic());
            });
    }

    }
}
