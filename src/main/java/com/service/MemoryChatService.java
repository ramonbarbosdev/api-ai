package com.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;

@Service
public class MemoryChatService {

    private final ChatClient.Builder builder;
    private final ChatMemory chatMemory;

    public MemoryChatService(ChatClient.Builder builder, ChatMemory chatMemory) {
        this.builder = builder;
        this.chatMemory = chatMemory;

    }

    public String generation(String conversationId, String message) {

        ChatClient chatClient = builder
                .defaultAdvisors(
                        MessageChatMemoryAdvisor
                                .builder(chatMemory)
                                .conversationId(conversationId) // ← AQUI
                                .build()
                )
                .build();

        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }

}

