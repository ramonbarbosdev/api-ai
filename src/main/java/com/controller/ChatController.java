package com.controller;


import com.MemoryChatService;
import com.model.ChatMessage;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Map;

@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = "http://localhost:4200")
public class ChatController {

    private final ChatClient chatClient;

    @Autowired(required = true)
    private MemoryChatService memoryChatService;

    public ChatController(@NotNull ChatClient.Builder chatClientBuilder)
    {
        this.chatClient = chatClientBuilder.build();
    }

    @PostMapping
    public Map<String, String> generation(@RequestBody ChatMessage message)
    {
        String reply = memoryChatService.generation(message.message());

        return Map.of("reply", reply);
    }

}
