CREATE TABLE user
(
    user_id            bigint(20)   NOT NULL AUTO_INCREMENT,
    email              varchar(255) NOT NULL,
    enabled            bit(1)       NOT NULL,
    first_name         varchar(255) DEFAULT NULL,
    last_name          varchar(255) DEFAULT NULL,
    password           varchar(255) DEFAULT NULL,
    phone              varchar(255) DEFAULT NULL,
    username           varchar(255) DEFAULT NULL,
    primary_account_id BIGINT       DEFAULT NULL,
    savings_account_id BIGINT       DEFAULT NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `UK_user_email` (`email`)
);

CREATE TABLE primary_account
(
    account_balance DECIMAL(10, 2),
    account_number  BIGINT,
    CONSTRAINT PK_priacc PRIMARY KEY (account_number)
);
