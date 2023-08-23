package com.admarv.saas.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author liuliu
 *
 */
public class DateUtils {
    
    private static final Logger log = LoggerFactory.getLogger(DateUtils.class);
    
    private DateUtils() {
        throw new IllegalStateException("Utility class");
    }
    
    // 常见的日期格式模式常量
    public static final String PATTERN_DEFAULT = "yyyy-MM-dd";
    public static final String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_DATE_TIMEZONE = "yyyy-MM-dd HH:mm:ss z";

    public static final String PATTERN_DATE_LONG = "MMMM dd, yyyy"; 
    public static final String PATTERN_DATE_SHORT = "MM/dd/yyyy";
    public static final String PATTERN_DATE_MDY = "MM/dd/yyyy";
    public static final String PATTERN_DATE_DMY = "dd/MM/yyyy";
    public static final String PATTERN_DATE_YMD = "yyyy/MM/dd";
    
    public static final String PATTERN_TIME_12HR = "hh:mm a";
    public static final String PATTERN_TIME_24HR = "HH:mm";
    
    public static final String PATTERN_MONTH_FULL = "MMMM"; 
    public static final String PATTERN_MONTH_ABBR = "MMM";
    public static final String PATTERN_MONTH_NUMERIC = "MM";
    
    public static final String PATTERN_YEAR_FULL = "yyyy";
    public static final String PATTERN_YEAR_ABBR = "yy";
    
    public static final String PATTERN_DAY_OF_WEEK_FULL = "EEEE";
    public static final String PATTERN_DAY_OF_WEEK_ABBR = "EEE";
    public static final String PATTERN_DAY_OF_WEEK_NUMERIC = "u";

    /**
     * 将字符串按照指定格式转换为Date对象
     *
     * @param dateString 日期字符串
     * @param pattern    指定的日期格式
     * @return 转换后的Date对象，如果解析失败则返回null
     */
    public static Date stringToDate(String dateString, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            return format.parse(dateString);
        } catch (ParseException e) {
            log.error("format parse error", e);
            return null;
        }
    }

    /**
     * 将Date对象按照指定格式格式化为字符串
     *
     * @param date    Date对象
     * @param pattern 指定的日期格式
     * @return 格式化后的日期字符串
     */
    public static String dateToString(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }
    
    public static void main(String[] args) {
        String test = DateUtils.dateToString(new Date(), DateUtils.PATTERN_DEFAULT);
        System.out.println(test);
    }
}
