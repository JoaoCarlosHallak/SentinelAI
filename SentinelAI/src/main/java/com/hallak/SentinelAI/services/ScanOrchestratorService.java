package com.hallak.SentinelAI.services;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;
import com.hallak.SentinelAI.dtos.ScanRequest;

import java.util.List;

public interface ScanOrchestratorService {
    String newScan(ScanRequest scanRequest) throws Exception;

}
