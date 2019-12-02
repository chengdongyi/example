package com.example.redis;

import java.util.concurrent.TimeUnit;

public interface RedisService {

    String get(String key);

    void set(String key, String value);

    void set(String key, String value, long timeout, TimeUnit timeUnit);

    void delete(String key);

    <T> T get(final String key, final Class<T> clz);

    <T> void set(String key, T data);

    <T> void set(String key, T data, long timeout, TimeUnit timeUnit);

}
