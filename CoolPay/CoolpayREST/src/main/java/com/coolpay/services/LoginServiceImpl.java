package com.coolpay.services;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.coolpay.entities.Recipient;
import com.coolpay.entities.Token;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Configuration
@PropertySource("application.properties")
public class LoginServiceImpl implements LoginService {
	
//	@Value("4C4105EC33D8F6CA")
	private String keyName = "4C4105EC33D8F6CA";
	
	private RestTemplate restTemplate = new RestTemplate();
	private HttpHeaders httpHeaders = new HttpHeaders();
	Token t = new Token();
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertiesResolver() {
	    return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Override
	public String getToken() {
    	
    		String coolpayURL = "https://coolpay.herokuapp.com/api/login";

    		httpHeaders = new HttpHeaders();
    		httpHeaders.set("Content-Type", "application/json");

    		JSONObject json = new JSONObject();
				try {
					json.put("username","WilsonL");
					json.put("apikey", keyName);
					System.out.println(json);
				} catch (JSONException e1) {
					e1.printStackTrace();
				}

    		HttpEntity <String> httpEntity = new HttpEntity <String> (json.toString(), httpHeaders);
    		System.out.println(httpEntity);

    		String response = restTemplate.postForObject(coolpayURL, httpEntity, String.class);

			JSONObject jsonObj = null;
			try {
				jsonObj = new JSONObject(response);
				System.out.println(jsonObj.toString());
				System.out.println(jsonObj.getString("token"));
				t.setToken(jsonObj.getString("token"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		return jsonObj.toString();
	}
	
	@Override
	public Recipient createRecipient(Recipient recipient) {
		ObjectMapper mapper = new ObjectMapper();
		String recipientJSON = "";
		try {
			recipientJSON = "{\"recipient\":" + mapper.writeValueAsString(recipient) + "}";
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				System.out.println(recipientJSON);
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Authorization", "Bearer " + t.getToken());
		headers.add("Content-Type", "application/json");

		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		HttpEntity<String> request = new HttpEntity<String>(recipientJSON, headers);
		String response = restTemplate.postForObject("https://coolpay.herokuapp.com/api/recipients", request, String.class);
		System.out.println(response);
		return null;
	}

}
