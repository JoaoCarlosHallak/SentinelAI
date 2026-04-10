package com.hallak.SentinelAI.services.rl;

public interface RateLimitScanService {
    void scan(String target);
    String handleRateLimitScanner(String target) throws Exception;
}
