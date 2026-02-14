package com.service;

import com.model.ChatResult;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.metadata.Usage;
import org.springframework.stereotype.Service;

@Service
public class MemoryChatService {

    private final ChatClient.Builder builder;
    private final ChatMemory chatMemory;

    public MemoryChatService(ChatClient.Builder builder, ChatMemory chatMemory) {
        this.builder = builder;
        this.chatMemory = chatMemory;
    }

    public ChatResult generation(String conversationId, String message) {

        ChatClient chatClient = builder
                .defaultAdvisors(
                        MessageChatMemoryAdvisor
                                .builder(chatMemory)
                                .conversationId(conversationId)
                                .build()
                )

                .build();

        ChatResponse response = chatClient.prompt()
                .user(message)
                .call()
                .chatResponse();

        Usage usage = response.getMetadata().getUsage();

        String content = response
                .getResult()
                .getOutput()
                .getText();

        return new ChatResult(
                content,
                usage.getPromptTokens(),
                usage.getCompletionTokens(),
                usage.getTotalTokens()
        );
    }

}
