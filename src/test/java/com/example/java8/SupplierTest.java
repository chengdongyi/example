package com.example.java8;

import com.example.domain.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Supplier<T> : 供给型接口
 */
public class SupplierTest {

    @Test
    public void test1(){

        Supplier<String> supplierStr = String::new;
        System.out.println(supplierStr.get());

        // --------------------------------------------

        Supplier<Employee> supplier = Employee::new;
        Employee emp = supplier.get();
        emp.setName("张三");
        System.out.println(emp.getName());
    }

    @Test
    public void test2(){

        List<Integer> numList = getNumList(10, () -> (int)(Math.random() * 100));

        for (Integer num : numList) {
            System.out.println(num);
        }
    }

    /**
     * 需求：产生指定个数的整数，并放入集合中
     */
    public List<Integer> getNumList(int num, Supplier<Integer> supplier){
        List<Integer> list = new ArrayList<>(num);

        for (int i = 0; i < num; i++) {
            Integer n = supplier.get();
            list.add(n);
        }

        return list;
    }

    @Test
    public void test3(){

        Supplier<Integer> supplier = () -> (int)(Math.random() * 100);
        List<Integer> lists = new ArrayList<>(10);

        for (int i = 0; i < 10; i++) {
            Integer n = supplier.get();
            lists.add(n);
        }

        for (Integer num : lists) {
            System.out.println(num);
        }
    }

}
