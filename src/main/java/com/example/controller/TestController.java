package com.example.controller;

import com.example.redis.RedisService;
import com.example.service.BaseService;
import com.example.service.TestService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class TestController {

    @Resource
    private TestService testService;

    @Resource
    private BaseService baseervice;

    @Resource
    private RedisService redisService;

    @Resource
    private StringRedisTemplate redis;

    @GetMapping("/deductStock")
    public String deductStock() {

        redis.opsForValue().set("stock", "50");

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //testService.deductStock();
                testService.deductStock1();
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(25);
        for (int i = 0; i < 20000; i++) {
            executorService.submit(runnable);
        }
        executorService.shutdown();
        System.out.println("redis:" + redis.opsForValue().get("stock"));

        return "ok";
    }

    @GetMapping("/value")
    public String value() {

        redisService.delete("9527");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                baseervice.getValue("9527");
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(25);
        for (int i = 0; i < 50; i++) {
            executorService.submit(runnable);
        }
        try {
            Thread.sleep(3000);
        } catch (Exception e){

        }
        for (int i = 0; i < 50; i++) {
            executorService.submit(runnable);
        }
        executorService.shutdown();
        return "ok";
    }

}
