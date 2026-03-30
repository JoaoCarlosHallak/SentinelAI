package com.hallak.SentinelAI.services.dt;

import com.hallak.SentinelAI.services.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectoryTransversalScanServiceImpl implements DirectoryTransversalScanService{

    private final HttpClientService httpClientService;

    @Autowired
    public DirectoryTransversalScanServiceImpl(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }


    @Override
    public void scan(String target) {
    }
}
