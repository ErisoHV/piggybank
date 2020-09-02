package com.dev.piggybank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.dev.piggybank.services.PiggyBankService;


public class AppController {

	@Autowired
	PiggyBankService piggyBankService;
	
	@GetMapping(value= {"/index", "/", "/app"})
	public String getHomePage() {
		return "index";
	}
	
}
