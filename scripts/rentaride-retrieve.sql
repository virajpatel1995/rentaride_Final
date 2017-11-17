# retrieve some data from the rental database

# retrieve all administrators
select * from user WHERE type = 'Administrator';

# retrieve all customers
select * from user WHERE type = 'Customer';

# retrieve all rental locations and vehicles assigned there (with type)
#SELECT 
#rentalLocation.name,
#vehicle.name,
#vehicleType.name

#FROM
#rentalLocation rl

#INNER JOIN vehicle ON rl.id = v.rentalLocationid
#INNER JOIN vehicleType ON vt.id = v.rentalLocationid;
SELECT 
rentalLocation.name,
rentalLocation.address,
rentalLocation.capacity,
vehicle.make,
vehicle.model,
vehicle.year,
vehicle.mileage,
vehicle.tag,
vehicle.lastServiced,
vehicle.status,
vehicle.maintenance,
vehicleType.name

FROM
vehicle

INNER JOIN rentalLocation ON rentalLocation.id = vehicle.rentalLocationid
INNER JOIN vehicleType ON vehicleType.id = vehicle.vehicleTypeid;

# retrieve all rental locations and reservations (with customers and vehicle type)
SELECT 
rentalLocation.name,
rentalLocation.address,
rentalLocation.capacity,
reservation.pickup,
reservation.length,
reservation.canceled,
user.firstName,
user.lastName,
vehicleType.name

FROM
reservation

INNER JOIN rentalLocation ON rentalLocation.id = reservation.rentalLocationid
INNER JOIN vehicleType ON vehicleType.id = reservation.vehicleTypeid
INNER JOIN user ON user.id = reservation.userid;

# retrieve all vehicles types along with their vehicles of the listed type
SELECT
vehicleType.name, 
vehicle.make, 
vehicle.model, 
vehicle.`year`, 
vehicle.mileage,
vehicle.tag,
vehicle.lastServiced, 
vehicle.status, 
vehicle.maintenance


FROM 
vehicleType 

INNER JOIN vehicle ON vehicleType.id = vehicle.vehicleTypeid;

# retrieve all customers and their reservations (with their rental locations and vehicle types)	
SELECT
user.firstName,
user.lastName,
reservation.pickup,
reservation.length,
reservation.canceled,
rentalLocation.name,
rentalLocation.address,
rentalLocation.capacity,
vehicleType.name


FROM
reservation

INNER JOIN rentalLocation ON rentalLocation.id = reservation.rentalLocationid
INNER JOIN vehicleType ON vehicleType.id = reservation.vehicleTypeid
INNER JOIN user ON user.id = reservation.userid;

# retrieve all customers and their rentals (with comments, vehicles and their vehicle types) 
SELECT
user.firstName,
user.lastName,
rental.pickup,
rental.comment,
rental.commentdate,
vehicle.make, 
vehicle.model, 
vehicle.`year`, 
vehicle.mileage,
vehicle.tag,
vehicle.lastServiced, 
vehicle.status, 
vehicle.maintenance,
vehicleType.name

FROM
rental

INNER JOIN user ON user.id = rental.userid
INNER JOIN vehicle ON vehicle.id = rental.vehicleid
INNER JOIN vehicleType ON vehicleType.id = vehicle.vehicleTypeid