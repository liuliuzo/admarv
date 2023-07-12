package com.admarv.saas.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liuliu
 * 
 */
public class TimeUtils {
    
    private TimeUtils() {
        throw new IllegalStateException("Utility class");
    }
    
    // 将指定格式的字符串转换为UNIX时间戳
    public static long stringToUnixTimestamp(String dateString, String format) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = sdf.parse(dateString);
        return date.getTime() / 1000;
    }

}
