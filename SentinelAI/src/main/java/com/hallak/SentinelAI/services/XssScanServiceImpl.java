package com.hallak.SentinelAI.services;

import com.hallak.SentinelAI.dtos.ScanDTO;
import org.springframework.stereotype.Service;

@Service
public class XssScanServiceImpl implements XssScanService {

    private final HttpClientService httpClientService;

    public XssScanServiceImpl(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }

    @Override
    public ScanDTO scan(String target) {
        return new ScanDTO();
    }
}
