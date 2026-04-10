package com.hallak.SentinelAI.services.jwt;

public interface JwtScanService {
    void scan(String target);
    String handleJwtScanner(String target) throws Exception;
}
