package com.hallak.SentinelAI.services.idor;

import com.hallak.SentinelAI.services.HttpClientService;
import com.hallak.SentinelAI.services.dt.DirectoryTransversalScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdorScanServiceImpl implements IdorScanService {

    private final HttpClientService httpClientService;

    @Autowired
    public IdorScanServiceImpl(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }


    @Override
    public void scan(String target) {

    }

    @Override
    public String handleIdorScanner(String target) throws Exception {
        System.out.println("IDOR Scan started for: " + target);
        return null;
    }
}
