package com.atguigu.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/*
* Java 内置四大核心函数式接口
*
* Consumer<T>   ： 消费性接口
*       void accept(T t);
*
* Supplier<T>   :   供给型接口
*       T get();
*
* Function<T, R>:   函数型接口
*       R apply(T t);
*
* Predicate<T>  :   断言型接口
*       boolean test(T t);
*
* 其他接口：
* BiFunction<T, U, R>
*     R apply(T t, U u);
*
* BiPredicate<T, U>
*     boolean test(T t, U u);
*
* UnaryOperator<T>      Function<T, R> 的子接口
*     R apply(T t);
*
* BinaryOperator<T>     BiFunction<T, U, R> 的子接口
*     T apply(T t, T t);
*
* BiConsumer<T, U>
*     void accept(T t, U u);
*
* IntFunction<R>
*     R apply(int value);
* LongFunction<R>
*     R apply(long value);
* DoubleFunction<R>
*     R apply(double value);
*
* ToIntFunction<T>
*     int applyAsInt(T value);
* ToLongFunction<T>
*     long applyAsLong(T value);
* ToDoubleFunction<T>
*     double applyAsDouble(T value);
*
* */
public class TestLambdaFunctional {

    //Consumer<T>   ： 消费性接口
    @Test
    public void test1(){
        consumer(10000, (x) -> System.out.println("每次消费：" + x));
    }

    public void consumer(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    //Supplier<T>   :   供给型接口
    @Test
    public void test2(){
        List list = getNumList(10, () -> (int) (Math.random() * 100));
        System.out.println(list);
    }

    //产生指定个数的整数，放入到集合中
    public List<Integer> getNumList(int num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            Integer integer = supplier.get();
            list.add(integer);
        }
        return list;
    }

    //Function<T, R>:   函数型接口
    @Test
    public void test3() {
        String newStr = strHandler("\t\t\t\t\t\t\t  你好java！     ", (x) -> x.trim().toUpperCase());
        System.out.println(newStr);
    }

    //处理字符串
    public String strHandler(String str, Function<String, String> function) {
        return function.apply(str);
    }

    //Predicate<T>  :   断言型接口
    @Test
    public void test4() {
        List<String> list = Arrays.asList("Hello", "atguigu", "Lambda", "www", "OK");
        List<String> newList = filterStr(list, (x) -> x.length() > 3);
        System.out.println(newList);
    }

    //将满足条件的字符串放入集合中
    public List<String> filterStr(List<String> list, Predicate<String> predicate) {
        List<String> strList = new ArrayList<>();

        for (String s : list) {
            boolean flag = predicate.test(s);
            if (flag) {
                strList.add(s);
            }
        }

        return strList;
    }

}
