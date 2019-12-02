package com.example.service.impl;

import com.example.redis.RedisService;
import com.example.service.BaseService;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BaseServiceImpl implements BaseService {

    @Resource
    private RedisService redisService;

    @Override
    public String getValue(String key) {

        /*
         * 在高并发情况下会出现缓存穿透问题, 原因是当并发请求同时从redis中获取数据时,
         * 这个如果redis中没有数据, 这些并发请求,
         * 会同时获取不到,因此会同同时访问访问数据库,并同时向缓存中存入数据
         *
         * 解决方案：
         *   使用双重检查+局部同步锁
         *   注意：尽量不要使用方法同步
         */
        String value = redisService.get(key);
        if (value == null) {
            synchronized (this) {
                value = redisService.get(key);
                if (value == null) {
                    value = "123456";
                    redisService.set(key, value);
                    System.out.println("(1) 从数据库中获取数据...");
                } else {
                    System.out.println("(2) 从Reids中获取数据...");
                }
            }
        } else {
            System.out.println("(3) 从Reids中获取数据...");
        }
        return value;
    }

}
