package com.hallak.SentinelAI.services.sd;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SensitiveDataScanService {
    Flux<HttpResponseDataDTO> scanAndBasicFilter(String target) throws Exception;
    Mono<String> handleSensitiveDataScanner(String target) throws Exception;
}
