package com.hallak.SentinelAI.services.cs;

import com.hallak.SentinelAI.services.HttpClientService;
import com.hallak.SentinelAI.services.jwt.JwtScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CorsScanServiceImpl implements CorsScanService {


    private final HttpClientService httpClientService;

    @Autowired
    public CorsScanServiceImpl(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }

    @Override
    public void scan(String target) {

    }
}
