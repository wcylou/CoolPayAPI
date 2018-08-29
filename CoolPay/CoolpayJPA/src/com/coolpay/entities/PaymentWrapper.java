package com.coolpay.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentWrapper {
	
	@JsonProperty("payment")
	private Payment payment;
	
	public PaymentWrapper() {
		payment = new Payment();
	}
	
	public PaymentWrapper(double amount, String currency, String id) {
		this();
		payment.setAmount(amount);
		payment.setCurrency(currency);
		payment.setId(id);
	}


}