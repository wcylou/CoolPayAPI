package com.coolpay.services;

import org.springframework.http.HttpEntity;

import com.coolpay.entities.User;

public interface LoginService {
	
	HttpEntity<String> setHeadersGET();

	HttpEntity<String> setHeadersPOST(String jsonString);

	String getToken(User user);
}
