
CREATE DATABASE IF NOT EXISTS bookshop;

USE bookshop;

CREATE TABLE IF NOT EXISTS `book` (
                                      `book_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `isbn_10` varchar(16) DEFAULT NULL,
    `isbn_13` varchar(16) DEFAULT NULL,
    `title` varchar(128) NOT NULL,
    `description` varchar(5120) NOT NULL,
    `genre` varchar(64) NOT NULL DEFAULT '0',
    `image` varchar(512) NOT NULL,
    `author` varchar(128) NOT NULL DEFAULT '',
    `publisher` varchar(128) DEFAULT NULL,
    `language` varchar(32) NOT NULL DEFAULT 'English',
    `page_count` smallint(5) unsigned NOT NULL DEFAULT 0,
    `release_date` datetime NOT NULL DEFAULT current_timestamp(),
    `hardcover_price` double unsigned NOT NULL DEFAULT 0,
    `paperback_price` double unsigned NOT NULL DEFAULT 0,
    `ebook_price` double unsigned NOT NULL DEFAULT 0,
    `audiobook_price` double unsigned NOT NULL DEFAULT 0,
    `ratings` double unsigned NOT NULL DEFAULT 0,
    `ratings_count` int(10) unsigned NOT NULL DEFAULT 0,
    PRIMARY KEY (`book_id`),
    KEY `author_id` (`author`) USING BTREE
    );

CREATE TABLE IF NOT EXISTS `user` (
                                      `user_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `username` varchar(50) NOT NULL,
    `password` varchar(64) NOT NULL,
    `first_name` varchar(64) DEFAULT NULL,
    `last_name` varchar(64) DEFAULT NULL,
    `email_address` varchar(64) DEFAULT NULL,
    `phone_number` varchar(15) DEFAULT NULL,
    `profile_picture` varchar(128) NOT NULL DEFAULT
    'https://icons.getbootstrap.com/assets/icons/person-circle.svg',
    `role` varchar(50) NOT NULL DEFAULT 'USER',
    `enabled` bit(1) NOT NULL DEFAULT b'1',
    `locked` bit(1) NOT NULL DEFAULT b'0',
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `username` (`username`),
    KEY `username_password` (`username`,`password`)
    );