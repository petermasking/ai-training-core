
package tech.masking.ai.rag;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

@Service
public class IngestionService
{
    private static final Logger log =
        LoggerFactory.getLogger(IngestionService.class);

    private final VectorStore vectorStore;

    public IngestionService(VectorStore vectorStore)
    {
        this.vectorStore = vectorStore;
    }

    public void ingest() throws IOException
    {
        var directory = Paths.get("./documents");

        var splitter = TokenTextSplitter
            .builder()
            .withChunkSize(800) // maximum aantal karakters (hoe kleiner hoe preciezer)
            .withMinChunkSizeChars(100)
            .build();

        try (var files = Files.walk(directory))
        {
            files
                .filter(path -> path.toString().toLowerCase().endsWith(".pdf"))
                .forEach(path ->
                {
                    log.info("Ingesting {}", path);

                    var resource = new FileSystemResource(path);
                    var reader = new PagePdfDocumentReader(resource);

                    var pages = reader.read();
                    var chunks = splitter.split(pages);

                    chunks.forEach(chunk ->
                        chunk.getMetadata().put("source", path.getFileName().toString())
                    );

                    vectorStore.add(chunks);
                });
        }
    }
}
