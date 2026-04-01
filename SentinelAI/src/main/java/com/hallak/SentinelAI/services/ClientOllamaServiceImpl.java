package com.hallak.SentinelAI.services;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClientOllamaServiceImpl implements ClientOllamaService {



    private final ChatModel chatModel;


    @Autowired
    public ClientOllamaServiceImpl(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public String sendRequest(String prompt) {
        return chatModel.call(prompt);
    }

}
