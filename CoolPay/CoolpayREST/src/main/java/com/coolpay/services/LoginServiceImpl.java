package com.coolpay.services;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Override
	public String getToken() {
    	
    		String coolpayURL = "https://coolpay.herokuapp.com/api/login";

    		HttpHeaders httpHeaders = new HttpHeaders();
    		httpHeaders.set("Content-Type", "application/json");

    		JSONObject json = new JSONObject();
				try {
					json.put("username","WilsonL");
					json.put("apikey","4C4105EC33D8F6CA");
					System.out.println(json);
				} catch (JSONException e1) {
					e1.printStackTrace();
				}

    		HttpEntity <String> httpEntity = new HttpEntity <String> (json.toString(), httpHeaders);

    		RestTemplate restTemplate = new RestTemplate();
    		String response = restTemplate.postForObject(coolpayURL, httpEntity, String.class);

			JSONObject jsonObj = null;
			try {
				jsonObj = new JSONObject(response);
				System.out.println(jsonObj.toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
    
		return jsonObj.toString();
	}

}
