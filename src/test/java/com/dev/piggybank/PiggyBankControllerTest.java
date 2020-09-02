package com.dev.piggybank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dev.piggybank.controllers.PiggyBankController;
import com.dev.piggybank.response.PiggyBankTotalsResponse;
import com.dev.piggybank.services.MovementService;
import com.dev.piggybank.services.PiggyBankService;

@ExtendWith(MockitoExtension.class)
class PiggyBankControllerTest {

	@Mock
	private PiggyBankService piggyBankService;
	
	@Mock
	private MovementService movementService;
	
	private PiggyBankController controller;
	
	private PiggyBankTotalsResponse response;

	@BeforeEach
	public void configure() {
		controller = new PiggyBankController(movementService, piggyBankService);
		response = new PiggyBankTotalsResponse(100, 2);
	}
	
	@Test
	public void getTotalAmountByCoinDenominationIdTest() {
		Mockito.when(piggyBankService.getTotalAmountByCoinDenominationId(Mockito.anyInt())).thenReturn(response.getTotalAmout());
		Mockito.when(piggyBankService.getTotalByCoinDenominationId(Mockito.anyInt())).thenReturn(response.getTotalCoins());
		
		ResponseEntity<PiggyBankTotalsResponse> totalsByDenomination = controller.getTotalsByDenomination(10);
		
		assertNotEquals(Matchers.notNullValue(), totalsByDenomination);
		assertNotEquals(Matchers.notNullValue(), totalsByDenomination.getBody());
		assertEquals(HttpStatus.OK, totalsByDenomination.getStatusCode());
		assertEquals(response, totalsByDenomination.getBody());
	}
	
	@Test
	public void getTotalsTest() {
		Mockito.when(piggyBankService.getTotalAmount()).thenReturn(response.getTotalAmout());
		Mockito.when(piggyBankService.getTotalCoins()).thenReturn(response.getTotalCoins());

		ResponseEntity<PiggyBankTotalsResponse> totalsByDenomination = controller.getTotals();
		
		assertNotEquals(Matchers.notNullValue(), totalsByDenomination);
		assertNotEquals(Matchers.notNullValue(), totalsByDenomination.getBody());
		assertEquals(HttpStatus.OK, totalsByDenomination.getStatusCode());
		assertEquals(response, totalsByDenomination.getBody());
	}

}
