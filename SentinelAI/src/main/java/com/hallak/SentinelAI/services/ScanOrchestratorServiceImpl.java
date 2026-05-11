package com.hallak.SentinelAI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hallak.SentinelAI.dtos.ScanRequest;
import com.hallak.SentinelAI.dtos.ScanType;
import com.hallak.SentinelAI.services.ci.CommandInjectionScanService;
import com.hallak.SentinelAI.services.csrf.CsrfScanService;
import com.hallak.SentinelAI.services.idor.IdorScanService;
import com.hallak.SentinelAI.services.ih.InsecureHeadersScanService;
import com.hallak.SentinelAI.services.lfi.LfiScanService;
import com.hallak.SentinelAI.services.op.OpenRedirectScanService;
import com.hallak.SentinelAI.services.sqli.SqlInjectionScanService;
import com.hallak.SentinelAI.services.xss.XssScanService;

import reactor.core.publisher.Mono;

@Service
public class ScanOrchestratorServiceImpl implements ScanOrchestratorService {

    private final XssScanService xssScanService;
    private final LfiScanService lfiScanService;
    private final SqlInjectionScanService sqlInjectionScanService;
    private final OpenRedirectScanService openRedirectScanService;
    private final InsecureHeadersScanService insecureHeadersScanService;
    private final CommandInjectionScanService commandInjectionScanService;
    private final CsrfScanService csrfScanService;
    private final IdorScanService idorScanService;


    @Autowired
    public ScanOrchestratorServiceImpl(XssScanService xssScanService, LfiScanService lfiScanService, SqlInjectionScanService sqlInjectionScanService, OpenRedirectScanService openRedirectScanService, InsecureHeadersScanService insecureHeadersScanService, CommandInjectionScanService commandInjectionScanService, CsrfScanService csrfScanService, IdorScanService idorScanService) {
        this.xssScanService = xssScanService;
        this.lfiScanService = lfiScanService;
        this.sqlInjectionScanService = sqlInjectionScanService;
        this.openRedirectScanService = openRedirectScanService;
        this.insecureHeadersScanService = insecureHeadersScanService;
        this.commandInjectionScanService = commandInjectionScanService;
        this.csrfScanService = csrfScanService;
        this.idorScanService = idorScanService;
    }


    @Override
    public Mono<String> newScan(ScanRequest scanRequest) throws Exception {
        String target = scanRequest.target();
        for (ScanType scanType : scanRequest.scanTypeList()) {
            switch (scanType) {
                case XSS -> {
                    return xssScanService.handleXssScanner(target);
                }
                case LFI -> {
                    return lfiScanService.handleLfiScanner(target);
                }
                case SQL_INJECTION -> {
                    return sqlInjectionScanService.handleSqlInjectionScanner(target);
                }
                case OPEN_REDIRECT -> {
                    return openRedirectScanService.handleOpenRedirectScanner(target);
                }
                case INSECURE_HEADERS -> {
                    return insecureHeadersScanService.handleInsecureHeadersScanner(target);
                }
                case COMMAND_INJECTION -> {
                    return commandInjectionScanService.handleCommandInjectionScanner(target);
                }
                case CSRF -> {
                    return csrfScanService.handleCsrfScanner(target);
                }
                case IDOR -> {
                    return idorScanService.handleIdorScanner(target);
                }
                default -> throw new IllegalArgumentException("Unsupported scan type: " + scanType);
            }
        }
        return null;
    }
}
