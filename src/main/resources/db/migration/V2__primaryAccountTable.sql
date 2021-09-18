CREATE TRIGGER balUp
    AFTER INSERT
    ON primary_transaction
    FOR EACH ROW
BEGIN
    IF NEW.type = 'PE' THEN
        IF NOT NEW.amount >
               (SELECT account_balance FROM primary_account WHERE account_number = NEW.primary_account_id) THEN
            UPDATE primary_account
            SET account_balance = account_balance - NEW.amount
            WHERE account_number = NEW.primary_account_id;
        END IF;
    ELSE
        UPDATE primary_account
        SET account_balance = account_balance + NEW.amount
        WHERE account_number = NEW.primary_account_id;
    END IF;
END;

CREATE PROCEDURE transactPE(IN _amount DOUBLE, priaccid BIGINT)
BEGIN
    DECLARE bal DECIMAL(19, 2);
    DECLARE bal1 DECIMAL(19, 2);
    SELECT account_balance INTO bal FROM primary_account WHERE account_number = priaccid;
    SET bal = bal - _amount;

    INSERT INTO primary_transaction (amount, available_balance, date, description, type, primary_account_id)
    VALUES (_amount, bal, sysdate(), 'Transfer From Primary Account to External', 'PE', priaccid);

    SELECT account_balance INTO bal1 FROM primary_account WHERE account_number = priaccid;

    IF bal1 = bal THEN
        UPDATE primary_transaction SET status = 'Success' WHERE primary_account_id = priaccid;
    ELSE
        SELECT id INTO priaccid FROM primary_transaction WHERE primary_account_id = priaccid ORDER BY id DESC LIMIT 1;
        UPDATE primary_transaction SET status = 'Fail' WHERE id = priaccid;
        UPDATE primary_transaction SET description = 'Insufficient Balance' WHERE id = priaccid;
        UPDATE primary_transaction SET description = 'Insufficient Balance' WHERE id = priaccid;
        UPDATE primary_transaction SET available_balance = bal1 WHERE id = priaccid;
    end if;

end;

CREATE PROCEDURE transactDeposit(IN _amount DOUBLE, priaccid BIGINT)
BEGIN
    DECLARE bal DECIMAL(19, 2);
    SELECT account_balance INTO bal FROM primary_account WHERE account_number = priaccid;
    SET bal = bal + _amount;

    INSERT INTO primary_transaction (amount, available_balance, date, description, status, type, primary_account_id)
    VALUES (_amount, bal, sysdate(), 'Deposit Money to Primary Account.', 'Success', 'DP', priaccid);

end;