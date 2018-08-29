package com.coolpay.services;

import java.util.ArrayList;

import com.coolpay.entities.Recipient;
import com.coolpay.entities.RecipientWrapper;

public interface RecipientService {

	RecipientWrapper createRecipient(RecipientWrapper recipient);

	ArrayList<Recipient> searchAllRecipients();

	Recipient findSingleRecipient(String recipientName);

}
