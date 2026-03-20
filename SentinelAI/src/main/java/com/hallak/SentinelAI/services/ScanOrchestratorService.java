package com.hallak.SentinelAI.services;

import com.hallak.SentinelAI.dtos.ScanDTO;
import com.hallak.SentinelAI.dtos.ScanRequest;

public interface ScanOrchestratorService {
    ScanDTO newScan(ScanRequest scanRequest);

}
