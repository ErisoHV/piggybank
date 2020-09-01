package com.dev.piggybank.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.piggybank.domain.CoinDenomination;
import com.dev.piggybank.services.CoinDenominationService;

@RestController
@RequestMapping("api/denomination")
public class CoinDenominationController {

	@Autowired
	CoinDenominationService coinService;
	
	
	@GetMapping(value="/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CoinDenomination>> getCoinDenominationsList() {
		
		List<CoinDenomination> denominationList = new ArrayList<CoinDenomination>();
		coinService.findAll().forEach(denominationList::add);
		
		return new ResponseEntity<List<CoinDenomination>>(denominationList, HttpStatus.OK);
	}
	
	@GetMapping(value="/{value}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CoinDenomination> getCoinDenominationByValue(@PathVariable("value") Integer value) {
		return new ResponseEntity<CoinDenomination>(coinService.findByCoinDenominationValue(value), HttpStatus.OK);
	}
	
}
