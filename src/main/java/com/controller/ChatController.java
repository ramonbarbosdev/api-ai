package com.controller;


import com.model.ChatResult;
import com.service.ChatHistoryService;
import com.service.ConversationService;
import com.service.MemoryChatService;
import com.model.ChatMessage;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = "http://localhost:4200")
public class ChatController {

    private final ChatClient chatClient;

    @Autowired(required = true)
    private MemoryChatService memoryChatService;

    @Autowired
    private ChatHistoryService chatHistoryService;

    @Autowired
    private ConversationService conversationService;

    public ChatController(@NotNull ChatClient.Builder chatClientBuilder)
    {
        this.chatClient = chatClientBuilder.build();
    }

    @PostMapping
    public  ChatResult generation(@RequestBody ChatMessage message)
    {
        ChatResult result = memoryChatService.generation(message.conversationId(),message.message());

        return result;
    }

    @GetMapping("/history/{conversationId}")
    public List<Map<String, String>> history(
            @PathVariable String conversationId
    ) {

        return chatHistoryService
                .getHistory(conversationId)
                .stream()
                .map(msg -> Map.of(
                        "type", msg.getMessageType().name(),
                        "content", msg.getText()
                ))
                .toList();

    }

    @GetMapping("/conversations")
    public List<String> conversations() {
        return conversationService.listConversationIds();
    }


}
