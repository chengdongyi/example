package com.example.ifdemo.vo;

import lombok.Data;

@Data
public class OrderReq {

    /**
     * 订单来源
     */
    private String source;

    /**
     * 支付类型
     */
    private String payType;

    /**
     * 订单编号
     */
    private String orderId;

    /**
     * 订单金额
     */
    private Long amount;

}
