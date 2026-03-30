package com.hallak.SentinelAI.services.csrf;

import com.hallak.SentinelAI.services.HttpClientService;
import com.hallak.SentinelAI.services.jwt.JwtScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CsrfScanServiceImpl implements CsrfScanService {

    private final HttpClientService httpClientService;

    @Autowired
    public CsrfScanServiceImpl(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }

    @Override
    public void scan(String target) {
    }
}
