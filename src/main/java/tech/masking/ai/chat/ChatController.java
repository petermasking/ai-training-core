
package tech.masking.ai.chat;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/chat")
public class ChatController
{
    private final ChatService chatService;

    public ChatController(ChatService chatService)
    {
        this.chatService = chatService;
    }

    @PostMapping()
    public AnswerResponse ask(@RequestBody QuestionRequest request)
    {
        String answer = chatService.ask(request.question());

        return new AnswerResponse(answer);
    }

    @PostMapping(
        value = "/stream",
        produces = MediaType.TEXT_EVENT_STREAM_VALUE
    )
    public Flux<String> streamChat(@RequestBody QuestionRequest request)
    {
        return chatService.stream(request.question());
    }

    public record QuestionRequest(String question) {}

    public record AnswerResponse(String answer) {}
}
