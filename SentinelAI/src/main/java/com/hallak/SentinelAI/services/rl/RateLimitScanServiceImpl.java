package com.hallak.SentinelAI.services.rl;


import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;
import com.hallak.SentinelAI.services.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RateLimitScanServiceImpl implements RateLimitScanService{


    private final HttpClientService httpClientService;

    @Autowired
    public RateLimitScanServiceImpl(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }


    @Override
    public Flux<HttpResponseDataDTO> scanAndBasicFilter(String target) {
        return Flux.empty();
    }

    @Override
    public Mono<String> handleRateLimitScanner(String target) throws Exception {
        return Mono.just("Rate Limit Scan: Em desenvolvimento para " + target);
    }
}
