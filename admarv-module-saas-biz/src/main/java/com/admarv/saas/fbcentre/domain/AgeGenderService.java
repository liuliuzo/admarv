package com.admarv.saas.fbcentre.domain;

import java.util.Map;

/**
 * 按年龄统计 按性别统计
 * 
 * @author liuliu
 *
 */
public class AgeGenderService {

	public static void calculateAgeAndGenderStatistics(
			Map<String, Integer> ageStatistics,
			Map<String, Integer> genderStatistics, 
			Map<String, Integer> dataMap) {
		for (Map.Entry<String, Integer> entry : dataMap.entrySet()) {
			String key = entry.getKey();
			int count = entry.getValue();
			String ageGroup = key;
			if (key.contains("U")) {
				ageGroup = ageGroup.replace("U.", "");  			 	//将 "U." 替换为空，获取年龄段部分
			} else if (key.contains("M") || key.contains("F")) {
				ageGroup = ageGroup.substring(key.indexOf(".") + 1); 	//获取年龄段部分
			}
			ageStatistics.put(ageGroup, ageStatistics.getOrDefault(ageGroup, 0) + count);
			String gender = key.substring(0, 1); 					  	//提取性别部分
			genderStatistics.put(gender, genderStatistics.getOrDefault(gender, 0) + count);
		}
	}
}
