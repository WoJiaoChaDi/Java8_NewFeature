package com.atguigu.java8;

import com.atguigu.java8.entity.Employee;
import com.atguigu.java8.entity.Status;
import com.atguigu.java8.entity.Trader;
import com.atguigu.java8.entity.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStreamExercise {

    List<Transaction> transactions = null;

    @Before
    public void before(){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    //1. 找出2011年发生的所有交易， 并按交易额排序（从低到高）
    @Test
    public void test1(){
    }

    //2. 交易员都在哪些不同的城市工作过？
    @Test
    public void test2(){
    }

    //3. 查找所有来自剑桥的交易员，并按姓名排序
    @Test
    public void test3(){
    }

    //4. 返回所有交易员的姓名字符串，按字母顺序排序
    @Test
    public void test4(){
    }

    //5. 有没有交易员是在米兰工作的？
    @Test
    public void test5(){
    }


    //6. 打印生活在剑桥的交易员的所有交易额
    @Test
    public void test6(){
    }


    //7. 所有交易中，最高的交易额是多少
    @Test
    public void test7(){
    }

    //8. 找到交易额最小的交易
    @Test
    public void test8(){
    }

    /*
	  	1.	给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？
		，给定【1，2，3，4，5】， 应该返回【1，4，9，16，25】。
	 */
    Integer[] nums = new Integer[]{1,2,3,4,5};
    @Test
    public void test_1(){
        List<Integer> list = Arrays.stream(nums)
                .map((x) -> x * x)
                .collect(Collectors.toList());
        nums = list.toArray(new Integer[5]);
        Arrays.stream(nums)
                .forEach(System.out::println);
    }

    /*
     2.	怎样用 map 和 reduce 方法数一数BUSY状态的Employee 流中有多少个Employee呢？
     */
    @Test
    public void test_2(){

        //只用map和reduce
        Integer reduce = emps.stream()
                .map((e) -> {
                    if (e.getStatus().equals(Status.FREE)) {
                        return 1;
                    } else {
                        return 0;
                    }
                })
                .reduce(0, Integer::sum);
        System.out.println(reduce);

        //可用以其他的情况
        long count = emps.stream()
                .filter((x) -> x.getStatus().equals(Status.FREE))
                .count();
        System.out.println(count);
    }
    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66, Status.BUSY),
            new Employee(101, "张三", 18, 9999.99, Status.FREE),
            new Employee(103, "王五", 28, 3333.33, Status.VACATION),
            new Employee(104, "赵六", 8, 7777.77, Status.BUSY),
            new Employee(104, "赵六", 8, 7777.77, Status.BUSY),
            new Employee(104, "赵六", 8, 7777.77, Status.FREE),
            new Employee(104, "赵六", 8, 7777.77, Status.FREE),
            new Employee(105, "田七", 38, 5555.55, Status.BUSY)
    );
}
