package com.admarv.saas.fb.model.adcreatives;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class DegreesOfFreedomSpec implements Serializable {

	private static final long serialVersionUID = 624717138315560709L;
	
	private CreativeFeaturesSpec creativeFeaturesSpec;

	public CreativeFeaturesSpec getCreativeFeaturesSpec() {
		return this.creativeFeaturesSpec;
	}

	public void setCreativeFeaturesSpec(CreativeFeaturesSpec creativeFeaturesSpec) {
		this.creativeFeaturesSpec = creativeFeaturesSpec;
	}

	@Override
	public String toString() {
		return "DegreesOfFreedomSpec [creativeFeaturesSpec=" + creativeFeaturesSpec + "]";
	}

}
