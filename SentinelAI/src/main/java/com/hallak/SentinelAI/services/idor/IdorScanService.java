package com.hallak.SentinelAI.services.idor;

public interface IdorScanService {
    void scan(String target);
    String handleIdorScanner(String target) throws Exception;
}
