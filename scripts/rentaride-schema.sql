# SQL file the creates the schema for the Rent a Vehicle Database

#remove Extra ForiegnKeys
SET @dropkey = IF((SELECT true FROM information_schema.TABLE_CONSTRAINTS
									WHERE CONSTRAINT_SCHEMA = DATABASE()
                                    AND TABLE_NAME = 'reservation'
									AND CONSTRAINT_NAME = 'reservation_ibfk_4'
                                    AND CONSTRAINT_TYPE = 'FOREIGN KEY') = true, 
									'ALTER TABLE reservation DROP FOREIGN KEY reservation_ibfk_4', 
                                    'select 1');
prepare ex from @dropkey;
EXECUTE ex;
DEALLOCATE prepare ex;


# Remove tables
DROP TABLE IF EXISTS hourlyPrice;
DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS rental;
DROP TABLE IF EXISTS reservation;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS vehicle;
DROP TABLE IF EXISTS rentalLocation;
DROP TABLE IF EXISTS vehicleType;

#Table definition for table 'user'
CREATE TABLE user (
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    type ENUM('Administrator', 'Customer'),
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    userName VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    createdDate DATETIME NOT NULL,
    
    memberUntil DATETIME,
    licState VARCHAR(255),
    licNumber VARCHAR(255),
    ccNumber VARCHAR(255),
    ccExpiration DATETIME,
    status  ENUM('ACTIVE', 'CANCELLED', 'TERMINATED')
    
    
    
) ENGINE = InnoDB;

#Table definition for table 'rentalLocation'
CREATE TABLE rentalLocation (
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE,
    address VARCHAR(255) NOT NULL,
    capacity INT NOT NULL
) ENGINE = InnoDB;

#Table definition for table 'vehicleType'
CREATE TABLE vehicleType (
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE
) ENGINE = InnoDB;

#Table definition for table 'hourlyPrice'
CREATE TABLE hourlyPrice (
	id	INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    maxHrs INT NOT NULL,
    price INT NOT NULL,
    vehicleTypeid INT UNSIGNED NOT NULL,
    
    FOREIGN KEY (vehicleTypeid) REFERENCES vehicleType(id) ON DELETE CASCADE
) ENGINE=InnoDB;

#Table definition for table 'reservation'
CREATE TABLE reservation (
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    pickup DATETIME NOT NULL,
    length INT NOT NULL,
    canceled BIT  NOT NULL,
    userid INT UNSIGNED NOT NULL,
    rentalLocationid INT UNSIGNED NOT NULL,
    vehicleTypeid INT UNSIGNED NOT NULL,
    
    FOREIGN KEY (rentalLocationid) REFERENCES rentalLocation(id) ON DELETE CASCADE,
	FOREIGN KEY (userid) REFERENCES user(id) ON DELETE CASCADE,
    FOREIGN KEY (vehicleTypeid) REFERENCES vehicleType(id) ON DELETE CASCADE
) ENGINE = InnoDB;

#Table definition for table 'vehicle'
CREATE TABLE vehicle (
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    make VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    year INT NOT NULL,
    mileage INT NOT NULL,
    tag VARCHAR(255) NOT NULL UNIQUE,
    lastServiced DATETIME,
    status ENUM ('INLOCATION', 'INRENTAL') NOT NULL,
    maintenance ENUM ('GOOD', 'NEEDSMAINTENANCE') NOT NULL,
    rentalLocationid INT UNSIGNED NOT NULL,
    vehicleTypeid INT UNSIGNED NOT NULL,
    
    FOREIGN KEY (rentalLocationid) REFERENCES rentalLocation(id) ON DELETE CASCADE,
    FOREIGN KEY (vehicleTypeid) REFERENCES vehicleType(id) ON DELETE CASCADE
) ENGINE = InnoDB;

#Table deinition for table 'rental'
CREATE TABLE rental (
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    pickup DATETIME,
    dropoff DATETIME,
    late BIT,
    charges INT,
    reservationid INT UNSIGNED NOT NULL,
    vehicleid INT UNSIGNED NOT NULL,
    userid INT UNSIGNED NOT NULL,
    
    FOREIGN KEY (reservationid) REFERENCES reservation(id) ON DELETE CASCADE,
    FOREIGN KEY (vehicleid) REFERENCES vehicle(id) ON DELETE CASCADE,
    FOREIGN KEY (userid) REFERENCES user(id) ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE comment(
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    comment VARCHAR(255),
    commentdate DATETIME,
    rentalid INT UNSIGNED NOT NULL,
    
    FOREIGN KEY(rentalid) REFERENCES rental(id) ON DELETE CASCADE
) ENGINE = InnoDB;
CREATE TABLE rentARideParams(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    membershipPrice INT,
    lateFee INT
) ENGINE = InnoDB;

#add a foriegn key to reservation
ALTER TABLE reservation
	ADD rentalid INT UNSIGNED;
ALTER TABLE reservation
	ADD FOREIGN KEY (rentalid) REFERENCES rental(id) ON DELETE CASCADE;
