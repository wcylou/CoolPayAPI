package com.coolpay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coolpay.entities.RecipientWrapper;
import com.coolpay.services.RecipientService;

@CrossOrigin({"*", "http://localhost:4200"})
@RequestMapping("api/")
@RestController
public class RecipientController {
	
	@Autowired
	RecipientService rServe;
	
	@RequestMapping(path="recipients", method= RequestMethod.POST)
	public RecipientWrapper createRecipient(@RequestBody RecipientWrapper recipient) {
		return rServe.createRecipient(recipient);
	}
	
	@RequestMapping(path="recipients/{name}", method= RequestMethod.GET)
	public String findSingleRecipient(@PathVariable String name) {
		return rServe.findSingleRecipient(name);
	}

}
