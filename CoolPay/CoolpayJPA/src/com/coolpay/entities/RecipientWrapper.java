package com.coolpay.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RecipientWrapper {
	
	@JsonProperty("recipient")
	private Recipient recipient;

	
	public RecipientWrapper() {
		recipient = new Recipient();
	}

	
	public RecipientWrapper(String recipientName) {
		this();
		recipient.setName(recipientName);
	}

	@JsonIgnore
	public String getName() {
		return recipient.getName();
	}

	public void setName(String recipientName) {
		recipient.setName(recipientName);
	}
	
	
}
