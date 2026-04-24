package com.hallak.SentinelAI.services.jwt;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;
import com.hallak.SentinelAI.services.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class JwtScanServiceImpl implements JwtScanService {


    private final HttpClientService httpClientService;

    @Autowired
    public JwtScanServiceImpl(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }




    @Override
    public Flux<HttpResponseDataDTO> scanAndBasicFilter(String target) {
        return Flux.empty();
    }

    @Override
    public Mono<String> handleJwtScanner(String target) throws Exception {
        return Mono.just("JWT Misconfiguration Scan: Em desenvolvimento para " + target);
    }
}
