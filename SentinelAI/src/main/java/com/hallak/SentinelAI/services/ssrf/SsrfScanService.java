package com.hallak.SentinelAI.services.ssrf;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SsrfScanService {
    Flux<HttpResponseDataDTO> scanAndBasicFilter(String target) throws Exception;
    Mono<String> handleSsrfScanner(String target) throws Exception;
}
