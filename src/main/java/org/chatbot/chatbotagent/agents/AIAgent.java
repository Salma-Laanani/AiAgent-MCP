package org.chatbot.chatbotagent.agents;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

import java.util.Arrays;

@Component
public class AIAgent {
    private ChatClient chatClient;
    public AIAgent(ChatClient.Builder builder, ChatMemory chatMemory, ToolCallbackProvider toolCallbackProvider) {

        Arrays.stream(toolCallbackProvider.getToolCallbacks()).forEach(tool->{
            System.out.println("---------------------");
            System.out.println(tool.getToolDefinition());
            System.out.println("---------------------");
                }

                );
        this.chatClient = builder
                .defaultSystem("""
                        vous etes un assistant qui se charge de repondre aux questions des utilisateurs en fonction du contexte fourni.
                        si aucun contexte fpourni repond avec je ne sais pas""")
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .defaultToolCallbacks(toolCallbackProvider)
                .build();
    }


    public String askAgent(String message) {
        return chatClient.prompt()
                .user(message)
                .call().content();
    }
}
