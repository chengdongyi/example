package com.example.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @description: 时间工具类
 * @author chengdongyi
 * @date 2019/11/27 10:56
 */
public class DateUtils {

    /**
     * 获取当前年月日
     * 格式: yyyyMMdd
     */
    public static String getCurrentDay() {
        return format(LocalDateTime.now(), "yyyyMMdd");
    }

    /**
     * 获取当前系统时间
     * 格式: yyyyMMddHHmmss
     */
    public static String getCurrentTime() {
        return format(LocalDateTime.now(), "yyyyMMddHHmmss");
    }

    /**
     * 获取当前年月
     * 格式: yyyyMM
     */
    public static String getCurrentMonth() {
        return format(LocalDateTime.now(), "yyyyMM");
    }

    /**
     * 获取月初
     */
    public static Date firstDayOfMonth(){
        return firstDayOfMonth(new Date());
    }

    /**
     * 获取月初
     */
    public static Date firstDayOfMonth(Date date){
        LocalDateTime localDateTime = asLocalDateTime(date);
        localDateTime = localDateTime.with(TemporalAdjusters.firstDayOfMonth())
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
        return asDate(localDateTime);
    }

    /**
     * 获取下一个月月初
     */
    public static Date firstDayOfNextMonth(){
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.plusMonths(1)
                .with(TemporalAdjusters.firstDayOfMonth())
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
        return asDate(localDateTime);
    }

    /**
     * 获取当月最后一天
     */
    public static Date lastDayOfMonth(){
        return lastDayOfMonth(new Date());
    }

    /**
     * 获取当月最后一天
     */
    public static Date lastDayOfMonth(Date date){
        LocalDateTime localDateTime = asLocalDateTime(date);
        localDateTime = localDateTime.with(TemporalAdjusters.lastDayOfMonth())
                .withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(999999999);
        return asDate(localDateTime);
    }

    /**
     * 是否是闰年
     */
    public static boolean isLeapYear(Date date) {
        return asLocalDate(date).isLeapYear();
    }

    // ========================= 时间转指定格式的字符串 =========================
    /**
     * 获取当前系统时间指定格式的字符串
     */
    public static String format(String pattern) {
        return format(LocalDateTime.now(), pattern);
    }

    /**
     * 获取指定格式的字符串
     */
    public static String format(Date date, String pattern) {
        LocalDateTime localDateTime = asLocalDateTime(date);
        return format(localDateTime, pattern);
    }

    /**
     * 获取指定格式的字符串
     */
    public static String format(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    // ========================= 字符串根据指定的格式转成时间 =========================
    public static Date parse(String time, String pattern) {
        LocalDateTime localDateTime = LocalDateTime.parse(time, DateTimeFormatter.ofPattern(pattern));
        return asDate(localDateTime);
    }

    public static LocalDateTime parseDateTime(String time, String pattern) {
        return LocalDateTime.parse(time, DateTimeFormatter.ofPattern(pattern));
    }

    // ========================= LocalDateTime 和 Date 转换 =========================
    /**
     * LocalDate 转 Date
     */
    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDateTime 转 Date
     */
    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date 转 LocalDate
     */
    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Date 转 LocalDateTime
     */
    public static LocalDateTime asLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }










    public static void main(String[] args) throws Exception {

//        LocalDateTime now = LocalDateTime.now();
//        LocalDate now1 = LocalDate.now();
//
//        System.out.println("-------------  时间转字符串 -------------");
//        System.out.println(format("yyyy-MM-dd HH:mm:ss"));
//        System.out.println(format(new Date(), "yyyy-MM-dd HH:mm:ss"));
//        System.out.println(format(now, "yyyy-MM-dd HH:mm:ss"));
//        System.out.println("-------------  字符串转时间 -------------");
//        System.out.println(parse("20191212000000", "yyyyMMddHHmmss"));
//        System.out.println(parseDateTime("20191212000000", "yyyyMMddHHmmss"));
//        System.out.println("-------------  LocalDateTime 和 Date 互转 -------------");
//        System.out.println(asDate(now));
//        System.out.println(asDate(now1));
//        System.out.println(asLocalDate(new Date()));
//        System.out.println(asLocalDateTime(new Date()));

        System.out.println("当前年月日：" + getCurrentDay());
        System.out.println("当前年月：" + getCurrentMonth());
        System.out.println("当前时间：" + getCurrentTime());
        System.out.println("月初：" + format(firstDayOfMonth(), "yyyy-MM-dd HH:mm:ss:SSS"));
        System.out.println("下个月初：" + format(firstDayOfNextMonth(), "yyyy-MM-dd HH:mm:ss:SSS"));
        System.out.println("月末：" + format(lastDayOfMonth(), "yyyy-MM-dd HH:mm:ss:SSS"));
        System.out.println("月末：" + format(lastDayOfMonth(new Date()), "yyyy-MM-dd HH:mm:ss:SSS"));

//        LocalDateTime today = LocalDateTime.now();
//
//        //获取当前年，并判断是否是闰年
//        System.out.println(today.getYear() + " 年, 是闰年? " + today.toLocalDate().isLeapYear());
//
//        //比较两个日期的先后
//        System.out.println("Today is before 01/01/2015? " + today.isBefore(LocalDateTime.of(2015,1, 1, 11, 30)));
//
//        //时间加减
//        System.out.println("1年后 " + today.plusYears(1));
//        System.out.println("2个月后 " + today.plusMonths(2));
//        System.out.println("3周前 " + today.minusWeeks(3));
//        System.out.println("10天前 " + today.minusDays(10));
//        System.out.println("2小时后 " + today.plusHours(2));
//        System.out.println("10分钟后 " + today.plusMinutes(10));
//        System.out.println("3秒后 " + today.plusSeconds(10));

    }

}
