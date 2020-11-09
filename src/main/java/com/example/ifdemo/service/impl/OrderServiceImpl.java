package com.example.ifdemo.service.impl;

import com.example.ifdemo.OrderHandler;
import com.example.ifdemo.service.OrderService;
import com.example.ifdemo.stereotype.OrderType;
import com.example.ifdemo.stereotype.OrderTypeImpl;
import com.example.ifdemo.vo.OrderReq;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

//    private Map<String, OrderHandler> orderHandleMap;
//
//    @Resource
//    public void setOrderHandleMap(List<OrderHandler> orderHandlers) {
//        System.out.println("---------------- 初始化 -----------------");
//        for (OrderHandler orderHandler : orderHandlers) {
//            System.out.println("--> " + orderHandler.getClass());
//        }
//
//        // 注入各种类型的订单处理类
//        orderHandleMap = orderHandlers.stream().collect(
//                Collectors.toMap(orderHandler -> AnnotationUtils.findAnnotation(orderHandler.getClass(), OrderType.class).source(),
//                        v -> v, (v1, v2) -> v1));
//    }

    private Map<OrderType, OrderHandler> orderHandleMap;

    @Resource
    public void setOrderHandleMap(List<OrderHandler> orderHandlers) {
        System.out.println("---------------- 初始化 -----------------");
        for (OrderHandler orderHandler : orderHandlers) {
            System.out.println("--> " + orderHandler.getClass());
        }

        // 注入各种类型的订单处理类
        orderHandleMap = orderHandlers.stream().collect(
                Collectors.toMap(orderHandler -> AnnotationUtils.findAnnotation(orderHandler.getClass(), OrderType.class),
                        v -> v, (v1, v2) -> v1));

    }

    @Override
    public void order(OrderReq req) {

        /*
        1. 使用 if ... else

        if ("pc".equals(req.getSource())) {
            System.out.println("--> 处理pc端订单...");
        } else if ("mobile".equals(req.getSource())) {
            System.out.println("--> 处理移动端订单...");
        } else {
            System.out.println("--> 处理其它来源订单...");
        }
        */

        /*
        2. 使用 策略模式
           根据订单来源，处理不同的订单

        // ...一些前置处理
        System.out.println("---------------- 1 -----------------");
        // 通过订单来源确定对应的handler
        OrderHandler orderHandler = orderHandleMap.get(order.getSource());
        System.out.println("---------------- 2 -----------------");
        orderHandler.handle(order);
        System.out.println("---------------- 4 -----------------");
        // ...一些后置处理
        */

        // ...一些前置处理
        // 通过订单来源确定对应的handler
        OrderType orderType = new OrderTypeImpl(req.getSource(), req.getPayType());
        OrderHandler orderHandler = orderHandleMap.get(orderType);
        orderHandler.handle(req);
        // ...一些后置处理
    }

}
