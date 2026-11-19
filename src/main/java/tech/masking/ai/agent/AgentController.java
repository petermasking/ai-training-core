
package tech.masking.ai.agent;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agent")
public class AgentController
{
    private final AgentService agentService;

    public AgentController(AgentService agentService)
    {
        this.agentService = agentService;
    }

    @PostMapping()
    public AnswerResponse ask(@RequestBody QuestionRequest request)
    {
        String answer = agentService.ask(request.question());

        return new AnswerResponse(answer);
    }

    public record QuestionRequest(String question) {}

    public record AnswerResponse(String answer) {}
}
