package com.hallak.SentinelAI.services.wa;

import com.hallak.SentinelAI.services.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeakAuthScanServiceImpl implements WeakAuthScanService {

    private final HttpClientService httpClientService;

    @Autowired
    public WeakAuthScanServiceImpl(HttpClientService httpClientService)
    {
        this.httpClientService = httpClientService;
    }

    @Override
    public void scan(String target) {

    }

    @Override
    public String handleWeakAuthScanner(String target) throws Exception {
        System.out.println("Weak Authentication Scan started for: " + target);
        return null;
    }
}
