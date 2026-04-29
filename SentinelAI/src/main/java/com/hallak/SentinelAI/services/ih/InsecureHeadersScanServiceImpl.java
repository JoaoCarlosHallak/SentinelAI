package com.hallak.SentinelAI.services.ih;

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
public class InsecureHeadersScanServiceImpl implements InsecureHeadersScanService {


    private final HttpClientService httpClientService;
    private final LoadPayloadsService loadPayloadsService;
    private final ClientOllamaService clientOllamaService;

    @Autowired
    public InsecureHeadersScanServiceImpl(HttpClientService httpClientService, LoadPayloadsService loadPayloadsService, ClientOllamaService clientOllamaService) {
        this.httpClientService = httpClientService;
        this.loadPayloadsService = loadPayloadsService;
        this.clientOllamaService = clientOllamaService;
    }


    @Override
    public Mono<HttpResponseDataDTO> scanAndBasicFilter(String target) throws Exception { 

        List<String> headers = ScanHelperUtils.mixPayloadAndTarget(loadPayloadsService.loadPayloads("payloads/ih.txt"), target);
    

        return httpClientService.sendRequest(target)
            .flatMap(data -> {
                String headerLowerCase = data.header().toString().toLowerCase();

                if (headers.stream().anyMatch(header -> headerLowerCase.contains(header.toLowerCase()))  
                || !headerLowerCase.contains("Content-Security-Policy") 
                || !headerLowerCase.contains("X-Content-Type-Options") 
                || !headerLowerCase.contains("X-Frame-Options") 
                || !headerLowerCase.contains("Strict-Transport-Security")) {
                    return (Mono.just(new HttpResponseDataDTO(
                        data.url(),
                        data.header(),
                        data.body(),
                        data.statusCode(),
                        data.responseTime(),
                        data.contentLength(),
                        data.header().toString()
                    )));
                } else {
                    return Mono.empty();
                }
            }).doOnNext(res -> System.out.println("Insecure Headers possible: " + res.url() +
                                " Status: " + res.statusCode()));
        
        /*fromIterable(payloads) 
            .flatMap(httpClientService::sendRequest, 10) 
            .filter(res -> res.statusCode() < 500) 
            .filter(res -> res.body() != null && !res.body().isEmpty()) 
            .filter(res -> {
                String body = res.body().toLowerCase();
                return .stream().anyMatch(err -> body.contains(err.toLowerCase()));
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
                System.out.println("Local File Inclusion possible: " + res.url() +
                                " Status: " + res.statusCode())
            ); */

    }


    @Override
    public Mono<String> handleInsecureHeadersScanner(String target) throws Exception {

        return scanAndBasicFilter(target) 
            .flatMap(result ->
                Mono.fromCallable(() ->
                    clientOllamaService.sendRequest(
                        SystemPrompts.buildInsecureHeadersAnalysisPrompt(result)
                    )
                ).subscribeOn(Schedulers.boundedElastic())
            )
            .switchIfEmpty(Mono.just("No vulnerabilities detected."));
    }
}
