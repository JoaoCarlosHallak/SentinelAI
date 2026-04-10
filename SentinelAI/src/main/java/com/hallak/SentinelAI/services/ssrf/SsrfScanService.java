package com.hallak.SentinelAI.services.ssrf;

public interface SsrfScanService {
    void scan(String target);
    String handleSsrfScanner(String target) throws Exception;
}
