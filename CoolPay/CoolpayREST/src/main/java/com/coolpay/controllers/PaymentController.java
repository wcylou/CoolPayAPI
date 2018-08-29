package com.coolpay.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coolpay.entities.Payment;
import com.coolpay.entities.PaymentWrapper;
import com.coolpay.services.PaymentService;

@CrossOrigin({ "*", "http://localhost:4200" })
@RequestMapping("api/")
@RestController
public class PaymentController {

	@Autowired
	PaymentService pServe;

	@RequestMapping(path = "payments", method = RequestMethod.POST)
	public PaymentWrapper sendMoney(@RequestBody PaymentWrapper payment) {
		return pServe.sendMoney(payment);
	}

	@RequestMapping(path = "payments", method = RequestMethod.GET)
	public ArrayList<Payment> listAllPayments() {
		return pServe.listAllPayments();
	}

}
