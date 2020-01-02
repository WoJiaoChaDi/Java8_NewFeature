package com.atguigu.java8;

import javafx.concurrent.Task;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestDatePackage {

    @Test
    public void test1() throws ParseException {

        //传统的Date：年是从1900开始算，月份是从0开始
        Date date0 = new Date();
        printDate(date0);

        //年月不是很方便，所以过时过期
        Date date1 = new Date(1, 11, 30, 14, 59, 59);
        printDate(date1);

        //Calendar 每周的第一天是星期天
        Calendar cal = Calendar.getInstance();
        Date date2 = cal.getTime();
        printDate(date2);

        //Calendar 日期-5天
        cal.add(Calendar.DAY_OF_MONTH, -5);
        Date date3 = cal.getTime();
        printDate(date3);

        //Calendar 设置日期时间
        cal.set(2000,11,31,15,33,33);
        Date date4 = cal.getTime();
        printDate(date4);

        //SimpleDateFormat 时间格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(new Date());
        System.out.println(format);

        //SimpleDateFormat 时间解析
        Date parseDate = sdf.parse("2019-11-11 16:45:45");
        System.out.println(parseDate);
        //以上都不是线程安全的
    }

    //测试SimpleDateFormat多线程安全问题
    public static void main(String[] args) {

        //会有多线程安全问题
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        //Callable<Date> task = () -> sdf.parse("20161218");
        //这样才没有多线程安全问题
        //Callable<Date> task = () -> DateFormatThreadLocal.convert("20161218");

        //用jdk8的api
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");
        Callable<LocalDate> task = () -> LocalDate.parse("20191212 15:16:17", dtf);

        ExecutorService pool = Executors.newFixedThreadPool(10);
        //List<Future<Date>> result = new ArrayList<>();
        List<Future<LocalDate>> result = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            result.add(pool.submit(task));
        }

        result.stream()
                .forEach((e) -> {
                    try {
                        System.out.println(e.get());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });
    }

    //测试SimpleDateFormat多线程安全问题
    @Test
    public void test3() {

    }

    public void printDate(Date date) {
        System.out.println(dateToStr(date));
    }

    public String dateToStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public String dateToStrOld(Date date) {
        return  Integer.toString(1900 + date.getYear())     //从1900开始算
                + "-"
                + Integer.toString(1 + date.getMonth())     //从0开始算
                + "-"
                + Integer.toString(date.getDate())  //日历中的第几天
                + "  "
                + Integer.toString(date.getHours())
                + ":"
                + Integer.toString(date.getMinutes())
                + ":"
                + Integer.toString(date.getSeconds())
                + "  星期"
                + Integer.toString(date.getDay())
                + "（0是星期天）";
    }
}
