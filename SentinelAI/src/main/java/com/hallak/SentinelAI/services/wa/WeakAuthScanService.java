package com.hallak.SentinelAI.services.wa;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WeakAuthScanService {
    Flux<HttpResponseDataDTO> scanAndBasicFilter(String target) throws Exception;
    Mono<String> handleWeakAuthScanner(String target) throws Exception;
}
