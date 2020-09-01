package com.dev.piggybank.services;

import org.springframework.stereotype.Service;

import com.dev.piggybank.domain.PiggyBank;
import com.dev.piggybank.domain.PiggyBankMovement;
import com.dev.piggybank.exceptions.InvalidMovementValueException;
import com.dev.piggybank.repository.PiggyBankMovementRepository;
import com.dev.piggybank.repository.PiggyBankRepository;

@Service
public class PiggyBankService {
	
	private final PiggyBankRepository piggyBankRepository;
	
	private final PiggyBankMovementRepository piggyBankMovementRepository;
	
	public PiggyBankService(PiggyBankRepository piggyBankRepository, PiggyBankMovementRepository piggyBankMovementRepository) {
		this.piggyBankRepository = piggyBankRepository;
		this.piggyBankMovementRepository = piggyBankMovementRepository;
	}
	
	public int getTotalAmountByCoinDenominationId (int coinDenominationId) {
		return this.piggyBankRepository.getTotalAmountByCoinDenominationId(coinDenominationId);
	}
	
	public int getTotalAmount() {
		return this.piggyBankRepository.getTotalAmount();
	}
	
	public PiggyBank getTotalCoinsByCoinDenominationId (int coinDenominationId) {
		return this.piggyBankRepository.findByIdCoinDenomination(coinDenominationId);
	}
	
	public int getTotalCoins() {
		return this.piggyBankRepository.getTotalAmount();
	}
	
	public PiggyBank saveToPiggyBank(PiggyBankMovement movement) {
		
		// piggy bank current status
		PiggyBank piggyBank = piggyBankRepository.findByCoinDenomination(movement.getCoinDenomination());
		
		// remove coins validation : not enough coins
		if (movement.getMovementValue() < 0 && piggyBank.getTotal() < (movement.getMovementValue() * -1)) {
			throw new InvalidMovementValueException(movement.getCoinDenomination());
		}
		
		// 1. movement
		piggyBankMovementRepository.save(movement);
		
		// 2. total
		piggyBank.setTotal(piggyBank.getTotal() + movement.getMovementValue());
		return piggyBankRepository.save(piggyBank);
		
	}

}
