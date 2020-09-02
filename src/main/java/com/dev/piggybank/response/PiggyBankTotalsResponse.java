package com.dev.piggybank.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PiggyBankTotalsResponse {

	int totalAmout;
	int totalCoins;
	
}
