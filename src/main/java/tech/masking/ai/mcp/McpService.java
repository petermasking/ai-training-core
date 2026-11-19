
package tech.masking.ai.mcp;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

@Service
public class McpService
{
    private final ChatClient chatClient;
    private final ToolCallbackProvider mcpTools;

    public McpService(ChatClient.Builder builder, ToolCallbackProvider mcpTools)
    {
        this.chatClient = builder.build();
        this.mcpTools = mcpTools;
    }

    public String ask(String question)
    {
        return chatClient
            .prompt()
            .user(question)
            .tools(mcpTools)
            .call()
            .content();
    }
}
