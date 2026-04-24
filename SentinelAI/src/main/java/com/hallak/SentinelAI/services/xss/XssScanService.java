package com.hallak.SentinelAI.services.xss;


import java.util.List;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface XssScanService {

    Flux<HttpResponseDataDTO> scanAndBasicFilter(String target) throws Exception;
    Mono<String> handleXssScanner(String target) throws Exception;
}
