package com.coolpay.services;

import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.coolpay.entities.Payment;
import com.coolpay.entities.Token;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	ObjectMapper mapper = new ObjectMapper();
	private RestTemplate restTemplate = new RestTemplate();
	MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
	
	@Override
	public Payment sendMoney(Payment payment) {
		
		try {
			// Convert object to JSON string
			String jsonInString = mapper.writeValueAsString(payment);
			System.out.println(jsonInString);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		headers.add("Authorization", "Bearer " + Token.getToken());
		headers.add("Content-Type", "application/json");

		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		HttpEntity<String> request = new HttpEntity<String>(recipientJSON, headers);
		String response = restTemplate.postForObject("https://coolpay.herokuapp.com/api/recipients", request, String.class);
		System.out.println(response);
		return recipient;
		String response = restTemplate.postForObject(coolpayURL, httpEntity, String.class);

		return restTemplate.postForObject("https://coolpay.herokuapp.com/api/payments", payment, Payment.class);
	}

}
