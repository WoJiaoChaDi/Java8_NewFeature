package com.atguigu.java8;

import com.atguigu.java8.entity.Employee;
import com.atguigu.java8.entity.Status;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class TestStreamApi_2 {

    List<Employee> emps = Arrays.asList(
            new Employee("张三", 18, 9999.99, Status.BUSY),
            new Employee("李四", 44, 5555.55, Status.FREE),
            new Employee("王五", 66, 3333.33, Status.VACATION),
            new Employee("王五", 44, 9999.99, Status.BUSY),
            new Employee("赵六", 18, 8888.88, Status.BUSY),
            new Employee("赵六", 21, 8888.88, Status.VACATION),
            new Employee("田七", 21, 7777.77, Status.FREE)
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

    /*
    * 排序：
    *   sorted()——自然排序(Comparable)
    *   sorted(Comparator com)——定制排序(Comparator)
    * */
    @Test
    public void test6() {
        List<String> list = Arrays.asList("ccc", "aaa", "eee", "bbb", "ddd");
        list.stream()
                .sorted()
                .forEach(System.out::println);

        System.out.println("--------------");

        //先按照名字正序，再按照工资倒序
        emps.stream()
                .sorted((e1, e2) -> {
                    if (e1.getAge() == e2.getAge()) {
                        return e1.getName().compareTo(e2.getName());
                    }else{
                        return (int) (e2.getSalary() - e1.getSalary());
                    }
                })
                .forEach(System.out::println);
    }

    //3. 终止操作
	/* 注意：流进行了终止操作后，不能再次使用
	 *
	 * allMatch——检查是否匹配所有元素
	 * anyMatch——检查是否至少匹配一个元素
	 * noneMatch——检查是否没有匹配的元素
	 * findFirst——返回第一个元素
	 * findAny——返回当前流中的任意元素
	 * count——返回流中元素的总个数
	 * max——返回流中最大值
	 * min——返回流中最小值
	 */
    @Test
    public void test7() {

        //是否全都匹配 BUSY 状态
        boolean b1 = emps.stream()
                .allMatch((e) -> e.getStatus().equals(Status.BUSY));
        System.out.println(b1);

        //是否至少有一个匹配 BUSY 状态
        boolean b2 = emps.stream()
                .anyMatch((e) -> e.getStatus().equals(Status.BUSY));
        System.out.println(b2);

        //是否全部不匹配 OTHER 状态
        boolean b3 = emps.stream()
                .noneMatch((e) -> e.getStatus().equals(Status.OTHER));
        System.out.println(b3);

        //返回工资最高的第一个元素
        Optional<Employee> first = emps.stream()
                .sorted((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()))
                .findFirst();
        System.out.println(first.get());

        //返回任意一个元素,如果找不到就返回null
        Employee any = emps.parallelStream()
                .findAny().orElse(null);
        System.out.println(any);

        //返回流中元素的总个数
        long count = emps.stream()
                .count();
        System.out.println(count);

        //返回工资最大的员工
        Optional<Employee> max = emps.stream()
                .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(max);

        //返回工资中最小的工资是多少
        Optional<Double> min = emps.stream()
                .map(Employee::getSalary)
                .min(Double::compareTo);

        Optional<Double> min2 = emps.stream()
                .map((e) -> e.getSalary())
                .min((e1, e2) -> e1.compareTo(e2));

        Optional<Double> min3 = emps.stream()
                .map(new Function<Employee, Double>() {
                    @Override
                    public Double apply(Employee e) {
                        return e.getSalary();
                    }
                })
                .min(new Comparator<Double>() {
                    @Override
                    public int compare(Double o1, Double o2) {
                        return o1.compareTo(o2);
                    }
                });

        System.out.println("min:" + min.get() + "  min2:" + min2.get() + "  min3:" + min3.get());
    }

    /*
    *
    * */

}
