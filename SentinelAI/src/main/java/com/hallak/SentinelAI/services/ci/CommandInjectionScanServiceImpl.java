package com.hallak.SentinelAI.services.ci;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;
import com.hallak.SentinelAI.services.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CommandInjectionScanServiceImpl implements CommandInjectionScanService {

    @Autowired
    private final HttpClientService httpClientService;

    public CommandInjectionScanServiceImpl(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }


    @Override
    public Flux<HttpResponseDataDTO> scanAndBasicFilter(String target) {
        return Flux.empty();
    }

    @Override
    public Mono<String> handleCommandInjectionScanner(String target) throws Exception {
        return Mono.just("Command Injection Scan: Em desenvolvimento para " + target);
    }
}
