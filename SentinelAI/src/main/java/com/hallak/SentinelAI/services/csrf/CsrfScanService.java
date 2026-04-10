package com.hallak.SentinelAI.services.csrf;

public interface CsrfScanService {
    void scan(String target);
    String handleCsrfScanner(String target) throws Exception;
}
