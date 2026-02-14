package com.service;

import com.SpringAiChatMemoryRepository;
import com.model.SpringAiChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationService {

    @Autowired
    private SpringAiChatMemoryRepository repository;

    public List<String> listConversationIds() {

        return repository.findAll()
                .stream()
                .map(SpringAiChatMemory::getConversationId)
                .distinct()
                .toList();

    }
}
