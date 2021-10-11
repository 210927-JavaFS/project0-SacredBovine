CREATE TABLE users (
	user_id SERIAL PRIMARY KEY,
	name_id INTEGER REFERENCES names(name_id),
	password_key VARCHAR(256) NOT NULL,
	email_address VARCHAR(50) UNIQUE NOT NULL,
	address_id INTEGER REFERENCES addresses(address_id),
	phone_number VARCHAR(15),
	user_type Integer,
	checking_account INTEGER REFERENCES accounts(account_number),
	savings_account INTEGER REFERENCES accounts(account_number),
	joint_account INTEGER REFERENCES accounts(account_number),
	new_user_flag BOOLEAN
);

CREATE TABLE names (
	name_id SERIAL PRIMARY KEY,
	first_name VARCHAR(30) NOT NULL,
	last_name VARCHAR(30) NOT NULL,
	new_name_flag BOOLEAN
);

CREATE TABLE addresses (
	address_id SERIAL PRIMARY KEY,
	street_number VARCHAR(10),
	street_name VARCHAR(50),
	city VARCHAR(50),
	region VARCHAR(50),
	zipcode VARCHAR(10),
	country VARCHAR(50),
	new_address_flag BOOLEAN
);

TRUNCATE users;
--DROP TABLE users, names, addresses;

INSERT INTO names (first_name, last_name, new_name_flag) VALUES ('Default', 'Admin', FALSE);
INSERT INTO addresses (street_number, street_name, city, region, zipcode, country, new_address_flag) 
	VALUES ('11111', 'business rd', 'home city', 'home state', '55555','USA', FALSE);
INSERT INTO users (name_id, password_key, email_address, address_id, phone_number, user_type, new_user_flag)
	VALUES (15, 'admin', 'admin@default.com', 17, '123-456-7890', 3, FALSE);
	
INSERT INTO names (first_name, last_name, new_name_flag) VALUES ('Default', 'Teller', FALSE);
INSERT INTO addresses (street_number, street_name, city, region, zipcode, country, new_address_flag) 
	VALUES ('11111', 'business rd', 'home city', 'home state', '55555','USA', FALSE);
INSERT INTO users (name_id, password_key, email_address, address_id, phone_number, user_type, new_user_flag)
	VALUES (16, 'teller', 'teller@default.com', 17, '123-456-7890', 2, FALSE);



--DELETE FROM names WHERE name_id < 14;
--DELETE FROM addresses WHERE address_id < 16;