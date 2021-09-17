CREATE TABLE primary_account
(
    user_id         BIGINT,
    account_balance DECIMAL(10, 2) DEFAULT NULL CHECK ( account_balance > 0.0 ),
    account_number  BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT
);

CREATE TABLE user
(
    user_id            BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    email              varchar(255)       NOT NULL,
    enabled            bit(1)             NOT NULL,
    first_name         varchar(255) DEFAULT NULL,
    last_name          varchar(255) DEFAULT NULL,
    password           varchar(255) DEFAULT NULL,
    phone              varchar(255) DEFAULT NULL,
    username           varchar(255) DEFAULT NULL,
    primary_account_id BIGINT       DEFAULT NULL,
    savings_account_id BIGINT       DEFAULT NULL,
    UNIQUE KEY `UK_user_email` (`email`),
    CONSTRAINT FK_usrpriacc FOREIGN KEY (primary_account_id) REFERENCES primary_account (account_number)
);

CREATE TABLE primary_transaction
(
    id                 bigint NOT NULL AUTO_INCREMENT,
    amount             double NOT NULL,
    available_balance  decimal(19, 2) DEFAULT NULL,
    date               datetime       DEFAULT NULL,
    description        varchar(255)   DEFAULT NULL,
    status             varchar(255)   DEFAULT NULL,
    type               varchar(255)   DEFAULT NULL,
    primary_account_id BIGINT         DEFAULT NULL,
    PRIMARY KEY (id),
    KEY FK_accid_priTrans (primary_account_id),
    CONSTRAINT FK_accid_priTrans FOREIGN KEY (primary_account_id) REFERENCES primary_account (account_number)
);

ALTER TABLE primary_account
    AUTO_INCREMENT = 1121000001;
ALTER TABLE user
    AUTO_INCREMENT = 101;

CREATE PROCEDURE insertpri(IN account_balance DECIMAL(10, 2))
BEGIN
    DECLARE getcount INT;

    SET getcount = (SELECT COUNT(*) FROM primary_account) + 101;

    INSERT INTO primary_account (user_id, account_balance) VALUES (getcount, account_balance);
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
    SET pid = (SELECT account_number FROM primary_account WHERE user_id = ((SELECT COUNT(*) FROM primary_account) + 100));
    INSERT INTO user (email, enabled, first_name, last_name, password, phone, username, primary_account_id, savings_account_id)
    VALUES (_email, _enabled, _first_name, _last_name, _password, _phone, _username, pid, NULL);
END;

