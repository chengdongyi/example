package com.example.java8;

import com.example.domain.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class CollectorsTest {

    private List<Employee> emps = Arrays.asList(
            new Employee(10001, "李四", 19, 9999.99),
            new Employee(10001, "张三", 18, 8888.88),
            new Employee(10002, "王五", 20, 8888.88),
            new Employee(10003, "田七", 22, 9999.99),
            new Employee(10002, "赵六", 21, 9999.99));

    @Test
    public void test1() {

        List<String> list = emps.stream().map(Employee::getName).collect(Collectors.toList());

        Set<String> set = emps.stream().map(Employee::getName).collect(Collectors.toSet());

        HashSet<String> hashSet = emps.stream().map(Employee::getName).collect(Collectors.toCollection(HashSet::new));

    }

    /**
     *  joining(): 拼接输入元素到一个String中(有序的)
     */
    @Test
    public void test2(){

        List<String> lists = Arrays.asList("Hello", "Java", "C++", "Python", "World");
        String collect = lists.stream().collect(Collectors.joining());
        // 结果：HelloJavaC++PythonWorld
        System.out.println(collect);

        collect = lists.stream().collect(Collectors.joining(","));
        // 结果：Hello,Java,C++,Python,World
        System.out.println(collect);

        collect = lists.stream().collect(Collectors.joining(",", "[", "]"));
        // 结果：[Hello,Java,C++,Python,World]
        System.out.println(collect);
    }

    /**
     *  mapping(): 将一个Collector的类型进行转换，
     *  在进行元素聚合之前，使用mapper参数将每一个输入的T类型的元素转换为U类型。
     *  mapping函数常用于多级分区和多级分组
     */
    @Test
    public void test3(){

        List<Integer> lists = emps.stream()
                .collect(Collectors.mapping(Employee::getAge, Collectors.toList()));
        // 结果：[19, 18, 20, 22, 21]
        System.out.println(lists);

    }

    /**
     * 分组
     */
    @Test
    public void test4(){

        // 按照Id分组
        Map<Integer, List<Employee>> map = emps.stream()
                .collect(Collectors.groupingBy(Employee::getId));
        System.out.println(map);

        // 求出各部门的薪资总和
        Map<Integer, Double> salMap = emps.stream()
                .collect(Collectors.groupingBy(Employee::getId, Collectors.summingDouble(Employee::getSalary)));
        System.out.println(salMap);

    }

    /**
     * 多级分组
     */
    @Test
    public void test5(){

        List<Employee> employees = Arrays.asList(
                new Employee(10001, "李四", 19, 9999.99),
                new Employee(10001, "张三", 18, 8888.88),
                new Employee(10002, "王五", 20, 8888.88),
                new Employee(10003, "田七", 22, 9999.99),
                new Employee(10002, "赵六", 21, 9999.99),
                new Employee(10001, "AA", 35, 9999.99),
                new Employee(10001, "BB", 70, 8888.88),
                new Employee(10002, "CC", 55, 8888.88),
                new Employee(10003, "DD", 40, 9999.99),
                new Employee(10002, "EE", 21, 9999.99));

        Map<Integer, Map<String, List<Employee>>> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getId, Collectors.groupingBy((e) -> {
                    if(e.getAge() >= 60)
                        return "老年";
                    else if(e.getAge() >= 35)
                        return "中年";
                    else
                        return "成年";
                })));
        System.out.println(map);
    }

    /**
     * 分区
     */
    @Test
    public void test7(){
        // 按照年龄分区
        Map<Boolean, List<Employee>> map = emps.stream()
                        .collect(Collectors.partitioningBy(emp -> emp.getAge() >= 35));
    }

}
