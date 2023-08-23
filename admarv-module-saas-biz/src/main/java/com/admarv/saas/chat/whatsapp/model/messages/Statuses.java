package com.admarv.saas.chat.whatsapp.model.messages;

import java.util.List;

public class Statuses{

    private String	id;
    private String	status;
    private String	timestamp;
    private String	recipientId;
    private List<Errors> errors;

    public String getId() {
      return this.id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getStatus() {
      return this.status;
    }

    public void setStatus(String status) {
      this.status = status;
    }

    public String getTimestamp() {
      return this.timestamp;
    }

    public void setTimestamp(String timestamp) {
      this.timestamp = timestamp;
    }

    public String getRecipientId() {
      return this.recipientId;
    }

    public void setRecipientId(String recipientId) {
      this.recipientId = recipientId;
    }

    public List<Errors> getErrors() {
      return this.errors;
    }

    public void setErrors(List<Errors> errors) {
      this.errors = errors;
    }

	@Override
	public String toString() {
		return "Statuses [id=" + id + ", status=" + status + ", timestamp=" + timestamp + ", recipientId=" + recipientId
				+ ", errors=" + errors + "]";
	}

}

