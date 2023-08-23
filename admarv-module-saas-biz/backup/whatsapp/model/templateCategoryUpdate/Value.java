package com.admarv.saas.whatsapp.model.templateCategoryUpdate;

public class Value {

	private Integer messageTemplateId;
	private String messageTemplateName;
	private String messageTemplateLanguage;
	private String previousCategory;
	private String newCategory;

	public Integer getMessageTemplateId() {
		return this.messageTemplateId;
	}

	public void setMessageTemplateId(Integer messageTemplateId) {
		this.messageTemplateId = messageTemplateId;
	}

	public String getMessageTemplateName() {
		return this.messageTemplateName;
	}

	public void setMessageTemplateName(String messageTemplateName) {
		this.messageTemplateName = messageTemplateName;
	}

	public String getMessageTemplateLanguage() {
		return this.messageTemplateLanguage;
	}

	public void setMessageTemplateLanguage(String messageTemplateLanguage) {
		this.messageTemplateLanguage = messageTemplateLanguage;
	}

	public String getPreviousCategory() {
		return this.previousCategory;
	}

	public void setPreviousCategory(String previousCategory) {
		this.previousCategory = previousCategory;
	}

	public String getNewCategory() {
		return this.newCategory;
	}

	public void setNewCategory(String newCategory) {
		this.newCategory = newCategory;
	}

	@Override
	public String toString() {
		return "Value [messageTemplateId=" + messageTemplateId + ", messageTemplateName=" + messageTemplateName
				+ ", messageTemplateLanguage=" + messageTemplateLanguage + ", previousCategory=" + previousCategory
				+ ", newCategory=" + newCategory + "]";
	}

}
