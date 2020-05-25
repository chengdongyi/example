package com.example.java8;

import com.example.domain.Employee;
import org.junit.Test;

import java.util.function.Function;

/**
 * Function<T, R> : 函数型接口
 */
public class FunctionTest {

    @Test
    public void test1(){
        Function<Integer, Integer> function1 = x -> x * 2;
        int num = function1.apply(4);
        System.out.println(num);// 8

        Function<Integer, String> function2 = x -> x * 2 + "dd";
        String strr = function2.apply(4);
        System.out.println(strr);// 8dd

        Function<String, String> strFunction1 = str -> new String(str);
        System.out.println(strFunction1.apply("aa"));//aa

        Function<String, String> strFunction2 = String::new;
        System.out.println(strFunction2.apply("bb"));//bb

        Function<String, Employee> objFunction1 = name -> {
            Employee emp = new Employee();
            emp.setName(name);
            return emp;
        };
        Employee emp = objFunction1.apply("王五");
        System.out.println(emp.getName());

    }

    @Test
    public void test2(){
        String newStr = strHandler("\t\t\t Java、C++、Python   ", str -> str.trim());
        System.out.println(newStr);

        String subStr = strHandler("Java、C++、Python", str -> str.substring(1, 4));
        System.out.println(subStr);
    }

    //需求：用于处理字符串
    public String strHandler(String str, Function<String, String> fun){
        return fun.apply(str);
    }

}
