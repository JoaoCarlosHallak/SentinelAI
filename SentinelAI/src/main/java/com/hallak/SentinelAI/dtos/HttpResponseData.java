package com.hallak.SentinelAI.dtos;

public record HttpResponseData(String body, int statusCode, long responseTime, int contentLenght) {
}
