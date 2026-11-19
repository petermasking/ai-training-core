
package tech.masking.ai.mcp;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mcp")
public class McpController
{
    private final McpService mcpService;

    public McpController(McpService mcpService)
    {
        this.mcpService = mcpService;
    }

    @PostMapping()
    public AnswerResponse ask(@RequestBody QuestionRequest request)
    {
        String answer = mcpService.ask(request.question());

        return new AnswerResponse(answer);
    }

    public record QuestionRequest(String question) {}

    public record AnswerResponse(String answer) {}
}
