package com.example.ifdemo;

import com.example.ifdemo.stereotype.OrderType;
import com.example.ifdemo.vo.OrderReq;
import org.springframework.stereotype.Service;

@Service
@OrderType(source = "pc", payType = "AliPay")
public class PCAliPayOrderHandler implements OrderHandler {

    @Override
    public void handle(OrderReq req) {
        System.out.println("处理PC端支付宝支付订单...");
    }

}

