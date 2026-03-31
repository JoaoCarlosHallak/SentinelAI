package com.hallak.SentinelAI.services.xss;


import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;

import reactor.core.publisher.Flux;



public interface XssScanService {

    Flux<HttpResponseDataDTO> scanAndBasicFilter(String target) throws Exception;
}
