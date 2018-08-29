package com.coolpay.services;

import java.util.ArrayList;

import com.coolpay.entities.Payment;
import com.coolpay.entities.PaymentWrapper;

public interface PaymentService {

	PaymentWrapper sendMoney(PaymentWrapper payment);

	ArrayList<Payment> listAllPayments();

}
