package com.example.ifdemo;

import com.alibaba.fastjson.JSON;
import com.example.ifdemo.vo.OrderReq;

//@Service
//@OrderType(source = "pc")
public class PCOrderHandler implements OrderHandler {

    @Override
    public void handle(OrderReq req) {
        System.out.println("Req:" + JSON.toJSONString(req));
        System.out.println("处理PC端订单...");
    }

}