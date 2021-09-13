CREATE TABLE `user` (
                        `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
                        `email` varchar(255) COLLATE utf8_bin NOT NULL,
                        `enabled` bit(1) NOT NULL,
                        `first_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
                        `last_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
                        `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
                        `phone` varchar(255) COLLATE utf8_bin DEFAULT NULL,
                        `username` varchar(255) COLLATE utf8_bin DEFAULT NULL,
                        `primary_account_id` bigint(20) DEFAULT NULL,
                        `savings_account_id` bigint(20) DEFAULT NULL,
                        PRIMARY KEY (`user_id`),
                        UNIQUE KEY `UK_user_email` (`email`));