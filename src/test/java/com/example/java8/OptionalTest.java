package com.example.java8;

import com.example.domain.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 一、Optional 容器类：用于尽量避免空指针异常
 *  Optional.of(T t) : 创建一个 Optional 实例
 *  Optional.empty() : 创建一个空的 Optional 实例
 *  Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
 *    isPresent() : 判断是否包含值
 *    orElse(T t) :  如果调用对象包含值，返回该值，否则返回t
 *    orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值
 *    map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
 *    flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
 *
 */
public class OptionalTest {

    public static void main(String[] args) {

        test1();
        test2();
        test3();

    }


    public static void test1() {

        List<Integer> list = Arrays.asList(3, 2, 4, 5, 1, 20);

        // 1. 最小值
        Stream<Integer> stream = list.stream();
        Optional<Integer> optional = stream.min(Integer::compareTo);
        optional.ifPresent(min -> System.out.println("最小值: " + min));
        stream.close();

        // 2. 最大值
        Stream<Integer> stream1 = list.stream();
        Optional<Integer> optional1 = stream1.max(Integer::compareTo);
        optional1.ifPresent(max -> System.out.println("最大值: " + max));
        stream1.close();

        // 3. 过滤(如果 num > 5, 则返回 max, 否则返回 -100)
        Stream<Integer> stream2 = list.stream();
        Optional<Integer> optional2 = stream2.filter(num -> num > 5).max(Integer::compareTo);
        int result = optional2.orElse(-100);
        System.out.println("过滤结果: " + result);
        stream2.close();
    }

    public static void test2() {

        Employee emp = null;
        Optional<Employee> dataOpt = Optional.ofNullable(emp);

        System.out.println("\r\n情况一 - 存在则开干-----------------------------------------");
        demo1(dataOpt, emp);
        System.out.println("\r\n情况二 - 存在则返回，无则返回标记----------------------------");
        demo2(dataOpt, emp);
        System.out.println("\r\n情况三 - 存在则返回，无则由函数产生--------------------------");
        demo3(dataOpt, emp);
        System.out.println("\r\n情况四 - 夺命连环null检查-----------------------------------");
        demo4(dataOpt, emp);
    }

    /**
     * 情况一 - 存在则开干
     */
    public static void demo1(Optional<Employee> empOpt, Employee emp) {
        // Java7：
        if (emp != null) {
            System.out.println(emp.getName());
        }
        // Java8：
        empOpt.ifPresent((employee) -> System.out.println(employee.getName()));
    }

    /**
     * 情况二 - 存在则返回，无则返回标记
     */
    public static void demo2(Optional<Employee> empOpt, Employee emp) {
        // Java7：
        Employee emp2 = demo2Java7(emp);
        System.out.println(emp2);

        // Java8：
        Employee emp3 = demo2Java8(empOpt);
        System.out.println(emp3);
    }
    public static Employee demo2Java7(Employee emp) {
        // Java7：
        if (emp != null) {
            return emp;
        } else {
            return getEmployee1();
        }
    }
    public static Employee demo2Java8(Optional<Employee> empOpt) {
         return empOpt.orElse(getEmployee1());
    }

    /**
     * 情况三 - 存在则返回，无则由函数产生
     */
    public static void demo3(Optional<Employee> empOpt, Employee emp) {
        // Java7：
        Employee emp2 = demo3Java7(emp);
        System.out.println(emp2);

        // Java8：
        Employee emp3 = demo3Java8(empOpt);
        System.out.println(emp3);
    }
    public static Employee demo3Java7(Employee emp) {
        // Java7：
        if (emp != null) {
            return emp;
        } else {
            return getEmployee1();
        }
    }
    public static Employee demo3Java8(Optional<Employee> empOpt) {
        return empOpt.orElseGet(() -> getEmployee1());
    }

    /**
     * 情况四 - 夺命连环null检查
     */
    public static void demo4(Optional<Employee> empOpt, Employee emp) {
        // Java7：
        System.out.println(demo4Java7(emp));

        // Java8：
        System.out.println(demo4Java8(empOpt));

    }
    public static String demo4Java7(Employee emp) {
        // Java7：
        if (emp != null) {
            String name = emp.getName();
            if (name != null) {
                return name.toString();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
    public static String demo4Java8(Optional<Employee> empOpt) {
        return empOpt.map(emp -> emp.getName())
                      .map(name -> name.toString())
                      .orElse(null);
    }

    public static Employee getEmployee() {
        Employee emp = new Employee();
        emp.setName("张三");
        emp.setAge(18);
        return emp;
    }

    public static Employee getEmployee1() {
        Employee emp = new Employee();
        emp.setName("李四");
        emp.setAge(19);
        return emp;
    }

    private static void test3() {

        System.out.println("\r\n------------------------------------");
        Employee emp = getEmployee();
        emp = null;
//        Optional<Employee> op = Optional.of(emp);
        Optional<Employee> op = Optional.ofNullable(emp);
//        Optional<String> op2 = op.map(Employee::getName);
//        System.out.println(op2.get());
        System.out.println(op.map(Employee::getName).orElse(null));
//        Optional<String> op3 = op.flatMap((e) -> Optional.of(e.getName()));
//        System.out.println(op3.get());

        System.out.println(op.flatMap((e) -> Optional.of(e.getName())).orElse(null));
    }

}
