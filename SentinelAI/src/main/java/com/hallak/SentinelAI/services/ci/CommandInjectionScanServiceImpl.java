package com.hallak.SentinelAI.services.ci;

import com.hallak.SentinelAI.services.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandInjectionScanServiceImpl implements CommandInjectionScanService {

    @Autowired
    private final HttpClientService httpClientService;

    public CommandInjectionScanServiceImpl(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }


    @Override
    public void scan(String target) {

    }
}
