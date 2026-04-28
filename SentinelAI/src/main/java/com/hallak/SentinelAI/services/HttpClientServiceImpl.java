package com.hallak.SentinelAI.services;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;

import reactor.core.publisher.Mono;

@Service
public class HttpClientServiceImpl implements HttpClientService {


    private final WebClient webClient;


    @Autowired
    public HttpClientServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }


    @Override
    public Mono<HttpResponseDataDTO> sendRequest(String url) {

        long start = System.currentTimeMillis();

        return webClient.get().
                uri(url)
                    .exchangeToMono(response ->
                            response.bodyToMono(String.class)
                                    .map(body -> {
                                        long time = System.currentTimeMillis() - start;
                                        return new HttpResponseDataDTO(url, body,
                                                response.statusCode().value(),
                                                time,
                                                body.length(),
                                            url.substring(url.indexOf("=") + 1));
                                    })
                    )
                    .timeout(Duration.ofSeconds(5))
                    .onErrorResume(e -> Mono.empty());
        }
                    
    }
        /* So para testar com o cookie de sessao no DVWA 
    return webClient.get()
            .uri(url)
            .header("Cookie", "PHPSESSID=pkgi206dvqm3di0mp6j83du667; security=low")
            .exchangeToMono(response ->
                    response.bodyToMono(String.class)
                            .map(body -> {
                                long time = System.currentTimeMillis() - start;
                                return new HttpResponseDataDTO(
                                        url,
                                        body,
                                        response.statusCode().value(),
                                        time,
                                        body.length(),
                                        url.substring(url.indexOf("=") + 1)
                                );
                            })
            )
            .timeout(Duration.ofSeconds(5))
            .onErrorResume(e -> Mono.empty());
    }
}*/






    



