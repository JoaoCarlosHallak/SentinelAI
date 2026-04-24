package com.hallak.SentinelAI.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;
import com.hallak.SentinelAI.dtos.ScanRequest;
import com.hallak.SentinelAI.dtos.ScanType;
import com.hallak.SentinelAI.services.ci.CommandInjectionScanService;
import com.hallak.SentinelAI.services.cs.CorsScanService;
import com.hallak.SentinelAI.services.csrf.CsrfScanService;
import com.hallak.SentinelAI.services.dt.DirectoryTransversalScanService;
import com.hallak.SentinelAI.services.idor.IdorScanService;
import com.hallak.SentinelAI.services.ih.InsecureHeadersScanService;
import com.hallak.SentinelAI.services.jwt.JwtScanService;
import com.hallak.SentinelAI.services.lfi.LfiScanService;
import com.hallak.SentinelAI.services.op.OpenRedirectScanService;
import com.hallak.SentinelAI.services.rl.RateLimitScanService;
import com.hallak.SentinelAI.services.sd.SensitiveDataScanService;
import com.hallak.SentinelAI.services.sqli.SqlInjectionScanService;
import com.hallak.SentinelAI.services.ssrf.SsrfScanService;
import com.hallak.SentinelAI.services.wa.WeakAuthScanService;
import com.hallak.SentinelAI.services.xss.XssScanService;

import reactor.core.publisher.Mono;

@Service
public class ScanOrchestratorServiceImpl implements ScanOrchestratorService {

    private final XssScanService xssScanService;
    private final LfiScanService lfiScanService;
    private final SqlInjectionScanService sqlInjectionScanService;
    private final OpenRedirectScanService openRedirectScanService;
    private final DirectoryTransversalScanService directoryTransversalScanService;
    private final InsecureHeadersScanService insecureHeadersScanService;
    private final SsrfScanService ssrfScanService;
    private final CorsScanService corsScanService;
    private final CommandInjectionScanService commandInjectionScanService;
    private final WeakAuthScanService weakAuthScanService;
    private final SensitiveDataScanService sensitiveDataScanService;
    private final CsrfScanService csrfScanService;
    private final RateLimitScanService rateLimitScanService;
    private final IdorScanService idorScanService;
    private final JwtScanService jwtScanService;

    @Autowired
    public ScanOrchestratorServiceImpl(XssScanService xssScanService, LfiScanService lfiScanService, SqlInjectionScanService sqlInjectionScanService, OpenRedirectScanService openRedirectScanService, DirectoryTransversalScanService directoryTransversalScanService, InsecureHeadersScanService insecureHeadersScanService, SsrfScanService ssrfScanService, CorsScanService corsScanService, CommandInjectionScanService commandInjectionScanService, WeakAuthScanService weakAuthScanService, SensitiveDataScanService sensitiveDataScanService, CsrfScanService csrfScanService, RateLimitScanService rateLimitScanService, IdorScanService idorScanService, JwtScanService jwtScanService) {
        this.xssScanService = xssScanService;
        this.lfiScanService = lfiScanService;
        this.sqlInjectionScanService = sqlInjectionScanService;
        this.openRedirectScanService = openRedirectScanService;
        this.directoryTransversalScanService = directoryTransversalScanService;
        this.insecureHeadersScanService = insecureHeadersScanService;
        this.ssrfScanService = ssrfScanService;
        this.corsScanService = corsScanService;
        this.commandInjectionScanService = commandInjectionScanService;
        this.weakAuthScanService = weakAuthScanService;
        this.sensitiveDataScanService = sensitiveDataScanService;
        this.csrfScanService = csrfScanService;
        this.rateLimitScanService = rateLimitScanService;
        this.idorScanService = idorScanService;
        this.jwtScanService = jwtScanService;
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
                case DIRECTORY_TRAVERSAL -> {
                    return directoryTransversalScanService.handleDirectoryTraversalScanner(target);
                }
                case INSECURE_HEADERS -> {
                    return insecureHeadersScanService.handleInsecureHeadersScanner(target);
                }
                case SSRF -> {
                    return ssrfScanService.handleSsrfScanner(target);
                }
                case CORS_MISCONFIGURATION -> {
                    return corsScanService.handleCorsScanner(target);
                }
                case COMMAND_INJECTION -> {
                    return commandInjectionScanService.handleCommandInjectionScanner(target);
                }
                case WEAK_AUTHENTICATION -> {
                    return weakAuthScanService.handleWeakAuthScanner(target);
                }
                case SENSITIVE_DATA_EXPOSURE -> {
                    return sensitiveDataScanService.handleSensitiveDataScanner(target);
                }
                case CSRF -> {
                    return csrfScanService.handleCsrfScanner(target);
                }
                case RATE_LIMIT_BYPASS -> {
                    return rateLimitScanService.handleRateLimitScanner(target);
                }
                case IDOR -> {
                    return idorScanService.handleIdorScanner(target);
                }
                case JWT_MISCONFIGURATION -> {
                    return jwtScanService.handleJwtScanner(target);
                }
                default -> throw new IllegalArgumentException("Unsupported scan type: " + scanType);
            }
        }
        return null;
    }
}
