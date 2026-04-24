package com.hallak.SentinelAI.services.sd;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;
import com.hallak.SentinelAI.services.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SensitiveDataScanServiceImpl implements SensitiveDataScanService {


    private final HttpClientService httpClientService;


    @Autowired
    public SensitiveDataScanServiceImpl(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }



    @Override
    public Flux<HttpResponseDataDTO> scanAndBasicFilter(String target) {
        return Flux.empty();
    }

    @Override
    public Mono<String> handleSensitiveDataScanner(String target) throws Exception {
        return Mono.just("Sensitive Data Scan: Em desenvolvimento para " + target);
    }
}
