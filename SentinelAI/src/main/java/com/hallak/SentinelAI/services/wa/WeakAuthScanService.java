package com.hallak.SentinelAI.services.wa;

public interface WeakAuthScanService {
    void scan(String target);
    String handleWeakAuthScanner(String target) throws Exception;
}
