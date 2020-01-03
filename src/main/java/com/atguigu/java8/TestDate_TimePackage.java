package com.atguigu.java8;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

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

    //3.计算时间的间隔
    //Duration  ：   计算两个时间之间的间隔
    //Period    ：   计算两个日期之间的间隔
    //ChronoUnit：   计算两个日期时间相差的总间隔
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

    //TemporaAdjuster 时间矫正器
    @Test
    public void test4() {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        //将日期指定为10日
        LocalDateTime ldt2 = ldt.withDayOfMonth(10);
        System.out.println(ldt2);

        //下个月中的第一天
        LocalDateTime ldt3 = ldt.with(TemporalAdjusters.firstDayOfNextMonth());
        System.out.println(ldt3);

        //获取下一个周日
        LocalDateTime ldt4 = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(ldt4);

        //自定义：获取下一个生日（2019-12-29）  (没有考虑闰年）
        LocalDateTime ldt_temp = LocalDateTime.of(2020, 2, 27, 12, 12, 12);
        LocalDateTime ldt5 = ldt_temp.with((adjuster) -> {
            int b_month = 2;
            int b_day = 28;

            LocalDateTime ldt_tmp = (LocalDateTime) adjuster;
            Month month_now = ldt_tmp.getMonth();
            int month = b_month - month_now.getValue();
            //如果生日月份 大于 当前月份
            if (month > 0) {
                //补充月份
                ldt_tmp = ldt_tmp.plusMonths(month);

                int dayOfMonth = ldt_tmp.getDayOfMonth();
                int day = b_day - dayOfMonth;
                //如果生日日期 大于 当前日期
                if (day > 0) {
                    //计算天数
                    ldt_tmp = ldt_tmp.plusDays(day);
                    return ldt_tmp;
                } else {
                    //计算天数
                    ldt_tmp = ldt_tmp.plusDays(day);
                    //补充一年
                    ldt_tmp = ldt_tmp.plusYears(1);
                    return ldt_tmp;
                }
            } else {
                //排除月份为当月的情况
                if(month != 0){
                    //减去月份
                    ldt_tmp = ldt_tmp.plusMonths(month);
                    //补充一年
                    ldt_tmp = ldt_tmp.plusYears(1);
                }

                int dayOfMonth = ldt_tmp.getDayOfMonth();
                int day = b_day - dayOfMonth;
                //计算天数
                ldt_tmp = ldt_tmp.plusDays(day);
                return ldt_tmp;
            }
        });
        System.out.println(ldt5);
    }

    //DateTimeFormatter ： 格式化时间/日期
    @Test
    public void test5() {
        LocalDateTime ldt = LocalDateTime.now();

        //定义好的时间格式化
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
        String str1 = ldt.format(dtf);
        System.out.println(str1);

        //自定义时间格式化
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String str2 = ldt.format(dtf2);
        System.out.println(str2);

        //解析回LocalDateTime
        LocalDateTime parseDateTime = LocalDateTime.parse("2020-01-03 11:22:22", dtf2);
        System.out.println(parseDateTime);
    }

    //设置时区
    //ZoneDate\ZonedTime\ZoneDateTime
    @Test
    public void test6() {
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        availableZoneIds.stream()
                .forEach(System.out::println);

        //获取指定时区的时间
        LocalDateTime now_Europe = LocalDateTime.now(ZoneId.of("Europe/Monaco"));
        //2020-01-03T03:44:29.272   获取Europe/Monaco  这个地方的时间
        System.out.println(now_Europe);

        //带时区时差的时间
        ZonedDateTime zonedDateTime = now_Europe.atZone(ZoneId.of("Europe/Monaco"));
        //2020-01-03T03:44:29.272+01:00[Europe/Monaco]  跟UTC时间时差 +01:00 小时
        System.out.println(zonedDateTime);

    }
}
