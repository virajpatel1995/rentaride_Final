# • Create 2 administrators.
# • Create 2 rental locations.
# • Create 2 vehicle types, each with 2 different hourly prices. 
# • Create 4 vehicles, 2 of one vehicle type and 2 of the other, assigned to the 2 rental
# locations (2 vehicles per location but with different vehicle types at each location).
# • Create 2 customers.
# 
# • For each customer, create 2 reservations, each with a vehicle type and a rental location.
# • Finally, for two of the created reservations (one per customer), create corresponding
# rentals, each involving a vehicle (with a correct vehicle type) and a comment. Do not
# calculate the rental charges or late fees but include some “made-up” values.


# 
# Create 2 administrators. Insert 2 rows into the user table
#
INSERT INTO team10.user (id, type, firstName, lastName, userName, password, email, address, createdDate, memberUntil, licState, licNumber, ccNumber, ccExpiration, status) 
VALUES (default, 'Administrator', 'John', 'Smith', 'admin1', '1q2w3e4r', 'jsmith@rentaride.com','333 Wide St., Flower, RI. 17345', NOW(), null, null, null, null, null, null) ;
INSERT INTO team10.user (id, type, firstName, lastName, userName, password, email, address, createdDate, memberUntil, licState, licNumber, ccNumber, ccExpiration, status) 
VALUES (default, 'Administrator', 'Alice', 'Bob', 'admin2', 'password123', 'abob@rentaride.com','734 Pine Straw Dr., Bloom, KY. 48878', NOW(), null, null, null, null, null, null) ;

#
# Create 2 rental locations. Insert 2 rows into the rental Location table
#
INSERT INTO team10.rentalLocation (id, name, address, capacity) 
VALUES (default, 'TATE', '123 Main St', 100) ;
INSERT INTO team10.rentalLocation (id, name, address, capacity) 
VALUES (default, 'MLC', '123 Baxter St', 50) ;


#
# Create 2 vehicle types, each with 2 different hourly prices. Insert 2 rows into the vehicleType table
#
# Hourly price???
INSERT INTO team10.vehicleType(id, name) 
VALUES (default, 'Seden') ;
INSERT INTO team10.vehicleType(id, name) 
VALUES (default, 'Pick-up Truck') ;

#
# insert 4 rows into the vehicle table
# Create 4 vehicles, 2 of one vehicle type and 2 of the other, assigned to the 2 rental
# locations (2 vehicles per location but with different vehicle types at each location).
#INSERT INTO team10.vehicle(id, make, model, year, mileage, tag, lastServiced, status, maintenance, rentalLocationid, vehicleTypeid) 
#VALUES (default, 'Toyota', 'Corolla', 2017, 100, 'ABC123', '2017-02-04 15:23:20', 'INLOCATION', 'GOOD', 1, 1) ;
#INSERT INTO team10.vehicle(id, make, model, year, mileage, tag, lastServiced, status, maintenance, rentalLocationid, vehicleTypeid) 
#VALUES (default, 'Ford', 'F150', 2017, 50, 'DEF123', '2012-12-23 10:13:30', 'INLOCATION', 'GOOD', 1, 2) ;
#INSERT INTO team10.vehicle(id, make, model, year, mileage, tag, lastServiced, status, maintenance, rentalLocationid, vehicleTypeid) 
#VALUES (default, 'Lamborghini', 'Aventador', 2017, 2, 'AAA111', '2017-10-05 10:20:21', 'INLOCATION', 'GOOD', 2, 1) ;
#INSERT INTO team10.vehicle(id, make, model, year, mileage, tag, lastServiced, status, maintenance, rentalLocationid, vehicleTypeid) 
#VALUES (default, 'Toyota', 'Tacoma', 2014, 31560, 'PPR133', '2017-06-04 00:43:20', 'INLOCATION', 'GOOD', 2, 2) ;

INSERT INTO team10.vehicle(id, make, model, year, mileage, tag, lastServiced, status, maintenance, rentalLocationid, vehicleTypeid) 
SELECT 'default', 'Toyota', 'Corolla', 2017, 100, 'ABC123', '2017-02-04 15:23:20', 'INLOCATION', 'GOOD', rl.id, vt.id
FROM team10.rentalLocation rl, team10.vehicleType vt 
WHERE rl.name = 'TATE' && vt.name = 'Seden';

INSERT INTO team10.vehicle(id, make, model, year, mileage, tag, lastServiced, status, maintenance, rentalLocationid, vehicleTypeid) 
SELECT 'default', 'Ford', 'F150', 2017, 50, 'DEF123', '2012-12-23 10:13:30', 'INLOCATION', 'GOOD', rl.id, vt.id
FROM team10.rentalLocation rl, team10.vehicleType vt 
WHERE rl.name = 'TATE' && vt.name = 'Pick-up Truck';

INSERT INTO team10.vehicle(id, make, model, year, mileage, tag, lastServiced, status, maintenance, rentalLocationid, vehicleTypeid) 
SELECT 'default', 'Lamborghini', 'Aventador', 2017, 2, 'AAA111', '2017-10-05 10:20:21', 'INLOCATION', 'GOOD',  rl.id, vt.id
FROM team10.rentalLocation rl, team10.vehicleType vt 
WHERE rl.name = 'MLC' && vt.name = 'Seden';

INSERT INTO team10.vehicle(id, make, model, year, mileage, tag, lastServiced, status, maintenance, rentalLocationid, vehicleTypeid) 
SELECT 'default', 'Toyota', 'Tacoma', 2014, 31560, 'PPR133', '2017-06-04 00:43:20', 'INLOCATION', 'GOOD',  rl.id, vt.id
FROM team10.rentalLocation rl, team10.vehicleType vt 
WHERE rl.name = 'MLC' && vt.name = 'Pick-up Truck';


#
# Create 2 customers. Insert 2 rows into the user table
#
INSERT INTO team10.user (id, type, firstName, lastName, userName, password, email, address, createdDate, memberUntil, licState, licNumber, ccNumber, ccExpiration, status) 
VALUES (default, 'Customer', 'Jack', 'Lewis', 'jacklewis', '123456', 'jlewis@gmail.com','123 North St., Athens, GA. 30605', NOW(), '2018-12-31 00:00:00', 'GA', '111222333', '1111222233334444', '2019-12-01 00:00:00', 'ACTIVE') ;
INSERT INTO team10.user (id, type, firstName, lastName, userName, password, email, address, createdDate, memberUntil, licState, licNumber, ccNumber, ccExpiration, status) 
VALUES (default, 'Customer', 'David', 'Salmon', 'davidsalmon', 'asdfasdf', 'dsalmon@gmail.com', '123 Baxter St., Athens, GA. 30605', NOW(), '2018-12-31 00:00:00', 'GA', '444555666', '1234123412341234', '2019-05-01 00:00:00', 'ACTIVE') ;

#
# insert 4 rows into the reservation table
# For each customer, create 2 reservations, each with a vehicle type and a rental location.
INSERT INTO reservation(id, pickup, length, canceled, userid, rentalLocationid, vehicleTypeid) 
SELECT 'default', '2017-11-05 10:00:00', 48, 0, u.id, rl.id, vt.id
FROM rentalLocation rl, vehicleType vt, user u
WHERE rl.name = 'MLC' AND vt.name = 'Seden'AND u.firstName = 'Jack'AND u.lastName = 'Lewis' ;

INSERT INTO reservation(id, pickup, length, canceled, userid, rentalLocationid, vehicleTypeid) 
SELECT 'default', '2017-12-05 10:00:00', 36, 0, u.id, rl.id, vt.id
FROM rentalLocation rl, vehicleType vt, user u
WHERE rl.name = 'TATE' AND vt.name = 'Pick-up Truck'AND u.firstName = 'Jack'AND u.lastName = 'Lewis' ;

INSERT INTO reservation(id, pickup, length, canceled, userid, rentalLocationid, vehicleTypeid) 
SELECT 'default', '2017-11-15 10:00:00', 72, 0, u.id, rl.id, vt.id
FROM rentalLocation rl, vehicleType vt, user u
WHERE rl.name = 'TATE' AND vt.name = 'Seden'AND u.firstName = 'David'AND u.lastName = 'Salmon' ;

INSERT INTO reservation(id, pickup, length, canceled, userid, rentalLocationid, vehicleTypeid) 
SELECT 'default', '2017-11-25 10:00:00', 144, 0, u.id, rl.id, vt.id
FROM rentalLocation rl, vehicleType vt, user u
WHERE rl.name = 'MLC' AND vt.name = 'Pick-up Truck'AND u.firstName = 'David'AND u.lastName = 'Salmon' ;



#
# insert 2 rows into the rental table
# Finally, for two of the created reservations (one per customer), create corresponding
# rentals, each involving a vehicle (with a correct vehicle type) and a comment. Do not
# calculate the rental charges or late fees but include some “made-up” values.

# Hardcoded rental
INSERT INTO rental(id, pickup, dropoff, late, charges, reservationid, vehicleid, comment, commentdate, userid) 
VALUES (default, '2017-11-05 10:00:00','2017-11-06 10:00:00', 0, 70, 1, 1, 'Nice car. Good experience.', '2017-11-06 11:00:00', 1);
INSERT INTO rental(id, pickup, dropoff, late, charges, reservationid, vehicleid, comment, commentdate, userid) 
VALUES (default, '2017-11-05 10:00:00','2017-11-06 10:00:00', 0, 70, 4, 2, 'Good car. Nice experience.', '2017-11-06 11:00:00', 2);


# Need help
INSERT INTO rental(id, pickup, dropoff, late, charges, reservationid, vehicleid, comment, commentdate, userid) 
SELECT 'default', '2017-11-05 10:00:00','2017-11-06 10:00:00', 0, 70, 1, v.id, 'Nice car. Good experience.', '2017-11-06 11:00:00', 
	(SELECT id FROM user u WHERE  u.firstName = 'David'AND u.lastName = 'Salmon') as userid
FROM reservation r, vehicle v , user u
WHERE  v.model = 'F150' ;

#INSERT INTO rental(id, pickup, dropoff, late, charges, reservationid, vehicleid, comment, commentdate, userid) 
#SELECT 'default', '2017-11-05 10:00:00','2017-11-06 10:00:00', 0, 70, 3, v.id, 'Good car. Nice experience.', '2017-11-06 11:00:00', u.id
#FROM reservation r, vehicle v , user u
#WHERE  v.model = 'Corolla' AND u.firstName = 'Jack'AND u.lastName = 'Lewis' ;

#
# insert 2 rows into the rental table
#
# INSERT INTO team10.hourlyPrice(id, maxHrs, price, vehicleTypeid) 
# VALUES (default, 120, 15, 1) ;
# INSERT INTO team10.hourlyPrice(id, maxHrs, price, vehicleTypeid) 
# VALUES (default, 144, 20, 2) ;

INSERT INTO team10.hourlyPrice(id, maxHrs, price, vehicleTypeid) 
SELECT 'default', 120, 15, vt.id
FROM vehicleType vt
WHERE vt.name = 'Seden'; 

INSERT INTO team10.hourlyPrice(id, maxHrs, price, vehicleTypeid) 
SELECT 'default', 144, 20, vt.id 
FROM vehicleType vt
WHERE vt.name = 'Pick-up Truck'; 

