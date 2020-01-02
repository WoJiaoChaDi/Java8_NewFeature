package com.atguigu.java8;

public interface MyInterface {

    //Java8中允许有 接口默认方法
    public default String get() {
        return "get() in MyInterface...";
    }

    public static void show() {
        System.out.println("接口中的静态方法...");
    }
}
