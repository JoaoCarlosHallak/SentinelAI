package com.hallak.SentinelAI.services.idor;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;
import com.hallak.SentinelAI.services.HttpClientService;
import com.hallak.SentinelAI.services.dt.DirectoryTransversalScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class IdorScanServiceImpl implements IdorScanService {

    private final HttpClientService httpClientService;

    @Autowired
    public IdorScanServiceImpl(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }


    @Override
    public Flux<HttpResponseDataDTO> scanAndBasicFilter(String target) {
        return Flux.empty();
    }

    @Override
    public Mono<String> handleIdorScanner(String target) throws Exception {
        return Mono.just("IDOR Scan: Em desenvolvimento para " + target);
    }
}
