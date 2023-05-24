package ru.otus.spring.hw14.services;

public interface JobCacheService {

    <T> T get(String key);

    <T> JobCacheService put(String key, T value);

    void clear();
}
