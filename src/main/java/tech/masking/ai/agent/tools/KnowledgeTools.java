
package tech.masking.ai.agent.tools;

import org.springframework.stereotype.Component;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;

@Component
public class KnowledgeTools
{
    private final VectorStore vectorStore;

    public KnowledgeTools(VectorStore vectorStore)
    {
        this.vectorStore = vectorStore;
    }

    @Tool(
        name = "searchKnowledgeBase",
        description = """
        Search internal policies,
        procedures and documentation.
        Use this tool for company-specific
        questions and always cite the sources.
        """)
    public List<Document> searchKnowledgeBase(
        @ToolParam(description = "Search query", required = true) String query
    ) {
        return vectorStore.similaritySearch(
            SearchRequest.builder()
                .query(query)
                .topK(4)
                .similarityThreshold(0.5)
                .build()
        );
    }
}