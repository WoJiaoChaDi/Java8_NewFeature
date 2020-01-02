package com.atguigu.java8;

public class TestDefaultMethod extends MyClass implements MyInterface{

    public static void main(String[] args) {
        TestDefaultMethod d = new TestDefaultMethod();
        //类优先原则
        System.out.println(d.get());

        //接口中的静态方法
        MyInterface.show();
    }
}
