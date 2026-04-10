package com.hallak.SentinelAI.services.ssrf;

import com.hallak.SentinelAI.services.HttpClientService;
import com.hallak.SentinelAI.services.sd.SensitiveDataScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SsrfScanServiceImpl implements SsrfScanService {

    private final HttpClientService httpClientService;


    @Autowired
    public SsrfScanServiceImpl(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }


    @Override
    public void scan(String target) {

    }

    @Override
    public String handleSsrfScanner(String target) throws Exception {
        System.out.println("SSRF Scan started for: " + target);
        return null;
    }
}
