package com.hallak.SentinelAI.services.csrf;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;
import com.hallak.SentinelAI.services.HttpClientService;
import com.hallak.SentinelAI.services.jwt.JwtScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CsrfScanServiceImpl implements CsrfScanService {

    private final HttpClientService httpClientService;

    @Autowired
    public CsrfScanServiceImpl(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }

    @Override
    public Flux<HttpResponseDataDTO> scanAndBasicFilter(String target) {
        return Flux.empty();
    }

    @Override
    public Mono<String> handleCsrfScanner(String target) throws Exception {
        return Mono.just("CSRF Scan: Em desenvolvimento para " + target);
    }
}
