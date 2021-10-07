CREATE TABLE users (
	user_id SERIAL PRIMARY KEY, 
	password_key VARCHAR(50) NOT NULL,
	email_address VARCHAR(30) UNIQUE NOT NULL,
	home_address VARCHAR(150),
	phone_number VARCHAR(150)
	/* home address and user name will be separate tables*/
);

CREATE TABLE names (
	name_id INTEGER PRIMARY KEY,
	first_name VARCHAR(30) NOT NULL,
	last_name VARCHAR(30) NOT NULL
);

CREATE TABLE addresses (
	address_id INTEGER PRIMARY KEY,
	street_number VARCHAR(10),
	street_name VARCHAR(50),
	city VARCHAR(50),
	region VARCHAR(50),
	zipcode VARCHAR(10),
	country VARCHAR(50)
);
--ALTER TABLE users DROP COLUMN home_address;