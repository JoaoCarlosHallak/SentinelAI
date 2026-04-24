package com.hallak.SentinelAI.services.dt;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;
import com.hallak.SentinelAI.services.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DirectoryTransversalScanServiceImpl implements DirectoryTransversalScanService{

    private final HttpClientService httpClientService;

    @Autowired
    public DirectoryTransversalScanServiceImpl(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }


    @Override
    public Flux<HttpResponseDataDTO> scanAndBasicFilter(String target) {
        return Flux.empty();
    }

    @Override
    public Mono<String> handleDirectoryTraversalScanner(String target) throws Exception {
        return Mono.just("Directory Traversal Scan: Em desenvolvimento para " + target);
    }
}
