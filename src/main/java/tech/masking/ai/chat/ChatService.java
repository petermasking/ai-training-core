
package tech.masking.ai.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class ChatService
{
    private final ChatClient chatClient;

    public ChatService(ChatClient.Builder builder)
    {
        this.chatClient = builder.build();
    }

    public String ask(String question)
    {
        return chatClient
            .prompt()
            .user(question)
            .call()
            .content();
    }

    public Flux<String> stream(String message)
    {
        return chatClient
            .prompt()
            .user(message)
            .stream()
            .content();
    }
}
