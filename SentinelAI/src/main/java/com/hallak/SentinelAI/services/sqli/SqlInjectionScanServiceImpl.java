package com.hallak.SentinelAI.services.sqli;

import com.hallak.SentinelAI.services.HttpClientService;
import com.hallak.SentinelAI.services.wa.WeakAuthScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SqlInjectionScanServiceImpl implements SqlInjectionScanService {

    private final HttpClientService httpClientService;

    @Autowired
    public SqlInjectionScanServiceImpl(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }


    @Override
    public void scan(String target) {

    }
}
