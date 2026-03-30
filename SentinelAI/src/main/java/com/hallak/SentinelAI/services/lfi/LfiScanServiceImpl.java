package com.hallak.SentinelAI.services.lfi;


import com.hallak.SentinelAI.services.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LfiScanServiceImpl implements LfiScanService {

    private final HttpClientService httpClientService;

    @Autowired
    public LfiScanServiceImpl(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }


    @Override
    public void scan(String target) {

    }
}

