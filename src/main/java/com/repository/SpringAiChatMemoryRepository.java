package com.repository;

import com.model.SpringAiChatMemory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringAiChatMemoryRepository   extends JpaRepository<SpringAiChatMemory, String> {

    List<SpringAiChatMemory>
    findByConversationIdOrderByTimestamp(String conversationId);

}