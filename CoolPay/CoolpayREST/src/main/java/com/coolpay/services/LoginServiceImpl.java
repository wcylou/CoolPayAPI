package com.coolpay.services;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.coolpay.entities.Token;
import com.coolpay.entities.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LoginServiceImpl implements LoginService {
	
	ObjectMapper mapper = new ObjectMapper();
	RestTemplate restTemplate = new RestTemplate();
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertiesResolver() {
	    return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Override
	public String getToken(User user) {

		String recipientJSON = "";
		try {
			recipientJSON = mapper.writeValueAsString(user);
			System.out.println(recipientJSON);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		HttpEntity<String> request = new HttpEntity<String>(recipientJSON, headers);
		
		String response = restTemplate.postForObject("https://coolpay.herokuapp.com/api/login", 
				request, String.class);

		JSONObject jsonObj = null;
			try {
				jsonObj = new JSONObject(response);
				Token.setToken(jsonObj.getString("token"));
				System.out.println(jsonObj.getString("token"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		return jsonObj.toString();
	}
	
	@Override
	public HttpEntity<String> setHeadersGET() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Token.getToken());
		headers.set("Content-Type", "application/json");
		HttpEntity<String> request = new HttpEntity<String>(headers);
		return request;
	}
	
	@Override
	public HttpEntity<String> setHeadersPOST(String jsonString) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Authorization", "Bearer " + Token.getToken());
		headers.add("Content-Type", "application/json");
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		HttpEntity<String> request = new HttpEntity<String>(jsonString, headers);
		return request;
	}
}