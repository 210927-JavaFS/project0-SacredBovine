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
--DROP TABLE user_accounts;

INSERT INTO accounts ( account_type, balance)
	VALUES ('savings',	678.91);

--DROP TABLE accounts;
--UPDATE accounts SET balance = ? where acount_number = ?;
select setval(pg_get_serial_sequence('accounts', 'account_number'), 9999999); 
-- started account numbers at 10000000 to ensure 8 digits should have check to not go to 9 digits without warning

UPDATE accounts SET new_account_flag = FALSE WHERE new_account_flag = TRUE;
DELETE FROM accounts WHERE account_number = 10000040;

DELETE FROM accounts WHERE account_number >1;

DELETE FROM user_accounts WHERE user_id = 4;
DROP TABLE Accounts CASCADE;


TRUNCATE TABLE accounts CASCADE;