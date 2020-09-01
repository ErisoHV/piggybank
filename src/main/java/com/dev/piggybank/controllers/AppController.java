package com.dev.piggybank.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class AppController {

	
	@GetMapping(value= {"/index", "/"})
	public String getHomePage() {
		return "index";
	}
	
	
	
}
