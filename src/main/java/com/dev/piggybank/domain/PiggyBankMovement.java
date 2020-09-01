package com.dev.piggybank.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="piggybank_movement")
public class PiggyBankMovement {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int movementValue;
	@Column(name = "movement_date", nullable = false, 
			insertable = false, columnDefinition = "DATE DEFAULT CURRENT_TIMESTAMP")
	private Date movementDate;
	
	@JoinColumn(name = "id_coindenomination")
	@ManyToOne
	private CoinDenomination coinDenomination;
	
}
