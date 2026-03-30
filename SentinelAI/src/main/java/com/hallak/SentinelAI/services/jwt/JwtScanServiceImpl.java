package com.hallak.SentinelAI.services.jwt;

import com.hallak.SentinelAI.services.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JwtScanServiceImpl implements JwtScanService {


    private final HttpClientService httpClientService;

    @Autowired
    public JwtScanServiceImpl(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }




    @Override
    public void scan(String target) {


    }



}
