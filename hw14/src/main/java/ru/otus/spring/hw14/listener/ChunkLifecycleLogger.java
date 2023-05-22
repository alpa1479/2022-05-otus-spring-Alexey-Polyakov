package ru.otus.spring.hw14.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.lang.NonNull;

@Slf4j
@RequiredArgsConstructor
public class ChunkLifecycleLogger implements ChunkListener {

    private final String stepName;

    public void beforeChunk(@NonNull ChunkContext chunkContext) {
        log.info("Started processing chunk for a step {}", stepName);
    }

    public void afterChunk(@NonNull ChunkContext chunkContext) {
        log.info("Completed processing chunk for a step {}", stepName);
    }

    public void afterChunkError(@NonNull ChunkContext chunkContext) {
        log.info("Error processing chunk for step {}", stepName);
    }
}
