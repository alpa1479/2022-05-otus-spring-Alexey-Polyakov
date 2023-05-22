package ru.otus.spring.hw14.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.CacheManager;

import static java.time.Duration.ofMinutes;
import static javax.cache.Caching.getCachingProvider;
import static org.ehcache.config.builders.CacheConfigurationBuilder.newCacheConfigurationBuilder;
import static org.ehcache.config.builders.ExpiryPolicyBuilder.timeToIdleExpiration;
import static org.ehcache.config.builders.ResourcePoolsBuilder.newResourcePoolsBuilder;
import static org.ehcache.config.units.MemoryUnit.MB;
import static org.ehcache.jsr107.Eh107Configuration.fromEhcacheCacheConfiguration;
import static ru.otus.spring.hw14.constants.JobNames.LIBRARY_DB_FROM_POSTGRES_TO_MONGO_MIGRATION_JOB;

@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        var resourcePools = newResourcePoolsBuilder()
                .offheap(15, MB)
                .build();

        var configuration = newCacheConfigurationBuilder(String.class, Object.class, resourcePools)
                .withExpiry(timeToIdleExpiration(ofMinutes(10)))
                .build();

        var cacheManager = getCachingProvider().getCacheManager();
        cacheManager.createCache(
                LIBRARY_DB_FROM_POSTGRES_TO_MONGO_MIGRATION_JOB.getName(),
                fromEhcacheCacheConfiguration(configuration)
        );
        return cacheManager;
    }
}
