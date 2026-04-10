package com.hallak.SentinelAI.services.xss;


import java.util.List;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;

import reactor.core.publisher.Flux;


public interface XssScanService {

    Flux<HttpResponseDataDTO> scanAndBasicFilter(String target) throws Exception;
    String handleXssScanner(String target) throws Exception;
}
