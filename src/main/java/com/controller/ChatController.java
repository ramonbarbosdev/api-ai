package com.controller;


import com.dto.ConversationDTO;
import com.model.ChatResult;
import com.model.RenameConversationRequest;
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
@CrossOrigin(origins = {
        "http://localhost:4200",
        "https://memorixai.ramoncode.com.br",
})

public class ChatController {

    private final ChatClient chatClient;

    @Autowired(required = true)
    private MemoryChatService memoryChatService;


    @Autowired
    private ConversationService conversationService;

    public ChatController(@NotNull ChatClient.Builder chatClientBuilder)
    {
        this.chatClient = chatClientBuilder.build();
    }


    @PostMapping("/send")
    public ChatResult sendMessage(@RequestBody ChatMessage message) {

        conversationService.ensureExists(
                message.conversationId()
        );

        return memoryChatService.generation(
                message.conversationId(),
                message.message()
        );
    }

    @PostMapping("/rename")
    public void renameConversation(
            @RequestBody RenameConversationRequest req
    ) {

        conversationService.renameConversation(
                req.conversationId(),
                req.title()
        );

    }


    // -------------------------------------------------------
    // GET CONVERSATION HISTORY
    // -------------------------------------------------------

    @GetMapping("/history/{conversationId}")
    public List<Map<String, String>> history(
            @PathVariable String conversationId
    ) {
        return conversationService.listHistory(conversationId);
    }


    @GetMapping("/conversations")
    public List<ConversationDTO> conversations() {

        return conversationService.listConversations();

    }


}
