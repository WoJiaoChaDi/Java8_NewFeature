package com.atguigu.java8;

public interface MyInterface2 {

    //Java8中允许有 接口默认方法
    public default String get() {
        return "get() in MyInterface2...";
    }
}
