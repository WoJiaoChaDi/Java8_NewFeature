package com.atguigu.java8;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/*
* 一、Lambda 表达式的基础语法：Java8 中引入了一个新的操作符 "->" 该操作符称为箭头操作符或 Lambda 操作符，箭头操作符将 Lambda 表达式拆分成两部分：
*
* lambda表达式需要 函数式 接口的支持
* (x, y) -> System.out.println(x + y)
*
* 左侧：Lambda 表达式的参数列表
* 右侧：Lambda 表达式中所需执行的功能，即 Lambda 体
*
* 语法格式一：无参数，无返回值
*       () -> System.out.println("Hello Lambda!");
*
* 语法格式二：有一个参数，并且无返回值
*       (x) -> System.out.println("Hello " + x);
*
* 语法格式三：若只有一个参数，小括号可以省略不写
*       x -> System.out.println("Hello " + x);
*
* 语法格式四：有两个以上的参数，有返回值，并且Lambda体内有多条语句
*       (x, y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x, y);
        };
**
* 语法格式五：若lambda 体重只有一条语句，return 和 大括号 都可以省略不写
*       (x, y) -> Integer.compare(x, y);
*
* 语法格式六：Lambda表达式的参数列表的数据类型可以不写，因为JVM编译器通过上下文推断出数据类型，即“类型推断”
*       Comparator<Integer> com = (Integer x, Integer y) -> Integer.compare(x, y);
*       Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
*
*       类型推断：
*       String[] str  {"aaa", "bbb", "ccc"};
*       List<String> list = new ArrayList<>();
*
* 上联：左右遇一括号省
* 下联：左侧推断类型省
* 横批：能省则省
* */
public class TestLambda {

    //语法格式一：无参数，无返回值
    //() -> System.out.println("Hello Lambda!");
    @Test
    public void test1(){

        //局部内部类中，使用了同级别局部变量时，必须是final变量
        int num = 0; //jdk1.7 以前，必须是final，但是本质上还是不能改变

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello Lambda!" + num);
            }
        };

        System.out.println("-----------");

        Runnable r1 = () -> System.out.println("Hello Lambda!" + num);
    }

    //语法格式二：有一个参数，并且无返回值
    //   (x) -> System.out.println("Hello " + x);
    @Test
    public void test2(){
        Consumer<String> con = (x) -> System.out.println("Hello " + x);
        con.accept("World!");
    }

    //语法格式三：若只有一个参数，小括号可以省略不写
    //       x -> System.out.println("Hello " + x);
    @Test
    public void test3(){
        Consumer<String> con = x -> System.out.println("Hello " + x);
        con.accept("World!");
    }

    //语法格式四：有两个以上的参数，有返回值，并且Lambda体内有多条语句
    @Test
    public void test4(){
        Comparator<Integer> com = (x, y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x, y);
        };
    }

    //语法格式五：若lambda 体重只有一条语句，return 和 大括号 都可以省略不写
    //       (x, y) -> Integer.compare(x, y);
    @Test
    public void test5(){
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
    }






}
