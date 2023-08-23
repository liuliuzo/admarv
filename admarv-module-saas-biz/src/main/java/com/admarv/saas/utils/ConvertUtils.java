package com.admarv.saas.utils;

public class ConvertUtils {
    public static int convertToInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            // 处理转换失败的情况
            return 0; // 或者抛出异常，根据实际需求进行处理
        }
    }
    
    public static double convertToDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return 0.00;
        }
    }
}
