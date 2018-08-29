package com.coolpay.services;

import com.coolpay.entities.PaymentWrapper;

public interface PaymentService {

	PaymentWrapper sendMoney(PaymentWrapper payment);

}
