CREATE TABLE accounts (
	account_number SERIAL PRIMARY KEY,
	account_type VARCHAR(9),
	balance MONEY
);

INSERT INTO accounts ( account_type, balance)
	VALUES ('savings',	678.91);

--UPDATE accounts SET balance = ? where acount_number = ?;
-- select setval(pg_get_serial_sequence('accounts', 'account_number'), 9999999); 
-- started account numbers at 10000000 to ensure 8 digits should have check to not go to 9 digits without warning