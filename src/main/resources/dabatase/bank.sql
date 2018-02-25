CREATE DATABASE bank;

﻿
CREATE TABLE BNK_USER (
    user_id SERIAL PRIMARY KEY,
    user_name TEXT NOT NULL
);

CREATE TABLE BNK_ACCOUNT(
    account_id SERIAL PRIMARY KEY,
    account_account INTEGER UNIQUE NOT NULL
);

CREATE TABLE BNK_USER_ACCOUNT(
    user_account_id SERIAL PRIMARY KEY,
    user_account_user_id INTEGER REFERENCES BNK_USER (user_id),
    user_account_account_id INTEGER REFERENCES BNK_ACCOUNT (account_id)
);

CREATE TABLE BNK_TRANSACTION_TYPE(
    transaction_type_id SERIAL PRIMARY KEY,
    description TEXT NOT NULL
);

CREATE TABLE BNK_TRANSACTION_STATUS(
    transaction_status_ID SERIAL PRIMARY KEY,
    description TEXT NOT NULL
);


CREATE TABLE BNK_TRANSACTION(
    transaction_id SERIAL PRIMARY KEY,
    transaction_value FLOAT,
    transaction_date TIMESTAMP DEFAULT NOW(),
    transaction_account_id INTEGER NOT NULL REFERENCES BNK_ACCOUNT (account_id),
    transaction_type_id INTEGER NOT NULL REFERENCES  BNK_TRANSACTION_TYPE (transaction_type_id),
    transaction_status INTEGER NOT NULL REFERENCES BNK_TRANSACTION_STATUS (transaction_status_id),
    transaction_user_id INTEGER REFERENCES BNK_USER (user_id)
);


