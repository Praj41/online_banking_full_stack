ALTER TABLE user
    ADD CONSTRAINT FK_usrpriacc FOREIGN KEY (primary_account_id) REFERENCES primary_account (account_number);
