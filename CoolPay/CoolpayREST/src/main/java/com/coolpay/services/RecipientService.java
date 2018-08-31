package com.coolpay.services;


import com.coolpay.entities.Recipient;
import com.coolpay.entities.RecipientWrapper;

public interface RecipientService {

	RecipientWrapper createRecipient(RecipientWrapper recipient);

	Recipient findSingleRecipient(String recipientName);

}
