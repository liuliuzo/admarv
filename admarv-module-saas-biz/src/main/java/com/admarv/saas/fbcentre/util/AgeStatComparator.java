package com.admarv.saas.fbcentre.util;

import java.util.Comparator;

import com.admarv.saas.fbcentre.dto.resp.AgeStat;

/**
 * 
 * @author liuliu
 *
 */
public class AgeStatComparator implements Comparator<AgeStat> {
	@Override
	public int compare(AgeStat ageStat1, AgeStat ageStat2) {
		return ageStat1.getAgeRange().compareTo(ageStat2.getAgeRange());
	}
}