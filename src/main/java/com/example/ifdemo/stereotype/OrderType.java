package com.example.ifdemo.stereotype;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OrderType {

    String source() default "pc";

    String payType();

}
