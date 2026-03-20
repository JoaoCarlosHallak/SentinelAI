package com.hallak.SentinelAI.services;

import com.hallak.SentinelAI.dtos.ScanDTO;
import com.hallak.SentinelAI.dtos.ScanRequest;
import com.hallak.SentinelAI.dtos.ScanType;
import com.hallak.SentinelAI.repositories.ScanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScanOrchestratorServiceImpl implements ScanOrchestratorService{

    private final XssScanService xssScanService;

    public ScanOrchestratorServiceImpl(XssScanService xssScanService) {
        this.xssScanService = xssScanService;
    }


    @Override
    public ScanDTO newScan(ScanRequest scanRequest) {
        String target = scanRequest.target();
        for (ScanType scanType : scanRequest.scanTypeList()) {

            switch (scanType) {
                case XSS -> xssScanService.scan(target);
            }

        }


    }
}
