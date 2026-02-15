package com.service;

import com.model.Conversation;
import com.repository.ConversationRepository;
import com.repository.SpringAiChatMemoryRepository;
import com.dto.ConversationDTO;
import com.model.SpringAiChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ConversationService {

    @Autowired
    private SpringAiChatMemoryRepository repository;

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private ChatHistoryService chatHistoryService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<String> listConversationIds() {

        return repository.findAll()
                .stream()
                .map(SpringAiChatMemory::getConversationId)
                .distinct()
                .toList();

    }

    public List<Map<String, String>> listHistory(String conversationId) {

        return chatHistoryService
                .getHistory(conversationId)
                .stream()
                .map(msg -> Map.of(
                        "role", msg.getMessageType().name().toLowerCase(),
                        "content", msg.getText()
                ))
                .toList();

    }

    private String generateTitleFromHistory(String id) {

        var history = chatHistoryService.getHistory(id);

        if (history.isEmpty()) {
            return "Nova conversa";
        }

        var firstUserMessage = history.stream()
                .filter(m -> m.getMessageType().name().equals("USER"))
                .findFirst();

        return firstUserMessage
                .map(m -> m.getText().substring(0, Math.min(30, m.getText().length())))
                .orElse("Nova conversa");

    }

    public void ensureExists(String conversationId) {

        if (!conversationRepository.existsById(conversationId)) {

            conversationRepository.save(
                    new Conversation(
                            conversationId,
                            "Nova conversa"
                    )
            );

        }

    }

    public void renameConversation(String id, String title) {

        var conversation =
                conversationRepository.findById(id)
                        .orElseThrow();

        conversation.setTitle(title);

        conversationRepository.save(conversation);

    }

    public List<ConversationDTO> listConversations() {

        return conversationRepository.findAll()
                .stream()
                .map(c -> new ConversationDTO(
                        c.getId(),
                        c.getTitle()
                ))
                .toList();

    }

    @Transactional
    public void deleteConversation(String id) {

        if (!conversationRepository.existsById(id)) {
            throw new RuntimeException("Conversation não existe");
        }

        jdbcTemplate.update(
                "DELETE FROM spring_ai_chat_memory WHERE conversation_id = ?",
                id
        );

        conversationRepository.deleteById(id);

    }

}
