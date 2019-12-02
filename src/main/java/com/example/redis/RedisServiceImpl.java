package com.example.redis;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public String get(String key) {
        if(StringUtils.isBlank(key)) {
            return null;
        }
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public void set(String key, String value) {
        if(StringUtils.isBlank(key) || value == null) {
            return;
        }
        stringRedisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, String value, long timeout, TimeUnit timeUnit) {
        if(StringUtils.isBlank(key) || value == null) {
            return;
        }
        stringRedisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }


    @Override
    public void delete(String key) {
        if(StringUtils.isBlank(key)) {
            return;
        }
        stringRedisTemplate.delete(key);
    }

    @Override
    public  <T> T get(String key, Class<T> clz) {

        if(StringUtils.isBlank(key)) {
            return null;
        }
        return (T) redisTemplate.opsForValue().get(key);
    }

    @Override
    public <T> void set(String key, T data) {

        if(StringUtils.isBlank(key) || data == null) {
            return;
        }
        redisTemplate.opsForValue().set(key, data);

    }

    @Override
    public <T> void set(String key, T data, long timeout, TimeUnit timeUnit) {

        if(StringUtils.isBlank(key) || data == null) {
            return;
        }
        redisTemplate.opsForValue().set(key, data, timeout, timeUnit);

    }
}
