package com.hallak.SentinelAI.services;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<HttpResponseDataDTO> newScan(ScanRequest scanRequest) throws Exception {
        String target = scanRequest.target();
        for (ScanType scanType : scanRequest.scanTypeList()) {

            switch (scanType) {
                case XSS -> xssScanService.scan(target);

                case LFI -> lfiScanService.scan(target);

                case SQL_INJECTION -> sqlInjectionScanService.scan(target);

                case OPEN_REDIRECT -> openRedirectScanService.scan(target);

                case DIRECTORY_TRAVERSAL -> directoryTransversalScanService.scan(target);

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
