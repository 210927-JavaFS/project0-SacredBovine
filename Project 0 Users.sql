CREATE TABLE users (
	user_id SERIAL PRIMARY KEY,
	name_id INTEGER REFERENCES names(name_id),
	password_key VARCHAR(256) NOT NULL,
	email_address VARCHAR(50) UNIQUE NOT NULL,
	address_id INTEGER REFERENCES addresses(address_id),
	phone_number VARCHAR(15),
	user_type Integer,
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

--ALTER TABLE users ADD COLUMN user_accounts_id INTEGER UNIQUE;
ALTER TABLE users DROP COLUMN user_accounts_id;

TRUNCATE names CASCADE;
TRUNCATE addresses  CASCADE;
DROP TABLE names, addresses, users CASCADE;

TRUNCATE names CASCADE;


INSERT INTO names (first_name, last_name, new_name_flag) VALUES ('Willie', 'Nelson', FALSE);
INSERT INTO addresses (street_number, street_name, city, region, zipcode, country, new_address_flag) 
	VALUES ('54321', 'Willie rd', 'San Francisco', 'CA', '22222','USA', FALSE);
INSERT INTO users (name_id, password_key, email_address, address_id, phone_number, user_type, new_user_flag)
	VALUES (5, 'password', 'willie@nelson.com', 2, '123-456-7890', 1, FALSE);

INSERT INTO names (first_name, last_name, new_name_flag) VALUES ('Default', 'Admin', FALSE);
INSERT INTO addresses (street_number, street_name, city, region, zipcode, country, new_address_flag) 
	VALUES ('11111', 'business rd', 'home city', 'home state', '55555','USA', FALSE);
INSERT INTO users (name_id, password_key, email_address, address_id, phone_number, user_type, new_user_flag)
	VALUES (6, 'admin', 'admin@default.com', 3, '123-456-7890', 1, FALSE);
	
INSERT INTO names (first_name, last_name, new_name_flag) VALUES ('Default', 'Teller', FALSE);
INSERT INTO addresses (street_number, street_name, city, region, zipcode, country, new_address_flag) 
	VALUES ('11111', 'business rd', 'home city', 'home state', '55555','USA', FALSE);
INSERT INTO users (name_id, password_key, email_address, address_id, phone_number, user_type, new_user_flag)
	VALUES (7, 'teller', 'teller@default.com',4, '123-456-7890', 2, FALSE);

INSERT INTO names (first_name, last_name, new_name_flag) VALUES ('Ron', 'Pearlman', FALSE);
INSERT INTO addresses (street_number, street_name, city, region, zipcode, country, new_address_flag) 
	VALUES ('66666', 'Ronnie rd', 'new York', 'NY', '66666','other USA', FALSE);
INSERT INTO users (name_id, password_key, email_address, address_id, phone_number, user_type, new_user_flag)
	VALUES (8, 'hellboy', 'ron@mail.com',5, '666-666-6666', 3, FALSE);



--DELETE FROM names WHERE name_id < 14;
--DELETE FROM addresses WHERE address_id < 16;