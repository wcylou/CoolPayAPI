package com.coolpay.services;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.coolpay.entities.Payment;
import com.coolpay.entities.PaymentWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PaymentServiceImpl implements PaymentService {

	private static LoginService lsi;

	@Autowired
	public void setLoginServiceService(LoginService lsi) {
		PaymentServiceImpl.lsi = lsi;
	}

	ObjectMapper mapper = new ObjectMapper();
	RestTemplate restTemplate = new RestTemplate();

	@Override
	public PaymentWrapper sendMoney(PaymentWrapper payment) {
		String recipientJSON = "";
		try {
			recipientJSON = mapper.writeValueAsString(payment);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		restTemplate.postForObject("https://coolpay.herokuapp.com/api/payments",
				lsi.setHeadersPOST(recipientJSON), String.class);
		return payment;
	}

	@Override
	public ArrayList<Payment> listAllPayments() {
		ResponseEntity<String> response = restTemplate.exchange("https://coolpay.herokuapp.com/api/payments",
				HttpMethod.GET, lsi.setHeadersGET(), String.class);

		ArrayList<Payment> allPayments = new ArrayList<>();
		try {
			JSONObject jObject = new JSONObject(response.getBody());
			JSONArray ja = jObject.getJSONArray("payments");
			for (int i = 0; i < ja.length(); i++) {
				Payment p = new Payment();
				p.setAmount(ja.getJSONObject(i).getDouble("amount"));
				p.setCurrency(ja.getJSONObject(i).getString("currency"));
				p.setRecipientId(ja.getJSONObject(i).getString("recipient_id"));
				p.setStatus(ja.getJSONObject(i).getString("status"));
				p.setId(ja.getJSONObject(i).getString("id"));
				allPayments.add(p);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return allPayments;
	}

}
