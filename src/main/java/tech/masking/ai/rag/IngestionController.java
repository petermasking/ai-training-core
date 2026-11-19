
package tech.masking.ai.rag;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rag/ingest")
public class IngestionController
{
    private final IngestionService ingestionService;

    public IngestionController(IngestionService ingestionService)
    {
        this.ingestionService = ingestionService;
    }

    @PostMapping()
    public ResponseEntity<String> ingest() throws IOException
    {
        ingestionService.ingest();

        return ResponseEntity.ok(
            "Document ingestion completed."
        );
    }
}
