package com.dev.piggybank.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dev.piggybank.domain.PiggyBankMovement;

public interface PiggyBankMovementRepository extends CrudRepository<PiggyBankMovement, Integer>{
	
	@Query(value = "SELECT p FROM PiggyBankMovement p WHERE truncate(movement_date) = truncate(?1)")
	public List<PiggyBankMovement> findByMovementDate(Date movementDate);
	
}
