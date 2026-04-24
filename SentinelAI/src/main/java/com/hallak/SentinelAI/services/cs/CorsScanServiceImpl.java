package com.hallak.SentinelAI.services.cs;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;
import com.hallak.SentinelAI.services.HttpClientService;
import com.hallak.SentinelAI.services.jwt.JwtScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CorsScanServiceImpl implements CorsScanService {


    private final HttpClientService httpClientService;

    @Autowired
    public CorsScanServiceImpl(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }

    @Override
    public Flux<HttpResponseDataDTO> scanAndBasicFilter(String target) {
        return Flux.empty();
    }

    @Override
    public Mono<String> handleCorsScanner(String target) throws Exception {
        return Mono.just("CORS Scan: Em desenvolvimento para " + target);
    }
}
