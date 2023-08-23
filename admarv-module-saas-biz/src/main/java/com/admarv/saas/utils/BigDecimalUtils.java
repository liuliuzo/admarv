package com.admarv.saas.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 
 * @author liuliu
 *
 */
public class BigDecimalUtils {
	
    public static BigDecimal convertToBigDecimal(String input) {
        try {
            return new BigDecimal(input);
        } catch (Exception e) {
            System.err.println("无效的数字格式: " + input);
            return BigDecimal.ZERO;
        }
    }
    
    public static void main(String[] args) {
        BigDecimal cpcBigDecimal = BigDecimalUtils.convertToBigDecimal(null);
        BigDecimal cpcBD = cpcBigDecimal.setScale(2, RoundingMode.HALF_UP);;
        System.out.println(cpcBD.toPlainString());
    }
}
