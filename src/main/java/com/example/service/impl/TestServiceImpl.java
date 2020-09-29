package com.example.service.impl;

import com.example.service.TestService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@Service
public class TestServiceImpl implements TestService {

    @Resource
    private StringRedisTemplate redis;

    @Resource
    private RedisLockRegistry redisLockRegistry;

    /**
     * 功能描述: 测试
     */
    @Override
    public String deductStock() {

        String key = "lockKey";
        String value = UUID.randomUUID().toString();
        try {
            boolean result = redis.opsForValue().setIfAbsent(key, value, 10, TimeUnit.SECONDS);
            if (!result) {
                System.out.println("扣减失败, error");
                return "error";
            }
            int stock = Integer.parseInt(redis.opsForValue().get("stock"));
            if (stock > 0) {
                int realStock = stock - 1;
                redis.opsForValue().set("stock", realStock + "");
                System.out.println("扣减成功, 剩余库存: " + realStock);
            } else {
                System.out.println("扣减失败, 库存不足！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (value.equals(redis.opsForValue().get(key))) {
                redis.delete(key);
            }
        }
        return "OK";
    }

    @Override
    public String deductStock1() {
        Lock lock = redisLockRegistry.obtain("lock");
//        boolean b1 = lock.tryLock(3, TimeUnit.SECONDS);
//        log.info("b1 is : {}", b1);
//
//        TimeUnit.SECONDS.sleep(5);
//
//        boolean b2 = lock.tryLock(3, TimeUnit.SECONDS);
//        log.info("b2 is : {}", b2);

        try {
            lock.tryLock(3, TimeUnit.SECONDS);
            TimeUnit.SECONDS.sleep(5);
            int stock = Integer.parseInt(redis.opsForValue().get("stock"));
            if (stock > 0) {
                int realStock = stock - 1;
                redis.opsForValue().set("stock", realStock + "");
                System.out.println("扣减成功, 剩余库存: " + realStock);
            } else {
                System.out.println("扣减失败, 库存不足！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return "OK";
    }
}
