package com.hallak.SentinelAI.services.ih;

public interface InsecureHeadersScanService {
    void scan(String target);
    String handleInsecureHeadersScanner(String target) throws Exception;
}


