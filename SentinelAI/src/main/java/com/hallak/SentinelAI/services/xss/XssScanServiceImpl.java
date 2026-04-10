package com.hallak.SentinelAI.services.xss;




import java.util.List;

import com.hallak.SentinelAI.prompts.SystemPrompts;
import com.hallak.SentinelAI.services.ClientOllamaService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;
import com.hallak.SentinelAI.services.ClientOllamaServiceImpl;
import com.hallak.SentinelAI.services.HttpClientService;
import com.hallak.SentinelAI.services.LoadPayloadsService;
import com.hallak.SentinelAI.utils.ScanHelperUtils;

import reactor.core.publisher.Flux;

@Service
public class XssScanServiceImpl implements XssScanService {


    private final HttpClientService httpClientService;
    private final LoadPayloadsService loadPayloadsService;
    private final ClientOllamaService clientOllamaService;



    @Autowired
    public XssScanServiceImpl(HttpClientService httpClientService, 
        LoadPayloadsService loadPayloadsService, ClientOllamaService clientOllamaService) {
        this.httpClientService = httpClientService;
        this.loadPayloadsService = loadPayloadsService;
        this.clientOllamaService = clientOllamaService;
    }

    @Override
    public Flux<HttpResponseDataDTO> scanAndBasicFilter(String target) throws Exception {
        if (!ScanHelperUtils.targetValidation(target)) {
            throw new IllegalArgumentException("Invalid target format. Target must contain '=' and '?'.");
        }

        List<String> payloads = ScanHelperUtils.mixPayloadAndTarget(loadPayloadsService.loadPayloads("payloads/xss.txt"), target);


        return Flux.fromIterable(payloads)
            .flatMap(httpClientService::sendRequest, 10)
            .filter(res -> res.statusCode() < 500)
            .filter(res -> res.body() != null && !res.body().isEmpty())

            .filter(res -> {
                String body = res.body();

                int index = res.url().indexOf("=");
                String payload = res.url().substring(index + 1);

                String decoded = payload
                        .replace("%3C", "<")
                        .replace("%3E", ">")
                        .replace("%22", "\"")
                        .replace("%27", "'");

                return body.contains(payload)
                    || body.contains(decoded);
            })
            .map(res.payload(p -> {
                int index = p.url().indexOf("=");
                return p.url().substring(index + 1);
            }))

            .doOnNext(res ->
                System.out.println("XSS possible: " + res.url() +
                                " Status: " + res.statusCode())
            );



    }



    public List<HttpResponseDataDTO> handleXssScanner(String target) throws Exception {
        List<HttpResponseDataDTO> results = scanAndBasicFilter(target).collectList().block();
        System.out.println(clientOllamaService.sendRequest(SystemPrompts.buildXssAnalysisPrompt(results.get(0), results.get(0).payload())));
        


        return null;


    }







    }
