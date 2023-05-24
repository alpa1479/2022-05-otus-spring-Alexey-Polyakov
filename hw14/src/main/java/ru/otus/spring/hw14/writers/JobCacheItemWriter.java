package ru.otus.spring.hw14.writers;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import ru.otus.spring.hw14.services.JobCacheService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;

@RequiredArgsConstructor
public class JobCacheItemWriter<T> implements ItemWriter<T> {

    private final Class<T> type;
    private final JobCacheService jobCacheService;

    @Override
    public void write(List<? extends T> items) {
        List<? super T> cachedItems = jobCacheService.get(type.getSimpleName());
        if (isEmpty(cachedItems)) {
            cachedItems = new ArrayList<>(items);
        } else {
            cachedItems.addAll(items);
        }
        jobCacheService.put(type.getSimpleName(), cachedItems);
    }
}
