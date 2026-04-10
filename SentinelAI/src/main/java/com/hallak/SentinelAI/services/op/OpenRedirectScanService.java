package com.hallak.SentinelAI.services.op;

public interface OpenRedirectScanService {
    void scan(String target);
    String handleOpenRedirectScanner(String target) throws Exception;
}
