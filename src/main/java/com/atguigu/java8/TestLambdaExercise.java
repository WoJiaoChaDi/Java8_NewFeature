package com.atguigu.java8;

import com.atguigu.java8.entity.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestLambdaExercise {

    List<Employee> emps = Arrays.asList(
            new Employee("张三", 18, 3333.33),
            new Employee("李四", 66, 5555.55),
            new Employee("王五", 44, 9999.99),
            new Employee("赵六", 21, 8888.88),
            new Employee("田七", 8, 7777.77)
    );

    //调用 Collections.sort() 方法，通过定制排序比较两个 Employee (先按照年龄比，年龄相同按照姓名比)，使用 lambda 作为参数传递。
    @Test
    public void test1(){
        Collections.sort(emps, (e1, e2) ->{
            if(e1.getAge() == e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }else{
                return Integer.compare(e1.getAge(), e2.getAge());
            }
        });

        for (Employee emp : emps) {
            System.out.println(emp);
        }
    }

    /*
    * 1.声明函数式接口，接口中声明抽象方法， public String getValue(String str);
    * 2.声明方法，在方法中编写使用接口作为参数，将一个字符串转换成大写，并作为方法的返回值
    * */
    @Test
    public void test2(){
        MyFunction myFunction = (x) -> x.toUpperCase();
        String str = myFunction.getValue("abcde");
        System.out.println(str);

        String str1 = strHandler("abcdefg", (x) -> x.toUpperCase());
        System.out.println(str1);

        String str2 = strHandler("abcdefg", (x) -> x.substring(2, 3));
        System.out.println(str2);
    }

    //用于处理字符串
    public String strHandler(String str, MyFunction myFunction) {
        return myFunction.getValue(str);
    }

    @Test
    public void test3(){
        opertion(100L, 200L, (x, y) -> x + y);

        opertion(100L, 200L, (x, y) -> x * y);
    }

    //对两个Long 型数据进行处理
    public void opertion(Long l1, Long l2, MyFunction2<Long, Long> myFunction2) {
        System.out.println(myFunction2.getValue(l1, l2));
    }

}
