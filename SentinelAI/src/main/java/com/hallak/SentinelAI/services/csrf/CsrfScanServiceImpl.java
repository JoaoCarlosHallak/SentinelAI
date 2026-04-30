package com.hallak.SentinelAI.services.csrf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;
import com.hallak.SentinelAI.prompts.SystemPrompts;
import com.hallak.SentinelAI.services.ClientOllamaService;
import com.hallak.SentinelAI.services.HttpClientService;
import com.hallak.SentinelAI.services.LoadPayloadsService;
import com.hallak.SentinelAI.utils.ScanHelperUtils;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class CsrfScanServiceImpl implements CsrfScanService {

    private final HttpClientService httpClientService;
    private final LoadPayloadsService loadPayloadsService;
    private final ClientOllamaService clientOllamaService;

    @Autowired
    public CsrfScanServiceImpl(HttpClientService httpClientService, LoadPayloadsService loadPayloadsService, ClientOllamaService clientOllamaService) {
        this.httpClientService = httpClientService;
        this.loadPayloadsService = loadPayloadsService;
        this.clientOllamaService = clientOllamaService;
    }


    @Override
    public Mono<HttpResponseDataDTO> scanAndBasicFilter(String target) throws Exception { 

        List<String> sensitiveActions = ScanHelperUtils.mixPayloadAndTarget(loadPayloadsService.loadPayloads("payloads/csrfSensitiveActions.txt"), target);
    

        return httpClientService.sendRequest(target)
            .filter(res -> res.statusCode() < 500) 
            .filter(res -> res.body() != null && !res.body().isEmpty())
            .flatMap(data -> {
                String body = data.body().toLowerCase();

                boolean hasForm = body.contains("<form");
                boolean isPost = body.contains("method=\"post\"");
                boolean sensitive = sensitiveActions.stream().anyMatch(action -> body.contains(action.toLowerCase()));
                boolean hasToken =
                    body.contains("csrf") ||
                    body.contains("token");


                if (hasForm && isPost && sensitive && !hasToken){
                    return Mono.just(new HttpResponseDataDTO(
                        data.url(),
                        data.header(),
                        data.body(),
                        data.statusCode(),
                        data.responseTime(),
                        data.contentLength()
                    ));
                } else {
                    return Mono.empty();
                }

            }).doOnNext(res -> System.out.println("Csrf possible: " + res.url() +
                                " Status: " + res.statusCode()));
        

    }


    @Override
    public Mono<String> handleCsrfScanner(String target) throws Exception {

        return scanAndBasicFilter(target) 
            .flatMap(result ->
                Mono.fromCallable(() ->
                    clientOllamaService.sendRequest(
                        SystemPrompts.buildCsrfAnalysisPrompt(result)
                    )
                ).subscribeOn(Schedulers.boundedElastic())
            )
            .switchIfEmpty(Mono.just("No vulnerabilities detected."));
    }
}

