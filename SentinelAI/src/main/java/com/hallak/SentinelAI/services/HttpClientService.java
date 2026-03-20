package com.hallak.SentinelAI.services;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;
import reactor.core.publisher.Mono;

public interface HttpClientService {
    Mono<HttpResponseDataDTO> sendRequest(String url);
}
