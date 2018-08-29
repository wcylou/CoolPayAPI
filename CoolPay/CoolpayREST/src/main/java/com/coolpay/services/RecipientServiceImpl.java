package com.coolpay.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.persistence.sessions.serializers.JSONSerializer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.coolpay.entities.Recipient;
import com.coolpay.entities.RecipientWrapper;
import com.coolpay.entities.Token;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RecipientServiceImpl implements RecipientService {
	
	@Autowired
	LoginServiceImpl lsi;
	
	ObjectMapper mapper = new ObjectMapper();
	private RestTemplate restTemplate = new RestTemplate();

	
	@Override
	public RecipientWrapper createRecipient(RecipientWrapper recipient) {
		String recipientJSON = "";
		try {
			recipientJSON = mapper.writeValueAsString(recipient);
			System.out.println(recipientJSON);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		
		headers.add("Authorization", "Bearer " + Token.getToken());
		headers.add("Content-Type", "application/json");

		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		HttpEntity<String> request = new HttpEntity<String>(recipientJSON, headers);
		String response = restTemplate.postForObject("https://coolpay.herokuapp.com/api/recipients", request, String.class);
		System.out.println(response);
		return recipient;
	}
	
	@Override
	public String findSingleRecipient(String recipientName) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Token.getToken());
		headers.set("Content-Type", "application/json");
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		Map<String, String> jsonParams = new HashMap<>();
		jsonParams.put("name", recipientName);
		
		ResponseEntity<String> response = restTemplate.exchange(
		    "https://coolpay.herokuapp.com/api/recipients?name={name}", HttpMethod.GET, request, String.class, jsonParams);
		
		String id = "";
		try {
			JSONObject jObject  = new JSONObject(response.getBody());
			JSONArray ja = jObject.getJSONArray("recipients");
			JSONObject first = ja.getJSONObject(0);
			id = first.getString("id"); 
		} catch (JSONException e) {
			e.printStackTrace();
		}

		System.out.println("SINGLE RECIPIENT" + response);
		return id;
	}
	
	@Override
	public ArrayList<Recipient> searchAllRecipients() {
		ArrayList<Recipient> listRecipients = new ArrayList<>();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Token.getToken());
		headers.set("Content-Type", "application/json");
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		ResponseEntity<String> response = restTemplate.exchange(
		    "https://coolpay.herokuapp.com/api/recipients", HttpMethod.GET, request, String.class);
		
		System.out.println("ALLRECIPIENTS" + response);
		return listRecipients;
	}

}
