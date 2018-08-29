package com.coolpay;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.coolpay.controllers.PaymentController;
import com.coolpay.controllers.RecipientController;
import com.coolpay.entities.PaymentWrapper;
import com.coolpay.entities.User;
import com.coolpay.services.LoginService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoolpayRestApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Autowired
	private RecipientController recipientController;
	
	@Autowired
	private PaymentController paymentController;
	
	private static LoginService lsi;

	@Autowired
	public void setLoginServiceService(LoginService lsi) {
		CoolpayRestApplicationTests.lsi = lsi;
	}
	
	@Test
	@DisplayName("Test Search For Recipient")
	public void testSearchForRecipient() {
		User u = new User("", "");
		lsi.getToken(u);
		assertEquals("Bob", recipientController.findSingleRecipient("Bob").getName());
	}
	
	@Test
	@DisplayName("Test Send Payment")
	public void testSendPayment() {
		User u = new User("", "");
		lsi.getToken(u);
		PaymentWrapper payment = new PaymentWrapper(10.5, "GBP", "6e7b146e-5957-11e6-8b77-86f30ca893d3");
		assertEquals("GBP", paymentController.sendMoney(payment).getCurrency());
	}
	
	@Test
	@DisplayName("Test List All Payments")
	public void testListAllPayments() {
		User u = new User("", "");
		lsi.getToken(u);
		assertEquals(15, paymentController.listAllPayments().size());
	}

}
