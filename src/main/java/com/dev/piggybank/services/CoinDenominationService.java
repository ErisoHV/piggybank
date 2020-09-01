package com.dev.piggybank.services;

import org.springframework.stereotype.Service;

import com.dev.piggybank.domain.CoinDenomination;
import com.dev.piggybank.repository.CoinDenominationRepository;

@Service
public class CoinDenominationService {

	private final CoinDenominationRepository coinDenominationRepository;
	
	public CoinDenominationService(CoinDenominationRepository coinRepository) {
		this.coinDenominationRepository = coinRepository;
	}
	
	public CoinDenomination findByCoinDenominationId(int id){
		return this.coinDenominationRepository.findById(id).get();
	}
	
	public CoinDenomination findByCoinDenominationValue(int value){
		return this.coinDenominationRepository.findByValue(value);
	}
	
	public Iterable<CoinDenomination> findAll() {
		return this.coinDenominationRepository.findAll();
	}

}
