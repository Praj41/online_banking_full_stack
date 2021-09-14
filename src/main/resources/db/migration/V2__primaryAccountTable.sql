CREATE TABLE primary_account (account_balance DECIMAL(10,2),
                              account_number BIGINT,
                              CONSTRAINT PK_priacc PRIMARY KEY (account_number));

-- ALTER TABLE user ADD CONSTRAINT FK_usrpriacc FOREIGN KEY (primary_account_id) REFERENCES primary_account(account_number);
