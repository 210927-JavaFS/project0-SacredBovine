CREATE TABLE accounts (
	account_number SERIAL PRIMARY KEY,
	account_type VARCHAR(9),
	balance NUMERIC(20,2),
	new_account_flag BOOLEAN
);

CREATE TABLE user_accounts (
	user_accounts_id SERIAL PRIMARY KEY,
	user_id INTEGER REFERENCES users(user_id),
	account_number INTEGER REFERENCES accounts(account_number)
);

select setval(pg_get_serial_sequence('accounts', 'account_number'), 9999999); 

