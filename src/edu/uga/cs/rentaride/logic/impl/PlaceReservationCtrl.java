//
// A control class to implement the 'Create club' use case
//
//

package edu.uga.cs.rentaride.logic.impl;


import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.entity.VehicleStatus;
import edu.uga.cs.rentaride.entity.VehicleCondition;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;



public class PlaceReservationCtrl {
    
    private ObjectLayer objectLayer = null;
    
    public PlaceReservationCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    
    public long placeReservation( String time, String dur, String loc, String type, String uName)
            throws RARException
    { 
        VehicleType 		    vehicleType  = null;
        VehicleType                modelVehicleType = null;
        List<VehicleType>          vehicleTypes= null;

        // check if a club with this name already exists (name is unique)
        modelVehicleType = objectLayer.createVehicleType();
        modelVehicleType.setName(type);;
        vehicleTypes = objectLayer.findVehicleType( modelVehicleType );
//        objectLayer.createVehicle(make, model, year, registrationTag, mileage, lastServiced, vehicleType, rentalLocation, vehicleCondition, vehicleStatus)
        if( !(vehicleTypes.size() > 0 )) {
        	throw new RARException("A Vehicle Type with this name does not exists: " + type);
        }
        // create the vehicle
        
        RentalLocation                modelLocation = null;
        List<RentalLocation>          rentalLocations= null;
        modelLocation = objectLayer.createRentalLocation();
        modelLocation.setName( loc );
        rentalLocations = objectLayer.findRentalLocation( modelLocation );
        if( !(rentalLocations.size() > 0 )) {
        	throw new RARException("A Location with this name does not exists: " + loc);
        }
        
       Customer modelCustomer = objectLayer.createCustomer();
        modelCustomer.setUserName(uName);;
        List<Customer>customers = objectLayer.findCustomer( modelCustomer );
        if( !(customers.size() > 0 ))  
        	throw new RARException("A Cystomer with this name does not exists: " + uName);
        
      
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss"); 
        Date pickupTime;
        try {
            pickupTime = df.parse(time);
        }catch(ParseException e) {
        		pickupTime = new Date();
        }
       
        Reservation reservation = objectLayer.createReservation(pickupTime, Integer.parseInt(dur), vehicleTypes.get(0), rentalLocations.get(0), customers.get(0));
        	objectLayer.storeReservation( reservation);
        return reservation.getId();
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
 

