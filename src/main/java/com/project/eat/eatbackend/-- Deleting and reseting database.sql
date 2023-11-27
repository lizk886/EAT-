-- Deleting and reseting database

ALTER TABLE DiningHall AUTO_INCREMENT = 1;

DELETE FROM MenuItem;

ALTER TABLE MenuItem AUTO_INCREMENT = 1;

DELETE FROM DiningHall;

ALTER TABLE DiningHall AUTO_INCREMENT = 1;