package com.example.ifdemo;

import com.example.ifdemo.stereotype.OrderType;
import com.example.ifdemo.vo.OrderReq;
import org.springframework.stereotype.Service;

@Service
@OrderType(source = "pc", payType = "WeChat")
public class PCWeChatOrderHandler implements OrderHandler {

    @Override
    public void handle(OrderReq req) {
        System.out.println("处理PC端微信支付订单...");
    }

}
