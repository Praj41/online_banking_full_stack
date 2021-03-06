CREATE PROCEDURE insertpri(IN _account_balance DECIMAL(10, 2))
BEGIN
    DECLARE getcount INT;

    SET getcount = (SELECT COUNT(*) FROM primary_account) + 101;

    INSERT INTO primary_account (id, account_balance) VALUES (getcount, _account_balance);
END;

CREATE PROCEDURE insertUser(IN _email varchar(255),
                            IN _enabled bit(1),
                            IN _first_name varchar(255),
                            IN _last_name varchar(255),
                            IN _password varchar(255),
                            IN _phone varchar(255),
                            IN _username varchar(255))
BEGIN
    DECLARE pid BIGINT;
    SET pid = (SELECT account_number FROM primary_account WHERE id = ((SELECT COUNT(*) FROM primary_account) + 100));
    INSERT INTO customer (email, enabled, first_name, last_name, password, phone, username, primary_account_id,
                          loan_account_id)
    VALUES (_email, _enabled, _first_name, _last_name, _password, _phone, _username, pid, NULL);
END;

CREATE TRIGGER balUp
    AFTER INSERT
    ON transaction
    FOR EACH ROW
BEGIN
    IF NEW.type = 'PE' THEN
        IF NOT NEW.amount >
               (SELECT account_balance FROM primary_account WHERE account_number = NEW.primary_account_id) THEN
            UPDATE primary_account
            SET account_balance = account_balance - NEW.amount
            WHERE account_number = NEW.primary_account_id;
        END IF;
    ELSEIF NEW.type = 'EMI' THEN
        IF NEW.status = 'Success' THEN
            UPDATE primary_account
            SET account_balance = account_balance - NEW.amount
            WHERE account_number = NEW.primary_account_id;
        END IF;
    ELSEIF NOT NEW.type = 'LF' THEN
        UPDATE primary_account
        SET account_balance = account_balance + NEW.amount
        WHERE account_number = NEW.primary_account_id;
    END IF;
END;

CREATE PROCEDURE transactPE(IN _amount DOUBLE, priaccid BIGINT)
BEGIN
    DECLARE bal DECIMAL(19, 2);
    DECLARE bal1 DECIMAL(19, 2);

    DECLARE date1 DATETIME;
    SET date1 = sysdate();

    SELECT account_balance INTO bal FROM primary_account WHERE account_number = priaccid;
    SET bal = bal - _amount;

    INSERT INTO transaction (amount, available_balance, date, description, type, primary_account_id)
    VALUES (_amount, bal, date1, 'Transfer From Primary Account to External', 'PE', priaccid);

    SELECT account_balance INTO bal1 FROM primary_account WHERE account_number = priaccid;

    IF bal1 = bal THEN
        UPDATE transaction SET status = 'Success' WHERE primary_account_id = priaccid AND date = date1;
    ELSE
        SELECT id INTO priaccid FROM transaction WHERE primary_account_id = priaccid ORDER BY id DESC LIMIT 1;
        UPDATE transaction
        SET available_balance = bal1,
            description       = 'Insufficient Balance',
            status            = 'Fail'
        WHERE id = priaccid;
    end if;

END;

CREATE PROCEDURE transactDeposit(IN _amount DOUBLE, priaccid BIGINT)
BEGIN
    DECLARE bal DECIMAL(19, 2);
    SELECT account_balance INTO bal FROM primary_account WHERE account_number = priaccid;
    SET bal = bal + _amount;

    INSERT INTO transaction (amount, available_balance, date, description, status, type, primary_account_id)
    VALUES (_amount, bal, sysdate(), 'Deposit Money to Primary Account.', 'Success', 'DP', priaccid);

END;

CREATE PROCEDURE createLoan(IN uid BIGINT)
BEGIN
    DECLARE var BIGINT;

    IF ((SELECT loan_account_id FROM customer WHERE user_id = uid) IS NULL) THEN

        SET var = (SELECT COUNT(*) FROM loan_account) + 101;
        INSERT INTO loan_account (id, loan_balance, loan_total, rate, years)
        VALUES (var, 0.0, 0.0, 0.0, 0);

        SELECT * FROM loan_account WHERE id = var;

        SELECT account_number INTO var FROM loan_account WHERE id = var;

        UPDATE customer SET loan_account_id = var WHERE user_id = uid;
    ELSE
        SELECT loan_account_id INTO var FROM customer WHERE user_id = uid;

        SELECT * FROM loan_account WHERE account_number = var;
    END IF;
END;

CREATE PROCEDURE loanReq(IN amount DECIMAL(10, 2), IN period INT, IN accno BIGINT)
BEGIN
    DECLARE bal DECIMAL(10, 2);
    DECLARE pid BIGINT;

    SELECT primary_account_id INTO pid FROM customer WHERE customer.loan_account_id = accno;
    SELECT account_balance
    INTO bal
    FROM primary_account,
         customer,
         loan_account
    WHERE loan_account.account_number = accno
      AND primary_account.account_number = customer.primary_account_id
      AND loan_account.account_number = customer.loan_account_id;
    IF amount = 0.0 THEN
        SET bal = bal;
    ELSEIF NOT (SELECT loan_balance FROM loan_account WHERE account_number = accno) > 0.0 THEN
        UPDATE loan_account
        SET loan_balance = amount,
            loan_total   = amount,
            rate         = 7.5,
            years        = period
        WHERE account_number = accno;

        SET bal = bal + amount;

        INSERT INTO transaction (amount, available_balance, date, description, status, type, primary_account_id,
                                 loan_account_id)
        VALUES (amount, bal, sysdate(), 'Money Deposited into primary account', 'Loan Approved', 'LA', pid, accno);

    ELSE
        INSERT INTO transaction (amount, available_balance, date, description, status, type, primary_account_id,
                                 loan_account_id)
        VALUES (amount, bal, sysdate(), 'Loan Already Exists Pay it off', 'Loan Not Approved', 'LF', pid, accno);

    END IF;

    SELECT * FROM loan_account WHERE account_number = accno;

END;

CREATE TRIGGER usrtransaction
    BEFORE INSERT
    ON transactionbtwuser
    FOR EACH ROW
BEGIN
    DECLARE bal DECIMAL(10, 2);

    SELECT account_balance INTO bal FROM primary_account WHERE account_number = NEW.from_account_id;

    SET NEW.date = sysdate();

    IF NEW.to_account_id IS NULL THEN
        SET NEW.status = 'Failed';

    ELSEIF bal < NEW.amount THEN
        SET NEW.status = 'Failed';
        SET NEW.description = 'Insufficient Balance at sender';
    ELSE
        UPDATE primary_account
        SET account_balance = account_balance - NEW.amount
        WHERE account_number = NEW.from_account_id;

        UPDATE primary_account
        SET account_balance = account_balance + NEW.amount
        WHERE account_number = NEW.to_account_id;

        SET NEW.status = 'Success';
        SET NEW.description = 'Amount Transfer Complete';
    END IF;

END;

CREATE PROCEDURE payLoan(IN laccno BIGINT, IN paccno BIGINT)
BEGIN
    DECLARE pay DECIMAL(10, 2);
    DECLARE yrs INT;
    DECLARE rt DECIMAL(10, 2);
    DECLARE amt DECIMAL(10, 2);
    DECLARE bal DECIMAL(10, 2);
    DECLARE now DATETIME;

    SET now = sysdate();
    SELECT loan_total, years, rate INTO pay, yrs, rt FROM loan_account WHERE account_number = laccno;

    SET rt = rt / 1200;
    SET yrs = yrs * 12;
    SET pay = (pay * rt * POW((1 + rt), yrs) ) / (POW((1 + rt), yrs) - 1);

    SELECT account_balance INTO amt FROM primary_account WHERE account_number = paccno;
    SELECT loan_balance INTO bal FROM loan_account WHERE account_number = laccno;

    IF pay > amt THEN
        INSERT INTO transaction (amount, available_balance, date, description, status, type, primary_account_id,
                                 loan_account_id)
        VALUES (pay, amt, now, 'Insufficient Balance To pay EMI', 'Failed', 'EMI', paccno, laccno);
    ELSEIF bal <= pay THEN
        INSERT INTO transaction (amount, available_balance, date, description, status, type, primary_account_id,
                                 loan_account_id)
        VALUES (bal, amt - bal, now, 'EMI Amount Has Been Deducted From Primary, Full Loan Paid', 'Success', 'EMI', paccno, laccno);
        UPDATE loan_account
        SET loan_balance = 0.0,
            loan_total   = 0.0,
            rate         = 0.0,
            years        = 0
        WHERE account_number = laccno;
    ELSE

        UPDATE loan_account
        SET loan_balance = loan_balance + (loan_balance * rt) - pay
        WHERE account_number = laccno;
        INSERT INTO transaction (amount, available_balance, date, description, status, type, primary_account_id,
                                 loan_account_id)
        VALUES (pay, amt - pay, now, 'EMI Amount Has Been Deducted From Primary', 'Success', 'EMI', paccno, laccno);
    END IF;

    SELECT *
    FROM transaction
    WHERE date = now;

END;