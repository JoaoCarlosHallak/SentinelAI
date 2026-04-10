package com.hallak.SentinelAI.services.sd;

public interface SensitiveDataScanService {
    void scan(String target);
    String handleSensitiveDataScanner(String target) throws Exception;
}
