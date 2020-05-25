package com.example.demo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @description: 遍历 HashMap 的 5 种最佳方式
 * @author chengdongyi
 * @date 2020/4/3 20:10
 */
public class HashMapDemo {

    public static void main(String[] args) {

        Map<Integer, String> maps = new HashMap<>();
        maps.put(1, "张三");
        maps.put(2, "李四");
        maps.put(3, "王五");
        maps.put(4, "赵六");
        maps.put(5, "田七");

        // 1. 使用 Iterator 遍历 HashMap EntrySet
        System.out.println("1. 使用 Iterator 遍历 HashMap EntrySet");
        Iterator<Map.Entry<Integer, String>> iterator = maps.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            System.out.println("key: " + entry.getKey() + ", value: " + entry.getValue());
        }

        // 2. 使用 Iterator 遍历 HashMap KeySet
        System.out.println("\r\n2. 使用 Iterator 遍历 HashMap KeySet");
        Iterator<Integer> iterators = maps.keySet().iterator();
        while (iterators.hasNext()) {
            Integer key = iterators.next();
            System.out.println("key: " + key + ", value: " + maps.get(key));
        }

        // 3. 使用 For-each 循环遍历 HashMap
        System.out.println("\r\n3. 使用 For-each 循环遍历 HashMap");
        for (Map.Entry<Integer, String> entry : maps.entrySet()) {
            System.out.println("key: " + entry.getKey() + ", value: " + entry.getValue());
        }

        // 4. 使用 Lambda 表达式遍历 HashMap
        System.out.println("\r\n4. 使用 Lambda 表达式遍历 HashMap");
        maps.forEach((key, value) -> {
            System.out.println("key: " + key + ", value: " + value);
        });

        // 5. 使用 Stream API 遍历 HashMap
        System.out.println("\r\n5. 使用 Stream API 遍历 HashMap");
        maps.entrySet().stream().forEach(map -> {
            System.out.println("key: " + map.getKey() + ", value: " + map.getValue());
        });

    }

}
