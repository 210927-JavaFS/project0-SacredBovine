CREATE TABLE requests (
	request_id SERIAL PRIMARY KEY,
	request_type INTEGER,
	request_message VARCHAR(250)
);