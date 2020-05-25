package com.example.java8;

import com.example.domain.Employee;
import org.junit.Test;

import java.util.function.Consumer;

/**
 *  Java8 内置的四大核心函数式接口
 *  Consumer<T> : 消费型接口
 *      void accept(T t);
 *
 *  Supplier<T> : 供给型接口
 *      T get();
 *
 *  Function<T, R> : 函数型接口
 *      R apply(T t);
 *
 *  Predicate<T> : 断言型接口
 *      boolean test(T t);
 */
public class ConsumerTest {

    @Test
    public void test1() {
        Employee emp = new Employee();
        //接受一个参数
        Consumer<Employee> consumer = employee -> employee.setName("张三");
        consumer.accept(emp);
        System.out.println(emp.getName());
    }

    @Test
    public void test2(){
        Consumer<Double> consumer = money -> System.out.println("KFC，每次消费：" + money + "元");
        consumer.accept(20.0);
    }

}
