package com.hallak.SentinelAI.services.xss;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;
import com.hallak.SentinelAI.services.HttpClientService;
import com.hallak.SentinelAI.services.LoadPayloadsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class XssScanServiceImpl implements XssScanService {


    private final HttpClientService httpClientService;
    private final LoadPayloadsService loadPayloadsService;


    @Autowired
    public XssScanServiceImpl(HttpClientService httpClientService, LoadPayloadsService loadPayloadsService) {
        this.httpClientService = httpClientService;
        this.loadPayloadsService = loadPayloadsService;
    }

    @Override
    public List<HttpResponseDataDTO> scan(String target) throws Exception {
        System.out.println(target + "/" + loadPayloadsService.loadPayloads("payloads/xss.txt").get(0));
        return Collections.singletonList(new HttpResponseDataDTO("", 200, 23, 250));
    }
}
