package com.atguigu.java8;

import com.atguigu.java8.entity.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class TestStreamApi_2 {

    List<Employee> emps = Arrays.asList(
            new Employee("张三", 18, 3333.33),
            new Employee("李四", 66, 5555.55),
            new Employee("王五", 44, 9999.99),
            new Employee("王五", 44, 9999.99),
            new Employee("赵六", 21, 8888.88),
            new Employee("赵六", 21, 8888.88),
            new Employee("田七", 8, 7777.77)
    );

    //中间操作：只有在进行终止操作的时候，才会执行
   /*
    * 筛选与切片
    * filter——接收 Lambda ， 从流中排除某些元素。
    * limit——截断流，使其元素不超过给定数量。
    * skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
    * distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
	* */

    //内部迭代：迭代操作 Stream API 内部完成
    @Test
    public void test1(){

        //中间操作
        Stream<Employee> empStream = emps.stream()
                .filter((x) -> {
                    System.out.print("Stream API 的中间操作!\t");
                    return x.getAge() > 35;
                });

        //终止操作：一次性执行全部内容，即“惰性求值”
        empStream.forEach(System.out::println);
    }

    //外部迭代
    @Test
    public void test2() {
        Iterator<Employee> iterator = emps.iterator();
        for (Employee emp : emps) {
            System.out.println(emp);
        }
    }

    @Test
    public void test3() {
        emps.stream()
                .filter((e) -> {
                    //stream执行完足够的数量后（比如limit），则不再往下执行了
                    //类似 && ||
                    System.out.print("短路!\t");
                    return e.getSalary() > 1000;
                })
                .limit(2)
                .forEach(System.out::println);
    }

    @Test
    public void test4() {
        emps.stream()
                .filter((e) -> e.getSalary() > 1000)
                .skip(2)
                .distinct() //去重是通过hashcode和equals来做的
                .forEach(System.out::println);
    }

    /*
    * 映射
    * map——接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
    * flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
    *
    * 区别：类似 add(Object obj)   addAll(Collection coll)
    * */
    @Test
    public void test5() {
        List<String> list = Arrays.asList("aa", "bb", "Cc", "dD", "EE");
        list.stream()
                .map((x) -> x.toUpperCase())
                .forEach(System.out::println);

        System.out.println("------------------------------------");

        emps.stream()
                .map((x) -> x.getName())
                .forEach(System.out::println);

        System.out.println("------------------------------------");

        //map本身返回流，函数的作用转换成流
        //{{a, a, a}, {b, b, b}}
        Stream<Stream<Character>> stream = list.stream()
                .map(TestStreamApi_2::filterCharacter);
        stream.forEach((sm) -> {
            sm.forEach(System.out::println);
        });

        System.out.println("------------------------------------");

        //flatMap
        //{a, a, a, b, b, b}
        Stream<Character> stream1 = list.stream()
                .flatMap(TestStreamApi_2::filterCharacter);
        stream1.forEach(System.out::println);
    }

    //字符串转换成流
    public static Stream<Character> filterCharacter(String string) {
        List<Character> list = new ArrayList<>();
        char[] chars = string.toCharArray();
        for (Character ch : chars) {
            list.add(ch);
        }
        return list.stream();
    }

}
