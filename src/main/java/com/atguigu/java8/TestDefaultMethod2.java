package com.atguigu.java8;

public class TestDefaultMethod2 implements MyInterface, MyInterface2{

    //如果多个接口有同样的默认方法，则需要重写
    @Override
    public String get() {
        return MyInterface2.super.get();
    }

    public static void main(String[] args) {
        TestDefaultMethod2 d = new TestDefaultMethod2();
        //类优先原则
        System.out.println(d.get());
    }
}
