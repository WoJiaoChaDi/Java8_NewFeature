package com.atguigu.java8.forkJoin;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class TestForkJoin {

    /*
    * ForkJoin框架
    * */
    @Test
    public void test1() {
        Instant start = Instant.now();

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalculate(0, 20000000000L);

        Long sum = pool.invoke(task);
        System.out.println(sum);

        Instant end = Instant.now();
        System.out.println("消费时间为：" + Duration.between(start, end).toMillis());
    }

    @Test
    public void test2() {
        Instant start = Instant.now();

        long sum = 0;
        for (long i = 0; i < 20000000000L; i++) {
            sum += i;
        }
        System.out.println(sum);

        Instant end = Instant.now();
        System.out.println("消费时间为：" + Duration.between(start, end).toMillis());
    }

    @Test
    public void test3() {
        Instant start = Instant.now();
        //顺序流（单线程）
        LongStream.rangeClosed(0, 20000000000L)
                .reduce(0, Long::sum);
        Instant end = Instant.now();
        System.out.println("消费时间为：" + Duration.between(start, end).toMillis());

        Instant start1 = Instant.now();
        //并行流\顺序流转换 .parallel() .sequential()
        LongStream.rangeClosed(0, 20000000000L)
                .parallel()
                .reduce(0, Long::sum);
        Instant end1 = Instant.now();
        System.out.println("消费时间为：" + Duration.between(start1, end1).toMillis());
    }
}
