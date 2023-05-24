package ru.otus.spring.hw14.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw14.services.JobCacheService;
import ru.otus.spring.hw14.services.JobCleanUpService;

@Service
@RequiredArgsConstructor
public class JobCleanUpServiceImpl implements JobCleanUpService {

    private final JobCacheService jobCacheService;

    @Override
    public void cleanUp() {
        jobCacheService.clear();
    }
}
