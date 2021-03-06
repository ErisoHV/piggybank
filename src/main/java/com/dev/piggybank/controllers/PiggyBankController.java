package com.dev.piggybank.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.piggybank.domain.PiggyBank;
import com.dev.piggybank.domain.PiggyBankMovement;
import com.dev.piggybank.response.PiggyBankTotalsResponse;
import com.dev.piggybank.services.MovementService;
import com.dev.piggybank.services.PiggyBankService;

@RestController
@RequestMapping("api/piggybank")
public class PiggyBankController {
	
	@Autowired
	MovementService movementService;
	
	@Autowired
	PiggyBankService piggyBankService;
	
	public PiggyBankController(MovementService movementService, PiggyBankService piggyBankService) {
		this.movementService = movementService;
		this.piggyBankService = piggyBankService;
	}
	
	@GetMapping(value="/movements", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PiggyBankMovement>> getMovements() {
		List<PiggyBankMovement> movementList = new ArrayList<PiggyBankMovement>();
		movementService.findAllMovement().forEach(movementList::add);
		
		return new ResponseEntity<List<PiggyBankMovement>> (movementList, HttpStatus.OK);
	}
	
	@GetMapping(value="/movements/date/{date}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PiggyBankMovement>> getMovementsByDate(@PathVariable("date") 
																	  @DateTimeFormat(pattern = "dd-MM-yyyy") Date date) {
		return new ResponseEntity<List<PiggyBankMovement>>(movementService.findByDate(date), HttpStatus.OK);
	}
	
	@GetMapping(value="/amount/{coinDenominationId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> getPiggyBankTotalAmount(@PathVariable("coinDenominationId") int coinDenominationId) {
		return new ResponseEntity<Integer>(piggyBankService.getTotalAmountByCoinDenominationId(coinDenominationId), HttpStatus.OK);
	}
	
	@GetMapping(value="/amount", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> getPiggyBankTotalAmount() {
		return new ResponseEntity<Integer>(piggyBankService.getTotalAmount(), HttpStatus.OK);
	}
	
	@GetMapping(value="/total/{coinDenominationId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PiggyBank> getPiggyBankTotalCoins(@PathVariable("coinDenominationId") int coinDenominationId) {
		return new ResponseEntity<PiggyBank>(piggyBankService.getTotalCoinsByCoinDenominationId(coinDenominationId), HttpStatus.OK);
	}
	
	@GetMapping(value="/total", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> getPiggyBankTotalCoins() {
		return new ResponseEntity<Integer>(piggyBankService.getTotalCoins(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/add", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PiggyBank> addCoinsToPiggyBank(@RequestBody PiggyBankMovement movement) {
		return new ResponseEntity<PiggyBank>(piggyBankService.saveToPiggyBank(movement), HttpStatus.OK);
	}
	
	@PostMapping(value = "/remove", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PiggyBank> removeCoinsFromPiggyBank(@RequestBody PiggyBankMovement movement) {
		movement.setMovementValue(movement.getMovementValue() * -1);
		return new ResponseEntity<PiggyBank>(piggyBankService.saveToPiggyBank(movement), HttpStatus.OK);
	}
	
	@GetMapping(value= "/totals")
	public ResponseEntity<PiggyBankTotalsResponse> getTotals(){
		PiggyBankTotalsResponse response = new PiggyBankTotalsResponse(piggyBankService.getTotalAmount(), piggyBankService.getTotalCoins());
		return new ResponseEntity<PiggyBankTotalsResponse>(response, HttpStatus.OK);
	}
	
	@GetMapping(value= "/totals/{coinDenominationId}")
	public ResponseEntity<PiggyBankTotalsResponse> getTotalsByDenomination(@PathVariable("coinDenominationId") int coinDenominationId){
		PiggyBankTotalsResponse response = new PiggyBankTotalsResponse(
				piggyBankService.getTotalAmountByCoinDenominationId(coinDenominationId), 
				piggyBankService.getTotalByCoinDenominationId(coinDenominationId));
		return new ResponseEntity<PiggyBankTotalsResponse>(response, HttpStatus.OK);
	}

}
