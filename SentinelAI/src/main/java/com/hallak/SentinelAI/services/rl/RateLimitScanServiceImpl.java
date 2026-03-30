package com.hallak.SentinelAI.services.rl;


import com.hallak.SentinelAI.services.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RateLimitScanServiceImpl implements RateLimitScanService{


    private final HttpClientService httpClientService;

    @Autowired
    public RateLimitScanServiceImpl(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }


    @Override
    public void scan(String target) {

    }
}
