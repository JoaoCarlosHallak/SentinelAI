package com.hallak.SentinelAI.services.sqli;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;
import com.hallak.SentinelAI.services.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SqlInjectionScanServiceImpl implements SqlInjectionScanService {

    private final HttpClientService httpClientService;

    @Autowired
    public SqlInjectionScanServiceImpl(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }


    @Override
    public Flux<HttpResponseDataDTO> scanAndBasicFilter(String target) {
        return Flux.empty();
    }

    @Override
    public Mono<String> handleSqlInjectionScanner(String target) throws Exception {
        return Mono.just("SQL Injection Scan: Em desenvolvimento para " + target);
    }
}
