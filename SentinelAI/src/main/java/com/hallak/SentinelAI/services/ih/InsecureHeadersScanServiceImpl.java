package com.hallak.SentinelAI.services.ih;

import com.hallak.SentinelAI.services.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsecureHeadersScanServiceImpl implements InsecureHeadersScanService {


    private final HttpClientService httpClientService;

    @Autowired
    public InsecureHeadersScanServiceImpl(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }


    @Override
    public void scan(String target) {


    }
}
