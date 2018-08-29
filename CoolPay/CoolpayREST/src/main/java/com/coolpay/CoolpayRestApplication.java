package com.coolpay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.client.RestTemplate;

import com.coolpay.entities.Payment;
import com.coolpay.entities.PaymentWrapper;
import com.coolpay.entities.Recipient;
import com.coolpay.entities.RecipientWrapper;
import com.coolpay.entities.User;
import com.coolpay.services.LoginService;
import com.coolpay.services.PaymentService;
import com.coolpay.services.RecipientService;

@SpringBootApplication
@PropertySource("classpath:application.properties") 
@Configuration
public class CoolpayRestApplication {
	
	private static RecipientService rsi;
	private static LoginService lsi;
	private static PaymentService psi;

	@Autowired
	public void setRecipientService(RecipientService rsi) {
		CoolpayRestApplication.rsi = rsi;
	}
	
	@Autowired
	public void setLoginService(LoginService lsi) {
		CoolpayRestApplication.lsi = lsi;
	}
	
	@Autowired
	public void setPaymentService(PaymentService psi) {
		CoolpayRestApplication.psi = psi;
	}
	
	@Value("${coolpay_api}")
	private static String keyName;
	
	   @Bean
	    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
	        return new PropertySourcesPlaceholderConfigurer();
	    }
	
	public static void main(String[] args) {
		SpringApplication.run(CoolpayRestApplication.class, args);
		User u = new User("WilsonL", "4C4105EC33D8F6CA");
		lsi.getToken(u);
		RecipientWrapper r = new RecipientWrapper("Bob");
		rsi.createRecipient(r);
		rsi.findSingleRecipient("Wilson");
		rsi.searchAllRecipients();
		Payment p = new Payment(10.5, "GBP", "Bob");
		Recipient recipient = rsi.findSingleRecipient(p.getName());
		PaymentWrapper pw = new PaymentWrapper(10.5, "GBP", recipient.getId());
		psi.sendMoney(pw);
		psi.listAllPayments();

	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	   return builder.build();
	}
}
