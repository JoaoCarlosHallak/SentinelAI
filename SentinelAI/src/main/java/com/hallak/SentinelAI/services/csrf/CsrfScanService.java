package com.hallak.SentinelAI.services.csrf;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;

import reactor.core.publisher.Mono;

public interface CsrfScanService {
    Mono<HttpResponseDataDTO> scanAndBasicFilter(String target) throws Exception;
    Mono<String> handleCsrfScanner(String target) throws Exception;
}
