package com.dev.piggybank.services;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.piggybank.domain.PiggyBankMovement;
import com.dev.piggybank.repository.PiggyBankMovementRepository;

@Service
public class MovementService {
	
	private final PiggyBankMovementRepository piggyBankMovementRepository;

	public MovementService(PiggyBankMovementRepository piggyBankMovementRepository) {
		this.piggyBankMovementRepository = piggyBankMovementRepository;
	}
	
	public PiggyBankMovement findByPiggyBankMovementId(int id) {
		return this.piggyBankMovementRepository.findById(id).get();
	}
	
	public Iterable<PiggyBankMovement> findAllMovement(){
		return this.piggyBankMovementRepository.findAll();
	}
	
	public List<PiggyBankMovement> findByDate(Date movementDate) {
		return this.piggyBankMovementRepository.findByMovementDate(movementDate);
	}
	
}
