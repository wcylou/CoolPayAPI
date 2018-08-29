package com.coolpay.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.coolpay.entities.Payment;
import com.coolpay.entities.Recipient;
import com.coolpay.entities.RecipientWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RecipientServiceImpl implements RecipientService {

	private static LoginService lsi;

	@Autowired
	public void setLoginServiceService(LoginService lsi) {
		RecipientServiceImpl.lsi = lsi;
	}

	ObjectMapper mapper = new ObjectMapper();
	RestTemplate restTemplate = new RestTemplate();

	@Override
	public RecipientWrapper createRecipient(RecipientWrapper recipient) {
		String recipientJSON = "";
//		if (findSingleRecipient(recipient.getName()) != null) {
//			System.out.println("Recipient with name already exists");
//		}
//		else {
			try {
				recipientJSON = mapper.writeValueAsString(recipient);
				System.out.println(recipientJSON);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			String response = restTemplate.postForObject("https://coolpay.herokuapp.com/api/recipients",
					lsi.setHeadersPOST(recipientJSON), String.class);
			System.out.println("CREATED RECIPIENT" + response);
//		}
		return recipient;
	}

	@Override
	public Recipient findSingleRecipient(String recipientName) {
		Map<String, String> jsonParams = new HashMap<>();
		jsonParams.put("name", recipientName);

		ResponseEntity<String> response = restTemplate.exchange(
				"https://coolpay.herokuapp.com/api/recipients?name={name}", HttpMethod.GET, lsi.setHeadersGET(),
				String.class, jsonParams);

		String id = "";
		Recipient recipient = new Recipient();
		try {
			JSONObject jObject = new JSONObject(response.getBody());
			JSONArray ja = jObject.getJSONArray("recipients");
			recipient.setId(ja.getJSONObject(0).getString("id"));
			recipient.setName(ja.getJSONObject(0).getString("name"));
			}
		catch (JSONException e) {
			e.printStackTrace();
		}

		System.out.println("SINGLE RECIPIENT" + response);
		return recipient;
	}

	@Override
	public ArrayList<Recipient> searchAllRecipients() {
		ArrayList<Recipient> listRecipients = new ArrayList<>();
		ResponseEntity<String> response = restTemplate.exchange("https://coolpay.herokuapp.com/api/recipients",
				HttpMethod.GET, lsi.setHeadersGET(), String.class);
		System.out.println("ALLRECIPIENTS" + response);
		return listRecipients;
	}

}
