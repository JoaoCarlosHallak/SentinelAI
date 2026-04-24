package com.hallak.SentinelAI.services.dt;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DirectoryTransversalScanService {
    Flux<HttpResponseDataDTO> scanAndBasicFilter(String target) throws Exception;
    Mono<String> handleDirectoryTraversalScanner(String target) throws Exception;
}
