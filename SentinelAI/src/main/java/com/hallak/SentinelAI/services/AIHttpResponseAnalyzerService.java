package com.hallak.SentinelAI.services;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;

import java.util.List;

public interface AIHttpResponseAnalyzerService {
    String sendToAI(List<HttpResponseDataDTO> httpResponseDataDTOs);
}