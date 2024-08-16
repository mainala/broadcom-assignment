CREATE DATABASE IF NOT EXISTS players;
USE players;

CREATE TABLE playersinfo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    age INT,
    address1 VARCHAR(255),
    address2 VARCHAR(255)
);
DELIMITER //

CREATE PROCEDURE insert_dummy_records()
BEGIN
    DECLARE i INT DEFAULT 1;
    
    WHILE i <= 1000000 DO
        INSERT INTO playersinfo (firstName, lastName, age, address1, address2)
        VALUES (
            CONCAT('First', i),
            CONCAT('Last', i),
            FLOOR(18 + (RAND() * 50)),
            CONCAT('Address1', i),
            CONCAT('Address2', i)
        );
        SET i = i + 1;
    END WHILE;
END //

DELIMITER ;

CALL insert_dummy_records();
CREATE TABLE matches (
    id INT AUTO_INCREMENT PRIMARY KEY,
    winner_id INT,
    loser_id INT,
    FOREIGN KEY (winner_id) REFERENCES playersinfo(id),
    FOREIGN KEY (loser_id) REFERENCES playersinfo(id)
);
DELIMITER //

CREATE PROCEDURE insert_match_records()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE max_id INT;

    SELECT MAX(id) INTO max_id FROM playersinfo;

    WHILE i <= 1000000 DO
        INSERT INTO matches (winner_id, loser_id)
        VALUES (
            FLOOR(1 + (RAND() * max_id)),
            FLOOR(1 + (RAND() * max_id))
        );
        SET i = i + 1;
    END WHILE;
END //

DELIMITER ;

CALL insert_match_records();