package com.hallak.SentinelAI.services;

import com.hallak.SentinelAI.dtos.HttpResponseDataDTO;
import com.hallak.SentinelAI.prompts.SystemPrompts;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AIHttpResponseAnalyzerServiceImpl implements AIHttpResponseAnalyzerService {





    private final ChatModel chatModel;

    public AIHttpResponseAnalyzerServiceImpl(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public String sendToAI(List<HttpResponseDataDTO> httpResponseDataDTOs) {
        return chatModel.call(SystemPrompts.getAnalyzerHttpResponsePrompt(httpResponseDataDTOs));
    }
}











