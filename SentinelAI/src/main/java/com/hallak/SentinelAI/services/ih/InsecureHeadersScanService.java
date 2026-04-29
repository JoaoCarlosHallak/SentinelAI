package com.hallak.SentinelAI.services.ih;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;

import reactor.core.publisher.Mono;

public interface InsecureHeadersScanService {
    Mono<HttpResponseDataDTO> scanAndBasicFilter(String target) throws Exception;
    Mono<String> handleInsecureHeadersScanner(String target) throws Exception;
}


