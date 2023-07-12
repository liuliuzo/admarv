package com.admarv.saas.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期转正Cron则表达式工具类
 * 
 * @author liuliu
 *
 */
public class CronUtils {

    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("ss mm HH dd MM ? yyyy");
    
    private CronUtils() {
        throw new IllegalStateException("Utility class");
    }
    
    public static String convertToCronExpression(String dateString) {
        LocalDateTime dateTime = LocalDateTime.parse(dateString, INPUT_FORMATTER);
        return dateTime.format(OUTPUT_FORMATTER);
    }
}
