package com.example.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Predicate<T> 断言型接口
 */
public class PredicateTest {


    @Test
    public void test1() {

        List<Integer> lists = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);

        // 1. 过滤 num > 5
        Predicate<Integer> p1 = i -> i > 5;

        // 2. 过滤 num < 20
        Predicate<Integer> p2 = i -> i < 20;

        // 3. 过滤 所有的奇数
        Predicate<Integer> p3 = i -> i % 2 == 0;
        List list = lists.stream().filter(p1.and(p2).and(p3)).collect(Collectors.toList());

        // 结果: [6, 8, 10, 12, 14]
        System.out.println(list.toString());

        // negate() : 取反
        list = lists.stream().filter(p1.and(p2).and(p3.negate())).collect(Collectors.toList());

        // 结果: [7, 9, 11, 13, 15]
        System.out.println(list.toString());
    }


    @Test
    public void test4(){
        List<String> list = Arrays.asList("Hello", "atguigu", "Lambda", "www", "ok");
        List<String> strList = filterStr(list, (s) -> s.length() > 3);

        for (String str : strList) {
            System.out.println(str);
        }
    }

    //需求：将满足条件的字符串，放入集合中
    public List<String> filterStr(List<String> list, Predicate<String> pre){
        List<String> strList = new ArrayList<>();

        for (String str : list) {
            if(pre.test(str)){
                strList.add(str);
            }
        }

        return strList;
    }

}
