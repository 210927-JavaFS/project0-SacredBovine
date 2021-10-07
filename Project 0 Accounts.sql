CREATE TABLE accounts (
	account_number SERIAL,
	account_type VARCHAR(9),
	balance MONEY
);

INSERT INTO accounts ( account_type, balance)
	VALUES ('savings',	678.91);

-- select setval(pg_get_serial_sequence('accounts', 'account_number'), 9999999); 
-- started account numbers at 10000000 to ensure 8 digits should have check to not go to 9 digits without warning