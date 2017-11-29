//
// A control class to implement the 'Create club' use case
//
//

package edu.uga.cs.rentaride.logic.impl;


import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.entity.VehicleStatus;
import edu.uga.cs.rentaride.entity.VehicleCondition;



public class PlaceRentalCtrl {
    
    private ObjectLayer objectLayer = null;
    
    public PlaceRentalCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    
    public long placeRental( String reservationIdS, String vehicleTag)
            throws RARException
    { 
        Reservation 		    reservation  = null;
        Reservation                modelReservation = null;
        List<Reservation>          reservations= null;
        modelReservation = objectLayer.createReservation();
        modelReservation.setId(Integer.parseInt(reservationIdS));
        reservations = objectLayer.findReservation(modelReservation);
        if(reservations.size()<1) throw new RARException("Reservation does not exists");
        reservation = reservations.get(0);
        
        Vehicle 		    vehicle  = null;
        Vehicle                modelVehicle= null;
        List<Vehicle>          vehicles= null;
        modelVehicle = objectLayer.createVehicle();
        modelVehicle.setRegistrationTag(vehicleTag);
        vehicles = objectLayer.findVehicle(modelVehicle);
        if(vehicles.size()<1) throw new RARException("Vehicle does not exists");
        vehicle = vehicles.get(0);
        
        if(!vehicle.getVehicleType().equals(reservation.getVehicleType())) throw new RARException("Vehicle does not match reservation");
        
        if(!vehicle.getRentalLocation().equals(reservation.getRentalLocation())) throw new RARException("Vehicle is not at the correct Rental Location");
        
        if((reservation.getPickupTime().getTime()+1800000 > new Date().getTime()) && (reservation.getPickupTime().getTime()-1800000 < new Date().getTime())) throw new RARException("Pick up isout of acceptable time frame.");
        
        	Rental rental = objectLayer.createRental();
        	rental.setCustomer(reservation.getCustomer());
        	rental.setPickupTime(reservation.getPickupTime());
        	rental.setReservation(reservation);
        	rental.setReturnTime(new Date(reservation.getPickupTime().getTime() + reservation.getLength()));
        	rental.setVehicle(vehicle);
        	objectLayer.storeRental(rental);
        return rental.getId();
        /*
        // check if a club with this name already exists (name is unique)
        modelVehicle = objectLayer.createVehicle();
        modelVehicle.setRegistrationTag(tag);
        vehicles = objectLayer.findVehicle( modelVehicle );
//        objectLayer.createVehicle(make, model, year, registrationTag, mileage, lastServiced, vehicleType, rentalLocation, vehicleCondition, vehicleStatus)
        if( vehicles.size() > 0 ) {
        	throw new RARException("A Vehicle with this tage already exists: " + tag);
        }
        // create the vehicle
        RentalLocation                modelLocation = null;
        List<RentalLocation>          rentalLocations= null;
        modelLocation = objectLayer.createRentalLocation();
        modelLocation.setName( location );
        rentalLocations = objectLayer.findRentalLocation( modelLocation );
        VehicleType                modelVehicleType = null;
        List<VehicleType>          vehicleTypes= null;
        modelVehicleType = objectLayer.createVehicleType();
        modelVehicleType.setName( type );
        vehicleTypes = objectLayer.findVehicleType( modelVehicleType );
        vehicle = objectLayer.createVehicle(make, model, year, tag, mileage, new Date(), vehicleTypes.get(0), rentalLocations.get(0), VehicleCondition.GOOD, VehicleStatus.INLOCATION);
        objectLayer.storeVehicle( vehicle );
        return vehicle.getId();
        */
    }
}

/* For updating
 * Vehicle newVehicle = vehicles.get(0);
            newVehicle.setMileage(Integer.parseInt(mileage));
            RentalLocation                modelLocation = null;
            List<RentalLocation>          rentalLocations= null;
            modelLocation = objectLayer.createRentalLocation();
            modelLocation.setName( location );
            rentalLocations = objectLayer.findRentalLocation( modelLocation );
            newVehicle.setRentalLocation(rentalLocations.get(0));
            VehicleType                modelVehicleType = null;
            List<VehicleType>          vehicleTypes= null;
            modelVehicleType = objectLayer.createVehicleType();
            modelVehicleType.setName( type );
            vehicleTypes = objectLayer.findVehicleType( modelVehicleType );
            newVehicle.setVehicleType(vehicleTypes.get(0));
            
            objectLayer.storeVehicle(newVehicle);
            return newRentalLocation.getId();
            */
 

