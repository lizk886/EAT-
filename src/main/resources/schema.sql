-- Check if the schema exists and create it if it does not
SET @dbname = 'backend_eat';
SET @exists = (SELECT COUNT(*) FROM information_schema.schemata WHERE schema_name = @dbname);
SET @stmt = IF(@exists = 0, CONCAT('CREATE SCHEMA ', @dbname), 'SELECT ''Schema already exists''');
PREPARE stmt FROM @stmt;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

USE `backend_eat`;

CREATE TABLE IF NOT EXISTS `DiningHall` (
  `DiningHall_id` BIGINT NOT NULL AUTO_INCREMENT,
  `DiningHall_Name` VARCHAR(45),
  PRIMARY KEY (`DiningHall_id`)
);

CREATE TABLE IF NOT EXISTS `Users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `Username` VARCHAR(50) NOT NULL,
  `Email` VARCHAR(55) NOT NULL,
  `Password` VARCHAR(255) NOT NULL,
  `is_Guest` TINYINT NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `MenuItem` (
  `Item_id` BIGINT NOT NULL AUTO_INCREMENT,
  `DiningHall_id` BIGINT NOT NULL,
  `Category` VARCHAR(100),
  `Item_name` VARCHAR(100),
  `Likes` INT DEFAULT NULL,
  `Dislikes` INT DEFAULT NULL,
  PRIMARY KEY (`Item_id`),
  CONSTRAINT `fk_MenuItem_DiningHall` FOREIGN KEY (`DiningHall_id`)
    REFERENCES `DiningHall` (`DiningHall_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
);

