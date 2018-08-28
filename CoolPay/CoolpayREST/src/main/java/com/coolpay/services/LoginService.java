package com.coolpay.services;

import com.coolpay.entities.Recipient;

public interface LoginService {
	
	String getToken();

	Recipient createRecipient(Recipient recipient);

}
