package com.hallak.SentinelAI.services.ih;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;
import com.hallak.SentinelAI.services.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class InsecureHeadersScanServiceImpl implements InsecureHeadersScanService {


    private final HttpClientService httpClientService;

    @Autowired
    public InsecureHeadersScanServiceImpl(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }


    @Override
    public Flux<HttpResponseDataDTO> scanAndBasicFilter(String target) {
        return Flux.empty();
    }

    @Override
    public Mono<String> handleInsecureHeadersScanner(String target) throws Exception {
        System.out.println("Insecure Headers Scan started for: " + target);
        return Mono.just("Insecure Headers Scan: Em desenvolvimento para " + target);
    }
}
