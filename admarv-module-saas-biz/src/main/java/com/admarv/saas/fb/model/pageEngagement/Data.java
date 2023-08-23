package com.admarv.saas.fb.model.pageEngagement;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author liuliu
 *
 */
public class Data implements Serializable{
	
	private static final long serialVersionUID = 5339167903622275126L;
	
	private String name;
	private String period;
	private List<Values> values;
	private String title;
	private String description;
	private String id;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPeriod() {
		return this.period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public List<Values> getValues() {
		return this.values;
	}

	public void setValues(List<Values> values) {
		this.values = values;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Data [name=" + name + ", period=" + period + ", values=" + values + ", title=" + title
				+ ", description=" + description + ", id=" + id + "]";
	}

}
