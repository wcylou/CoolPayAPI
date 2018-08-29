package com.coolpay.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.coolpay.entities.PaymentWrapper;
import com.coolpay.entities.Token;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PaymentServiceImpl implements PaymentService {


	ObjectMapper mapper = new ObjectMapper();
	private RestTemplate restTemplate = new RestTemplate();
	MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();

	@Override
	public PaymentWrapper sendMoney(PaymentWrapper payment) {
		String recipientJSON = "";
		try {
			recipientJSON = mapper.writeValueAsString(payment);
			System.out.println(recipientJSON);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();

		headers.add("Authorization", "Bearer " + Token.getToken());
		headers.add("Content-Type", "application/json");

		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		HttpEntity<String> request = new HttpEntity<String>(recipientJSON, headers);
		String response = restTemplate.postForObject("https://coolpay.herokuapp.com/api/payments", request,
				String.class);
		System.out.println("PAYMENT RESPONSE" + response);
		return payment;
	}

}
