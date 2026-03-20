package com.hallak.SentinelAI.services;

import com.hallak.SentinelAI.dtos.HttpResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class HttpClientServiceImpl implements HttpClientService {


    private final WebClient webClient;


    @Autowired
    public HttpClientServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }


    public Mono<HttpResponseData> sendRequest(String url) {

        long start = System.currentTimeMillis();

        return webClient.get().
                uri(url)
                    .exchangeToMono(response ->
                            response.bodyToMono(String.class)
                                    .map(body -> {
                                        long time = System.currentTimeMillis() - start;
                                        return new HttpResponseData(body,
                                                response.statusCode().value(),
                                                time,
                                                body.length());
                                    })
                    )
                    .timeout(Duration.ofSeconds(5))
                    .onErrorResume(e -> Mono.empty());
        }
    }



