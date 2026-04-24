package com.hallak.SentinelAI.services.ssrf;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;
import com.hallak.SentinelAI.services.HttpClientService;
import com.hallak.SentinelAI.services.sd.SensitiveDataScanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SsrfScanServiceImpl implements SsrfScanService {

    private final HttpClientService httpClientService;


    @Autowired
    public SsrfScanServiceImpl(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }


    @Override
    public Flux<HttpResponseDataDTO> scanAndBasicFilter(String target) {
        return Flux.empty();
    }

    @Override
    public Mono<String> handleSsrfScanner(String target) throws Exception {
        return Mono.just("SSRF Scan: Em desenvolvimento para " + target);
    }
}
