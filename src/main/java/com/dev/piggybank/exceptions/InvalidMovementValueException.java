package com.dev.piggybank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dev.piggybank.domain.CoinDenomination;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class InvalidMovementValueException extends RuntimeException{	

	private static final long serialVersionUID = 1L;
	
	public InvalidMovementValueException(CoinDenomination coinDenomination) {
		super("No hay suficientes monedas de " + coinDenomination.getValue() + " pesos en la alcancía");
	}


}
