package com.coolpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.coolpay.entities.Recipient;
import com.coolpay.services.LoginServiceImpl;

@SpringBootApplication
public class CoolpayRestApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CoolpayRestApplication.class, args);
		LoginServiceImpl lsi = new LoginServiceImpl();
		lsi.getToken();
		Recipient r = new Recipient("Wilson");
		lsi.createRecipient(r);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	   return builder.build();
	}
}
