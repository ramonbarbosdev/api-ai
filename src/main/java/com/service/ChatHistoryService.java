package com.service;


import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatHistoryService {

    private final ChatMemory chatMemory;

    public ChatHistoryService(ChatMemory chatMemory) {
        this.chatMemory = chatMemory;
    }

    public List<Message> getHistory(String conversationId) {
        return chatMemory.get(conversationId);
    }

}