package com.atguigu.java8;

import com.atguigu.java8.entity.Employee;
import com.atguigu.java8.entity.Godness;
import com.atguigu.java8.entity.Man;
import com.atguigu.java8.entity.NewMan;
import org.junit.Test;

import java.util.Optional;

/*
 * 一、Optional 容器类：用于尽量避免空指针异常
 * 	Optional.of(T t) : 创建一个 Optional 实例
 * 	Optional.empty() : 创建一个空的 Optional 实例
 * 	Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
 * 	isPresent() : 判断是否包含值
 * 	orElse(T t) :  如果调用对象包含值，返回该值，否则返回t
 * 	orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值
 * 	map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
 * 	flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
 *
 * 	*/
public class TestOptional {

    @Test
    public void test1() {
        Optional<Employee> op = Optional.of(new Employee());
        Employee emp = op.get();
        System.out.println(emp);

        //这样使用可以快速判断null的位置
        Optional.of(null);//在这行报错
    }

    @Test
    public void test2() {
        Optional<Employee> empty = Optional.empty();
        System.out.println(empty.get());//在这行报错
    }

    @Test
    public void test3() {
        //传null，调用empty;    否则调用of()
        Optional<Employee> op = Optional.ofNullable(null);

        //判断是否有值
        if (op.isPresent()) {
            System.out.println(op.get());//也能快速锁定问题所在
        }

        //如果有值就返回值，如果没有则使用orElse的默认值
        Employee employee = op.orElse(new Employee("张三", 18, 2222.22));
        System.out.println(employee);

        //如果有值就返回值，如果没有则使用orElseGet函数式接口中的返回的默认值
        Employee employee1 = op.orElseGet(() -> new Employee());
    }

    @Test
    public void test4() {
        Optional<Employee> op = Optional.ofNullable(new Employee(null, 18, 2222.22));
        Optional<String> s = op.map((e) -> e.getName());
        if (s.isPresent()) {
            System.out.println(s.get());
        }

        Optional<String> s1 = op.flatMap((e) -> Optional.ofNullable(e.getName()));
        if (s1.isPresent()) {
            System.out.println(s1.get());
        }
    }

    @Test
    public void test5() {
        Man man = new Man();
        String godnessName = getGodnessName(man);
        System.out.println(godnessName);
        System.out.println("--------------");

        Optional<NewMan> newMan = Optional.ofNullable(null);
        System.out.println(getGodnessName2(newMan));

        Optional<NewMan> newMan2 = Optional.ofNullable(new NewMan());
        System.out.println(getGodnessName2(newMan2));

        Optional<Godness> gn = Optional.ofNullable(null);
        Optional<NewMan> newMan3 = Optional.ofNullable(new NewMan(gn));
        System.out.println(getGodnessName2(newMan3));

        Optional<Godness> gn1 = Optional.ofNullable(new Godness("存在"));
        Optional<NewMan> newMan4 = Optional.ofNullable(new NewMan(gn1));
        System.out.println(getGodnessName2(newMan4));
    }

    public String getGodnessName(Man man) {
        if (man != null) {
            if (man.getGodness() != null) {
                return man.getGodness().getName();
            }
        }
        return "默认值";
    }

    public String getGodnessName2(Optional<NewMan> newMan) {
        return newMan.orElse(new NewMan()) //如果为null，则创建一个NewMan
                .getGodness()   //此处不会空指针，因为如果是null，上面一句创建了NewMan
                .orElse(new Godness("默认值")) //如果为null，则创建一个Godness
                .getName();     //此处不会空指针，因为如果null，上一句创建了Godness
    }

}
