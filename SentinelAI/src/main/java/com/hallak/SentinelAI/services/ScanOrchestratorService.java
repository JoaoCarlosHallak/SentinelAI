package com.hallak.SentinelAI.services;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;
import com.hallak.SentinelAI.dtos.ScanRequest;

import java.util.List;

import reactor.core.publisher.Mono;

public interface ScanOrchestratorService {
    Mono<String> newScan(ScanRequest scanRequest) throws Exception;

}
