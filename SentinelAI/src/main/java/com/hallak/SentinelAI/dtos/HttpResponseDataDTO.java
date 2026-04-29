package com.hallak.SentinelAI.dtos;

import org.springframework.http.HttpHeaders;

public class HttpResponseDataDTO {

    private final  String url;
    private final HttpHeaders header;
    private final String body;
    private final int statusCode;
    private final long responseTime;
    private final int contentLength;
    private final String payload;

    public HttpResponseDataDTO(String url, HttpHeaders header, String body, int statusCode, long responseTime, int contentLength, String payload) {
        this.url = url;
        this.header = header;
        this.body = body;
        this.statusCode = statusCode;
        this.responseTime = responseTime;
        this.contentLength = contentLength;
        this.payload = payload;
    }

    public HttpResponseDataDTO(String url, HttpHeaders header, String body, int statusCode, long responseTime, int contentLength) {
        this.url = url;
        this.header = header;
        this.body = body;
        this.statusCode = statusCode;
        this.responseTime = responseTime;
        this.contentLength = contentLength;
        this.payload = null;
    }

    
    public String url() {
        return url;
    }

    public HttpHeaders header() {
        return header;
    }

    public String body() {
        return body;
    }

    public int statusCode() {
        return statusCode;
    }

    public long responseTime() {
        return responseTime;
    }

    public int contentLength() {
        return contentLength;
    }

    public String payload() {
        return payload;
    }    
}
