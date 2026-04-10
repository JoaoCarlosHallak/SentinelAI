package com.hallak.SentinelAI.controllers;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;
import com.hallak.SentinelAI.dtos.ScanRequest;
import com.hallak.SentinelAI.services.ScanOrchestratorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class ScanController {

    private final ScanOrchestratorService scanOrchestratorService;

    public ScanController(ScanOrchestratorService scanService) {
        this.scanOrchestratorService = scanService;
    }


    @PostMapping(value = "scan")
    public ResponseEntity<String> newScan(@RequestBody ScanRequest scanRequest) throws Exception {
        return new ResponseEntity<>(scanOrchestratorService.newScan(scanRequest), HttpStatus.OK);
    }




}
