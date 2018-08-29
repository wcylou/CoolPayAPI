package com.coolpay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.coolpay.entities.Payment;
import com.coolpay.entities.PaymentWrapper;
import com.coolpay.entities.RecipientWrapper;
import com.coolpay.services.LoginServiceImpl;
import com.coolpay.services.PaymentServiceImpl;
import com.coolpay.services.RecipientServiceImpl;

@SpringBootApplication
public class CoolpayRestApplication {
	
	private static RecipientServiceImpl rsi;

	@Autowired
	public void setRecipientService(RecipientServiceImpl rsi) {
		CoolpayRestApplication.rsi = rsi;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CoolpayRestApplication.class, args);
		LoginServiceImpl lsi = new LoginServiceImpl();
		lsi.getToken();
		RecipientWrapper r = new RecipientWrapper("Wilson");
//		rsi.createRecipient(r);
		rsi.findSingleRecipient("Wilson");
		rsi.searchAllRecipients();
		PaymentServiceImpl psi = new PaymentServiceImpl();
		Payment p = new Payment(10.5, "GBP", "Wilson");
		String recipientId = rsi.findSingleRecipient(p.getName());
		PaymentWrapper pw = new PaymentWrapper(10.5, "GBP", recipientId);
		psi.sendMoney(pw);

	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	   return builder.build();
	}
}
