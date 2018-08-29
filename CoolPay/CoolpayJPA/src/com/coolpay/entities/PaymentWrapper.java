package com.coolpay.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentWrapper {

	@JsonProperty("payment")
	private Payment payment;

	public PaymentWrapper() {
		payment = new Payment();
	}

	public PaymentWrapper(double amount, String currency, String recipientId) {
		payment.setAmount(amount);
		payment.setCurrency(currency);
		payment.setRecipientId(recipientId);
	}
	
	public double getAmount() {
		return payment.getAmount();
	}
	
	public String getCurrency() {
		return payment.getCurrency();
	}
	
	public String getRecipientId() {
		return payment.getRecipientId();
	}


}
