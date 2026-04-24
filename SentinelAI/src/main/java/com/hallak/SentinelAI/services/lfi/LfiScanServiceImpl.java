package com.hallak.SentinelAI.services.lfi;


import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;
import com.hallak.SentinelAI.services.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class LfiScanServiceImpl implements LfiScanService {

    private final HttpClientService httpClientService;

    @Autowired
    public LfiScanServiceImpl(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }


    @Override
    public Flux<HttpResponseDataDTO> scanAndBasicFilter(String target) {
        return Flux.empty();
    }

    @Override
    public Mono<String> handleLfiScanner(String target) throws Exception {
        return Mono.just("LFI Scan: Em desenvolvimento para " + target);
    }
}

