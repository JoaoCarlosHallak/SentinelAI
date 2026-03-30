package com.hallak.SentinelAI.services.op;

import com.hallak.SentinelAI.services.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpenRedirectScanServiceImpl implements OpenRedirectScanService {


    private final HttpClientService httpClientService;

    @Autowired
    public OpenRedirectScanServiceImpl(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }


    @Override
    public void scan(String target) {

    }
}
