package com.hallak.SentinelAI.services.jwt;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface JwtScanService {
    Flux<HttpResponseDataDTO> scanAndBasicFilter(String target) throws Exception;
    Mono<String> handleJwtScanner(String target) throws Exception;
}
