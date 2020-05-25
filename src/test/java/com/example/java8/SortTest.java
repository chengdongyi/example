package com.example.java8;

import java.util.*;

import com.example.domain.Employee;
import org.junit.Test;

/**
 * 排序
 */
public class SortTest {

    private List<Employee> employees = Arrays.asList(
            new Employee(10001, "李四", 19, 9999.99),
            new Employee(10001, "张三", 18, 8888.88),
            new Employee(10002, "王五", 20, 8888.88),
            new Employee(10003, "田七", 22, 9999.99),
            new Employee(10002, "赵六", 21, 9999.99));

    @Test
    public void testList() {
        //对数字进行排序
        List<Integer> nums = Arrays.asList(3, 1, 5, 2, 9, 8, 4, 10, 6, 7);
        nums.sort(Comparator.reverseOrder());
        System.err.println("倒序:" + nums);

        nums.sort(Comparator.naturalOrder());
        System.err.println("正序:" + nums);

        List<String> lists = Arrays.asList("123acb", "123abc", "acbd", "abcd", "ddee", "ddaa");
        lists.sort(Comparator.reverseOrder());
        System.err.println("倒序:" + lists);

        lists.sort(Comparator.naturalOrder());
        System.err.println("正序:" + lists);

        // java8 Stream.sorted() 自然排序
        lists.stream().sorted().forEach(System.err::println);
    }

    @Test
    public void testMap() {

        // Map 自然排序
        Map<String, Object> maps = new HashMap<>();
        maps.put("abcd", 3);
        maps.put("123acb", 2);
        maps.put("ddee", 6);
        maps.put("123abc", 1);
        maps.put("ddaa", 5);
        maps.put("acbd", 4);

        maps.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(s -> System.err.println(s.getKey() + " : " + s.getValue()));

        Map<String, Object> result = new LinkedHashMap<>();
        maps.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(map -> result.put(map.getKey(), map.getValue()));
    }

    @Test
    public void testBeans1() {
        // 1. 按照 Age 排序
        employees.sort(Comparator.comparing(Employee::getAge));
        employees.forEach(System.err::println);
    }

    @Test
    public void testBeans2() {
        Comparator<Employee> comparator = (emp1, emp2)-> String.valueOf(emp1.getAge()).compareTo(String.valueOf(emp2.getAge()));
        employees.sort(comparator);
        employees.forEach(System.err::println);
        System.out.println("---------------------------------------");
        employees.sort(comparator.reversed());
        employees.forEach(System.err::println);
    }

    @Test
    public void testBeans3() {
        employees.sort(Comparator.comparing(Employee::getId).thenComparing(Employee::getSalary));
        employees.forEach(System.err::println);
    }

    @Test
    public void testBeans4() {

        Comparator<Employee> comparator1 = (emp1, emp2)-> String.valueOf(emp1.getId()).compareTo(String.valueOf(emp2.getId()));
        Comparator<Employee> comparator2 = (emp1, emp2)-> String.valueOf(emp1.getSalary()).compareTo(String.valueOf(emp2.getSalary()));
        employees.sort(comparator1.thenComparing(comparator2));
        employees.forEach(System.err::println);
    }

}
