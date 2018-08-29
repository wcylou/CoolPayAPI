package com.coolpay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.coolpay.entities.Payment;
import com.coolpay.entities.RecipientWrapper;
import com.coolpay.services.LoginServiceImpl;
import com.coolpay.services.PaymentServiceImpl;
import com.coolpay.services.RecipientServiceImpl;

@SpringBootApplication
public class CoolpayRestApplication {
	
	@Autowired
	LoginServiceImpl lsi2;
	
	public static void main(String[] args) {
		SpringApplication.run(CoolpayRestApplication.class, args);
		LoginServiceImpl lsi = new LoginServiceImpl();
		lsi.getToken();
		RecipientServiceImpl rsi = new RecipientServiceImpl();
		RecipientWrapper r = new RecipientWrapper("Wilson");
		rsi.createRecipient(r);
		rsi.searchRecipients("Wilson");
		PaymentServiceImpl psi = new PaymentServiceImpl();
		Payment p = new Payment(10.5, "GBP", "6e7b146e-5957-11e6-8b77-86f30ca893d3");
		psi.sendMoney(p);

	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	   return builder.build();
	}
}
