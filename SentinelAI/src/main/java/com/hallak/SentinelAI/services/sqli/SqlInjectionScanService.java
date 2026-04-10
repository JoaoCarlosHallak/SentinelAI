package com.hallak.SentinelAI.services.sqli;

public interface SqlInjectionScanService {
    void scan(String target);
    String handleSqlInjectionScanner(String target) throws Exception;
}
