package com.atguigu.java8;

import com.atguigu.java8.entity.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/*
* 方法引用：若Lambda 体中的内容有方法已经实现了，我们可以使用“方法引用”
*           （可以理解为方法引用时 Lambda 表达式的另一种表现形式）
*
* 主要有三种语法格式：
*
* 对象::实例方法名
* 类::静态方法名
* 类::实例方法名
*
* 注意：
*   1.Lambda 体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一致！
*   2.若Lambda 参数列表中的第一个参数是 实例方法的调用者，而第二个参数是实例方法的参数时，可以使用ClassName::method
*
*
* 二、构造器引用：
*
* 格式：
*   ClassName::new
*
* 注意：需要调用的构造器的参数列表与函数式接口中抽象方法的参数列表保持一致！
*
*
* 三、数组引用
*
*   Type::new
*
*
*
* */
public class TestMethodRef {

    //对象::实例方法名
    @Test
    public void test1(){
        PrintStream ps = System.out;

        //Lambda 表达式
        Consumer<String> con = (x) -> ps.println(x);

        //函数式接口的 参数、返回值  与  对象方法的 参数、返回值 保持一致
        Consumer<String> con1 = ps::println;
        Consumer<String> con2 = System.out::println;

        con1.accept("abcde");
        con2.accept("ABCDE");
    }

    //对象::实例方法名
    @Test
    public void test2(){
        Employee emp = new Employee("张三", 18, 9999.99);

        Supplier<String> supplier = () -> emp.getName();

        Supplier<Integer> supplier1 = emp::getAge;

        System.out.println(supplier.get());
        System.out.println(supplier1.get());
    }

    //类::静态方法名
    @Test
    public void test3() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);

        Comparator<Integer> com1 = Integer::compare;

        List<Integer> list = Arrays.asList(6,3,8,32,1,7,48,9,1);
        Collections.sort(list, com1);
        System.out.println(list);
    }

    //类::实例方法名
    @Test
    public void test4() {
        BiPredicate<String, String> biPredicate = (x, y) -> x.equals(y);

        BiPredicate<String, String> biPredicate1 = String::equals;

        //类::实例方法  第一个参数是对象，后面的参数都是
        MyInterface_3<String, String, String> my = String::replaceAll;
        //上面的语句等同于下面的语句
        //MyInterface_3<String, String, String> my = (x, y, z) -> x.replaceAll(y, z);
        String result = my.test("abcdefg","e","pppp");
        System.out.println(result);
    }

    public interface MyInterface_3<T,U,F> {
        T test(T x, U y, F z);
    }

    //构造器引用
    @Test
    public void test5(){
        Supplier<Employee> supplier = () -> new Employee();

        //构造器中的参数列表 必须跟 函数式接口的参数列表 一致，到时候new的就是那个构造函数
        //此时是 new Employee();
        Supplier<Employee> supplier1 = Employee::new;

        Function<Integer, Employee> function = (x) -> new Employee(x);

        //此时是 new Employee(Integer id);
        Function<Integer, Employee> function1 = Employee::new;
        Employee emp1 = function1.apply(5);
        System.out.println(emp1);
    }

    //数组引用
    @Test
    public void test6() {
        Function<Integer, String[]> function = (x) -> new String[x];
        String[] strArr = function.apply(10);
        System.out.println(strArr.length);

        Function<Integer, String[]> function1 = String[]::new;
        String[] strArr2 = function1.apply(20);
        System.out.println(strArr2.length);
    }


}
