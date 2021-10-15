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
	