package com.atguigu.java8;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

public class TestDate_TimePackage {

    //1. 人阅读的时间：LocalDate LocalTime LocalDateTime
    @Test
    public void test1() {
        //获取当前日期时间
        LocalDateTime ldt = LocalDateTime.now();
        //2020-01-03T09:25:52.102
        System.out.println(ldt);

        //获取指定的日期时间，此处的月份是实际数字
        LocalDateTime ldt2 = LocalDateTime.of(2019, 10, 19, 13, 12, 33);
        //2019-10-19T13:12:33
        System.out.println(ldt2);

        //日期时间加减，产生新的日期时间
        LocalDateTime ldt2_new = ldt2.plusYears(1);
        ldt2_new = ldt2_new.plusMonths(1);
        ldt2_new = ldt2_new.plusDays(10);
        ldt2_new = ldt2_new.minusHours(15);
        ldt2_new = ldt2_new.minusMinutes(50);
        ldt2_new = ldt2_new.minusSeconds(35);
        System.out.println(ldt2_new);

        //获取年月日时分秒，获取的月份是实际数字
        System.out.println(ldt2_new.getYear());
        System.out.println(ldt2_new.getMonthValue());
        System.out.println(ldt2_new.getDayOfMonth());
        System.out.println(ldt2_new.getHour());
        System.out.println(ldt2_new.getMinute());
        System.out.println(ldt2_new.getSecond());
    }

    //2. 计算机阅读的时间：Instant 时间戳，以Unix元年：1970-1-1 00:00:00 到某个时间之间的毫秒值
    @Test
    public void test2() {
        Instant ins1 = Instant.now();//默认获取的是UTC 时区为基础的，与中国相差8个小时时差
        //2020-01-03T01:37:32.731Z
        System.out.println(ins1);

        //添加时区相差时间
        OffsetDateTime odt = ins1.atOffset(ZoneOffset.ofHours(8));
        //2020-01-03T09:37:32.731+08:00
        System.out.println(odt);

        //获取毫秒值
        long l = ins1.toEpochMilli();
        //1578015569247
        System.out.println(l);

        //从Unix元年,偏移60秒
        Instant ins2 = Instant.ofEpochSecond(60);
        //1970-01-01T00:01:00Z
        System.out.println(ins2);
    }

    //3.
    //Duration ： 计算两个时间之间的间隔
    //Period   ： 计算两个日期之间的间隔
    @Test
    public void test3() throws InterruptedException {
        Instant ins1 = Instant.now();
        Thread.sleep(1000);
        Instant ins2 = Instant.now();

        Duration duration1 = Duration.between(ins1, ins2);
        System.out.println(duration1.toMillis());
        System.out.println("--------------");

        LocalTime lt1 = LocalTime.now();
        Thread.sleep(1000);
        LocalTime lt2 = LocalTime.now();

        Duration duration2 = Duration.between(lt1, lt2);
        System.out.println(duration2.toMillis());
        System.out.println("--------------");

        //日期相差的值
        LocalDate ld1 = LocalDate.of(1991,12,29);
        LocalDate ld2 = LocalDate.now();
        Period period = Period.between(ld1, ld2);
        System.out.println(period.getDays());//月份中相差的天数
        System.out.println(period.getMonths());//相差的月份
        System.out.println(period.getYears());//相差的年份
        System.out.println("--------------");

        //相差的总天数
        long chronoUnitDay = ChronoUnit.DAYS.between(ld1, ld2);
        System.out.println(chronoUnitDay);

        //相差的总月份
        long chronoUnitMonth = ChronoUnit.MONTHS.between(ld1, ld2);
        System.out.println(chronoUnitMonth);

    }
}
