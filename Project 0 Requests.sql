CREATE TABLE requests (
	request_id SERIAL PRIMARY KEY,
	request_type INTEGER,
	request_message VARCHAR(250),
	request_user_id INTEGER REFERENCES users(user_id)
);

