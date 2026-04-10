package com.hallak.SentinelAI.services.lfi;

public interface LfiScanService {
    void scan(String target);
    String handleLfiScanner(String target) throws Exception;
}
