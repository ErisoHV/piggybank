DROP TABLE IF EXISTS piggybank_movement;

DROP TABLE IF EXISTS piggybank;

DROP TABLE IF EXISTS coin_denomination;

-- Denominacion de las monedas
CREATE TABLE coin_denomination (
	id INT IDENTITY(1,1) PRIMARY KEY,
	value INT NOT NULL,
	description VARCHAR(250)
);

INSERT INTO coin_denomination (value, description) VALUES
			(50, 'El grabado en el anverso es el escudo de armas de Colombia. En el reverso, la denominación “50” en números arábigos, debajo  la palabra “PESOS” y el conjunto rodeando de una corona de laurel abierta.'),
			(100, ''),
			(200, ''),
			(500, ''),
			(1000, '');
--

-- Movimientos dentro del piggybank
CREATE TABLE piggybank_movement (
	id INT IDENTITY(1,1) PRIMARY KEY,
	id_coindenomination INT NOT NULL,
	movement_value INT NOT NULL,
	movement_date DATE NOT NULL DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (id_coindenomination) REFERENCES coin_denomination(id)
);
--

-- Piggy Bank
CREATE TABLE piggybank (
	id INT IDENTITY(1,1) PRIMARY KEY,
	id_coindenomination INT NOT NULL,
	total INT NOT NULL DEFAULT 0,
	FOREIGN KEY (id_coindenomination) REFERENCES coin_denomination(id)
);

INSERT INTO piggybank (id_coindenomination) 
SELECT id FROM coin_denomination;

--