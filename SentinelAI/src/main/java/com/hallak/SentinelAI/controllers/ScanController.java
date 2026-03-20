package com.hallak.SentinelAI.controllers;

import com.hallak.SentinelAI.dtos.ScanDTO;
import com.hallak.SentinelAI.dtos.ScanRequest;
import com.hallak.SentinelAI.services.ScanOrchestratorService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/")
public class ScanController {

    private final ScanOrchestratorService scanOrchestratorService;

    @Autowired
    public ScanController(ScanOrchestratorService scanService) {
        this.scanOrchestratorService = scanService;
    }


    @PostMapping(value = "scan")
    public ResponseEntity<ScanDTO> newScan(@RequestBody ScanRequest scanRequest) {
        return new ResponseEntity<>(scanOrchestratorService.newScan(scanRequest), HttpStatus.OK);
    }




}
