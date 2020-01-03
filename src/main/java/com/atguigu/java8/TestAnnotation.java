package com.atguigu.java8;

import org.junit.Test;

import java.lang.reflect.Method;

/*重复注解与类型注解*/
public class TestAnnotation {

    @MyAnnotation("Hello")
    @MyAnnotation("World")
    public void show() {

    }

    @Test
    public void test1() throws NoSuchMethodException {
        Class<TestAnnotation> clazz = TestAnnotation.class;
        Method m1 = clazz.getMethod("show");
        MyAnnotation[] annotationsByType = m1.getAnnotationsByType(MyAnnotation.class);

        for (MyAnnotation myAnnotation : annotationsByType) {
            System.out.println(myAnnotation);
        }
    }
}
