package com.dev.piggybank.repository;

import org.springframework.data.repository.CrudRepository;

import com.dev.piggybank.domain.CoinDenomination;

public interface CoinDenominationRepository extends CrudRepository<CoinDenomination, Integer>{

	public CoinDenomination findByValue(int value);
	
}
