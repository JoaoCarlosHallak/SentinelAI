package com.hallak.SentinelAI.services.sd;

import com.hallak.SentinelAI.services.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SensitiveDataScanServiceImpl implements SensitiveDataScanService {


    private final HttpClientService httpClientService;


    @Autowired
    public SensitiveDataScanServiceImpl(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }



    @Override
    public void scan(String target) {

    }
}
