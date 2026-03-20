package com.hallak.SentinelAI.services;

import com.hallak.SentinelAI.dtos.ScanDTO;
import com.hallak.SentinelAI.dtos.ScanRequest;
import com.hallak.SentinelAI.dtos.ScanType;
import com.hallak.SentinelAI.repositories.ScanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScanOrchestratorServiceImpl implements ScanOrchestratorService {

    private final XssScanService xssScanService;
    private final LfiScanService lfiScanService;
    private final SqlInjectionScanService sqlInjectionScanService;
    private final OpenRedirectScanService openRedirectScanService;
    private final DirectoryTraversalScanService directoryTraversalScanService;
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
    public ScanOrchestratorServiceImpl(XssScanService xssScanService, LfiScanService lfiScanService, SqlInjectionScanService sqlInjectionScanService, OpenRedirectScanService openRedirectScanService, DirectoryTraversalScanService directoryTraversalScanService, InsecureHeadersScanService insecureHeadersScanService, SsrfScanService ssrfScanService, CorsScanService corsScanService, CommandInjectionScanService commandInjectionScanService, WeakAuthScanService weakAuthScanService, SensitiveDataScanService sensitiveDataScanService, CsrfScanService csrfScanService, RateLimitScanService rateLimitScanService, IdorScanService idorScanService, JwtScanService jwtScanService) {
        this.xssScanService = xssScanService;
        this.lfiScanService = lfiScanService;
        this.sqlInjectionScanService = sqlInjectionScanService;
        this.openRedirectScanService = openRedirectScanService;
        this.directoryTraversalScanService = directoryTraversalScanService;
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
    public ScanDTO newScan(ScanRequest scanRequest) {
        String target = scanRequest.target();
        for (ScanType scanType : scanRequest.scanTypeList()) {

            switch (scanType) {
                case XSS -> xssScanService.scan(target);

                case LFI -> lfiScanService.scan(target);

                case SQL_INJECTION -> sqlInjectionScanService.scan(target);

                case OPEN_REDIRECT -> openRedirectScanService.scan(target);

                case DIRECTORY_TRAVERSAL -> directoryTraversalScanService.scan(target);

                case INSECURE_HEADERS -> insecureHeadersScanService.scan(target);

                case SSRF -> ssrfScanService.scan(target);

                case CORS_MISCONFIGURATION -> corsScanService.scan(target);

                case COMMAND_INJECTION -> commandInjectionScanService.scan(target);

                case WEAK_AUTHENTICATION -> weakAuthScanService.scan(target);

                case SENSITIVE_DATA_EXPOSURE -> sensitiveDataScanService.scan(target);

                case CSRF -> csrfScanService.scan(target);

                case RATE_LIMIT_BYPASS -> rateLimitScanService.scan(target);

                case IDOR -> idorScanService.scan(target);

                case JWT_MISCONFIGURATION -> jwtScanService.scan(target);
            }

        }
        return null;
    }
}
