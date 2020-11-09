package com.example.ifdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.ifdemo.service.OrderService;
import com.example.ifdemo.vo.OrderReq;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/order")
    public JSONObject order(@RequestBody OrderReq req) {

        orderService.order(req);
        JSONObject json = new JSONObject();
        json.put("code", 200);
        json.put("msg", "success");
        return json;
    }

}
