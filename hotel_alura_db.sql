DROP DATABASE IF EXISTS `hotel_alura_db`;

CREATE DATABASE IF NOT EXISTS `hotel_alura_db`;

use hotel_alura_db;

  
  CREATE TABLE `hotel_alura_db`.`login` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `active` BOOLEAN NOT NULL DEFAULT true,
  `username` CHAR(30) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `create_time` TIMESTAMP NULL,
  `update_time` TIMESTAMP NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_username` (`username`)
);  
  
CREATE TRIGGER `update_login_timestamp`
BEFORE UPDATE ON `login` FOR EACH ROW
SET NEW.update_time = CURRENT_TIMESTAMP;

CREATE TRIGGER `set_login_create_time`
BEFORE INSERT ON `login` FOR EACH ROW
SET NEW.create_time = CURRENT_TIMESTAMP;
  
  CREATE TABLE `hotel_alura_db`.`reservations` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `active` BOOLEAN NOT NULL DEFAULT true,
  `check_in_date` DATE NOT NULL,
  `check_out_date` DATE NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `payment_option` VARCHAR(255),
  `create_time` TIMESTAMP NULL,
  `update_time` TIMESTAMP NULL,
  PRIMARY KEY (`id`)
);
  
CREATE TRIGGER `update_reservations_timestamp`
BEFORE UPDATE ON `reservations` FOR EACH ROW
SET NEW.update_time = CURRENT_TIMESTAMP;

CREATE TRIGGER `set_reservations_create_time`
BEFORE INSERT ON `reservations` FOR EACH ROW
SET NEW.create_time = CURRENT_TIMESTAMP;

CREATE TABLE `hotel_alura_db`.`guests` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `active` BOOLEAN NOT NULL DEFAULT true,
  `name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `birthday` DATE NOT NULL,
  `nationality` VARCHAR(50) NOT NULL,
  `phone` VARCHAR(20) NOT NULL,
  `reservation_id` INT NOT NULL,
  `create_time` TIMESTAMP NULL,
  `update_time` TIMESTAMP NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`reservation_id`) REFERENCES `reservations` (`id`)
);

CREATE TRIGGER `update_guests_timestamp`
BEFORE UPDATE ON `guests` FOR EACH ROW
SET NEW.update_time = CURRENT_TIMESTAMP;

CREATE TRIGGER `set_guests_create_time`
BEFORE INSERT ON `guests` FOR EACH ROW
SET NEW.create_time = CURRENT_TIMESTAMP;
  
  
  CREATE TABLE `hotel_alura_db`.`daily_rate` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `active` BOOLEAN NOT NULL DEFAULT true,
  `price` DECIMAL(10,2) NOT NULL,
  `create_time` TIMESTAMP NULL,
  `update_time` TIMESTAMP NULL,
  PRIMARY KEY (`id`)
);  
  
CREATE TRIGGER `update_daily_rate_timestamp`
BEFORE UPDATE ON `daily_rate` FOR EACH ROW
SET NEW.update_time = CURRENT_TIMESTAMP;

CREATE TRIGGER `set_daily_rate_create_time`
BEFORE INSERT ON `daily_rate` FOR EACH ROW
SET NEW.create_time = CURRENT_TIMESTAMP;
  
  
  -- set user
  INSERT INTO `hotel_alura_db`.`login` (`username`, `password`) VALUES ('admin', '8634757969310858970056571127015175896244927041566922730197001243');
  
  -- set the daily rate value 
  INSERT INTO `hotel_alura_db`.`daily_rate` (`price`) VALUES ( 100 );
  

