package com.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "spring_ai_chat_memory")
public class SpringAiChatMemory {

    @Id
    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "conversation_id")
    private String conversationId;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "type")
    private String type;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getConversationId() {
        return conversationId;
    }

    public String getContent() {
        return content;
    }

    public String getType() {
        return type;
    }

}
