package com.hallak.SentinelAI.dtos;

public record HttpResponseDataDTO(String url, String body, int statusCode, long responseTime, int contentLength, String payload) {
}
