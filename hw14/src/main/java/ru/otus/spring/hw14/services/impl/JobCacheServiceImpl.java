package ru.otus.spring.hw14.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw14.constants.JobNames;
import ru.otus.spring.hw14.services.JobCacheService;

import javax.cache.Cache;
import javax.cache.CacheManager;

@Service
@RequiredArgsConstructor
public class JobCacheServiceImpl implements JobCacheService {

    private final CacheManager cacheManager;

    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) getCache().get(key);
    }

    @Override
    public <T> JobCacheService put(String key, T value) {
        getCache().put(key, value);
        return this;
    }

    @Override
    public void clear() {
        getCache().clear();
    }

    private <T> Cache<String, T> getCache() {
        return cacheManager.getCache(JobNames.LIBRARY_DB_FROM_POSTGRES_TO_MONGO_MIGRATION_JOB.getName());
    }
}
