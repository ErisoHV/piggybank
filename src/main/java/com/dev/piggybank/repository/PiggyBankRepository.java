package com.dev.piggybank.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dev.piggybank.domain.CoinDenomination;
import com.dev.piggybank.domain.PiggyBank;

public interface PiggyBankRepository extends CrudRepository<PiggyBank, Integer>{

	@Query("SELECT total * VALUE FROM PiggyBank pb INNER JOIN CoinDenomination pm ON (pb.coinDenomination.id  = pm.id) WHERE pm.id=?1")
	public int getTotalAmountByCoinDenominationId(int coinDenominationId);
	
	@Query("SELECT total FROM PiggyBank pb WHERE pb.coinDenomination.id=?1") 
	public int getTotalByCoinDenominationId(int coinDenominationId);
	
	@Query("SELECT p FROM PiggyBank p WHERE p.id=?1")
	public PiggyBank findByIdCoinDenomination(int idCoinDenomination);
	
	public PiggyBank findByCoinDenomination(CoinDenomination coinDenomination);
	
	@Query("SELECT SUM(total * value) FROM PiggyBank pb INNER JOIN CoinDenomination pm ON (pb.coinDenomination.id  = pm.id)")
	public int getTotalAmount();
	
	@Query("SELECT SUM(total) FROM PiggyBank pb")
	public int getTotalCoins();
	
}
