package com.coolpay.services;

import java.util.List;


import com.coolpay.entities.Recipient;
import com.coolpay.entities.RecipientWrapper;

public interface RecipientService {

	List<Recipient> searchRecipients(String recipientName);

	RecipientWrapper createRecipient(RecipientWrapper recipient);

}
