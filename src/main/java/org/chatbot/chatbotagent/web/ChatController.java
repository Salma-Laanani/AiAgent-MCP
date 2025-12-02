package org.chatbot.chatbotagent.web;

import org.chatbot.chatbotagent.agents.AIAgent;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {
    private AIAgent aiAgent;
    public ChatController(AIAgent aiAgent) {
        this.aiAgent = aiAgent;
    }

    @GetMapping("/chat")
    public String Chat(@RequestParam(name = "message") String message) {
        return aiAgent.askAgent(message);
    }
}
