package com.hallak.SentinelAI.dtos;

public record HttpResponseDataDTO(String body, int statusCode, long responseTime, int contentLenght) {
}
