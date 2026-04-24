package com.hallak.SentinelAI.services.lfi;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LfiScanService {
    Flux<HttpResponseDataDTO> scanAndBasicFilter(String target) throws Exception;
    Mono<String> handleLfiScanner(String target) throws Exception;
}
