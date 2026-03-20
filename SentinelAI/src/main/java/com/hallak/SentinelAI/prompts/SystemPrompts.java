package com.hallak.SentinelAI.prompts;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;

import java.util.List;

public class SystemPrompts {

    public static String getAnalyzerHttpResponsePrompt(List<HttpResponseDataDTO> httpResponseDataDTO) {
        return """
                Colocar prompt de analise aqui. Vai receber objeto HttpResponseDataDTO
                """;
    }
}







