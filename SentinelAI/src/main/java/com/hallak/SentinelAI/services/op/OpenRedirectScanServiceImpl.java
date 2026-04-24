package com.hallak.SentinelAI.services.op;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;
import com.hallak.SentinelAI.services.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OpenRedirectScanServiceImpl implements OpenRedirectScanService {


    private final HttpClientService httpClientService;

    @Autowired
    public OpenRedirectScanServiceImpl(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }


    @Override
    public Flux<HttpResponseDataDTO> scanAndBasicFilter(String target) {
        return Flux.empty();
    }

    @Override
    public Mono<String> handleOpenRedirectScanner(String target) throws Exception {
        return Mono.just("Open Redirect Scan: Em desenvolvimento para " + target);
    }
}
