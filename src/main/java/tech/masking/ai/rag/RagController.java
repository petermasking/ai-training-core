
package tech.masking.ai.rag;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rag")
public class RagController
{
    private final RagService ragService;

    public RagController(RagService ragService)
    {
        this.ragService = ragService;
    }

    @PostMapping()
    public AnswerResponse ask(@RequestBody QuestionRequest request)
    {
        String answer = ragService.ask(request.question());

        return new AnswerResponse(answer);
    }

    public record QuestionRequest(String question) {}

    public record AnswerResponse(String answer) {}
}
