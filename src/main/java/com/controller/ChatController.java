package com.controller;


import com.model.ChatMessage;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Map;

@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = "http://localhost:4200")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(@NotNull ChatClient.Builder chatClientBuilder)
    {
        this.chatClient = chatClientBuilder.build();
    }

    @PostMapping
    public Map<String, String> generation(@RequestBody ChatMessage message)
    {
        String reply = this.chatClient.prompt()
                .user(message.message())
                .call()
                .content();

        return Map.of("reply", reply);
    }

}
