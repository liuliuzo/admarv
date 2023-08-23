package com.admarv.saas.fb.model.adcreatives;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class CreativeFeaturesSpec implements Serializable {
	
	private static final long serialVersionUID = -3645850920593517530L;
	
	private AdvantagePlusCreative advantagePlusCreative;
	
	private StandardEnhancements standardEnhancements;

	public AdvantagePlusCreative getAdvantagePlusCreative() {
		return this.advantagePlusCreative;
	}

	public void setAdvantagePlusCreative(AdvantagePlusCreative advantagePlusCreative) {
		this.advantagePlusCreative = advantagePlusCreative;
	}

	public StandardEnhancements getStandardEnhancements() {
		return this.standardEnhancements;
	}

	public void setStandardEnhancements(StandardEnhancements standardEnhancements) {
		this.standardEnhancements = standardEnhancements;
	}

	@Override
	public String toString() {
		return "CreativeFeaturesSpec [advantagePlusCreative=" + advantagePlusCreative + ", standardEnhancements="
				+ standardEnhancements + "]";
	}
}
