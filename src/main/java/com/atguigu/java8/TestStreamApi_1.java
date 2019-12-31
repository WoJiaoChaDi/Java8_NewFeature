package com.atguigu.java8;

import com.atguigu.java8.entity.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/*
* 一、Stream 的三个操作步骤：
* 1.创建Stream
* 2.中间操作
* 3.终止操作（中断操作）
*
*
* */
public class TestStreamApi_1 {

    //创建Stream
    @Test
    public void test1() {
        //1.可以通过Collection 系列集合提供的 stream() 或 parallelStream() 获取
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        //2.通过哦Arrays 中的静态方法stream() 获取数组流
        Employee[] employees = new Employee[10];
        Stream<Employee> stream2 = Arrays.stream(employees);

        //3.通过Stream 中的静态方法 of()
        Stream<String> stream3 = Stream.of("aa", "bb", "cc");

        //4.创建无限流
        //迭代
        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2);
        stream4.forEach(System.out::println);
    }
}
