package com.hallak.SentinelAI.services.cs;

public interface CorsScanService {
    void scan(String target);
    String handleCorsScanner(String target) throws Exception;
}
