package com.hallak.SentinelAI.services.wa;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;
import com.hallak.SentinelAI.services.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class WeakAuthScanServiceImpl implements WeakAuthScanService {

    private final HttpClientService httpClientService;

    @Autowired
    public WeakAuthScanServiceImpl(HttpClientService httpClientService)
    {
        this.httpClientService = httpClientService;
    }

    @Override
    public Flux<HttpResponseDataDTO> scanAndBasicFilter(String target) {
        return Flux.empty();
    }

    @Override
    public Mono<String> handleWeakAuthScanner(String target) throws Exception {
        return Mono.just("Weak Authentication Scan: Em desenvolvimento para " + target);
    }
}
