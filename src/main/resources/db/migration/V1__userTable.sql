CREATE TABLE primary_account
(
    id              BIGINT             NOT NULL,
    account_balance DECIMAL(10, 2) DEFAULT NULL CHECK ( account_balance >= 0.0 ),
    account_number  BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT
);

CREATE TABLE loan_account
(
    id             BIGINT             NOT NULL,
    loan_balance   DECIMAL(10, 2) DEFAULT NULL CHECK ( loan_balance >= 0.0 ),
    loan_total     DECIMAL(10, 2) DEFAULT NULL CHECK ( loan_total >= 0.0 ),
    rate           DECIMAL(2, 2)  DEFAULT NULL,
    years          INT            DEFAULT NULL CHECK ( years >= 0 ),
    account_number BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT
);

CREATE TABLE customer
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
    loan_account_id BIGINT       DEFAULT NULL,
    UNIQUE KEY `UK_user_email` (`email`),
    CONSTRAINT FK_usrpriacc FOREIGN KEY (primary_account_id) REFERENCES primary_account (account_number),
    CONSTRAINT FK_usrloanacc FOREIGN KEY (loan_account_id) REFERENCES loan_account (account_number)
);

CREATE TABLE transaction
(
    id                 bigint NOT NULL AUTO_INCREMENT,
    amount             double NOT NULL,
    available_balance  decimal(19, 2) DEFAULT NULL,
    date               datetime       DEFAULT NULL,
    description        varchar(255)   DEFAULT NULL,
    status             varchar(255)   DEFAULT NULL,
    type               varchar(255)   DEFAULT NULL,
    primary_account_id BIGINT         DEFAULT NULL,
    loan_account_id    BIGINT         DEFAULT NULL,
    PRIMARY KEY (id),
    KEY FK_accid_priTrans (primary_account_id),
    CONSTRAINT FK_accid_priTrans FOREIGN KEY (primary_account_id) REFERENCES primary_account (account_number),
    CONSTRAINT FK_accid_loanTrans FOREIGN KEY (loan_account_id) REFERENCES loan_account (account_number)
);

ALTER TABLE primary_account
    AUTO_INCREMENT = 1121000001;

ALTER TABLE loan_account
    AUTO_INCREMENT = 6121000001;

ALTER TABLE customer
    AUTO_INCREMENT = 101;

ALTER TABLE transaction
    AUTO_INCREMENT = 1;
