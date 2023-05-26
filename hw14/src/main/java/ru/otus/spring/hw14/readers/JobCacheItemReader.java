package ru.otus.spring.hw14.readers;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.batch.item.ItemReader;
import ru.otus.spring.hw14.services.JobCacheService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class JobCacheItemReader<T> implements ItemReader<T> {

    private final Class<T> type;
    private final JobCacheService jobCacheService;
    private List<T> items;

    @Override
    public T read() {
        var key = type.getSimpleName();
        if (items == null) { // for init case
            items = jobCacheService.get(key);
        }
        return Optional.ofNullable(items)
                .filter(CollectionUtils::isNotEmpty)
                .map(items -> items.remove(0))
                .orElse(null);
    }
}
