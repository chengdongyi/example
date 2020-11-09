package com.example.ifdemo;

import com.alibaba.fastjson.JSON;
import com.example.ifdemo.vo.OrderReq;

//@Service
//@OrderType(source = "mobile")
public class MobileOrderHandler implements OrderHandler {

    @Override
    public void handle(OrderReq req) {
        System.out.println("Req:" + JSON.toJSONString(req));
        System.out.println("处理移动端订单...");
    }

}