package com.coolpay.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	
	ObjectMapper mapper = new ObjectMapper();
	private RestTemplate restTemplate = new RestTemplate();
	MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();

	
	@Override
	public RecipientWrapper createRecipient(RecipientWrapper recipient) {
		String recipientJSON = "";
		try {
//			recipientJSON = "{\"recipient\":" + mapper.writeValueAsString(recipient) + "}";
			recipientJSON = mapper.writeValueAsString(recipient);
			System.out.println(recipientJSON);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		headers.add("Authorization", "Bearer " + Token.getToken());
		headers.add("Content-Type", "application/json");

		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		HttpEntity<String> request = new HttpEntity<String>(recipientJSON, headers);
		String response = restTemplate.postForObject("https://coolpay.herokuapp.com/api/recipients", request, String.class);
		System.out.println(response);
		return recipient;
	}
	
	@Override
	public ArrayList<Recipient> searchRecipients(String recipientName) {
		ArrayList<Recipient> listRecipients = new ArrayList<>();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + Token.getToken());
		headers.set("Content-Type", "application/json");

		HttpEntity<String> request = new HttpEntity<String>(headers);

		Map<String, String> jsonParams = new HashMap<>();
		jsonParams.put("name", recipientName);
		
		ResponseEntity<String> response = restTemplate.exchange(
		    "https://coolpay.herokuapp.com/api/recipients?name={name}", HttpMethod.GET, request, String.class, jsonParams);
		
		System.out.println(response);
		
//		Map<String, String> newMap = new TreeMap<>();
//		
//		try
//		{
//		    JSONArray jsonArray = new JSONArray(response);
//
//		    for (int i = 0; i < jsonArray.length(); i++) {
//		        JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//		        String userName = jsonObject.getString("name");
//		        String userId = jsonObject.getString("id");
//		        newMap.put(userName, userId);
//		        System.out.println(newMap);
//
//		    }
//		} 
//		catch (JSONException e) 
//		{
//		    e.printStackTrace();
//		}
		return listRecipients;
		
	}

}
