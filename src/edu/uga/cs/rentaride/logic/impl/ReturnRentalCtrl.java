//
// A control class to implement the 'Create club' use case
//
//

package edu.uga.cs.rentaride.logic.impl;


import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.entity.VehicleStatus;
import edu.uga.cs.rentaride.entity.VehicleCondition;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;



public class ReturnRentalCtrl {
    
    private ObjectLayer objectLayer = null;
    
    public ReturnRentalCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    
    public long returnRental( String rentalID)
            throws RARException
    { 
    	Rental						rental = null;
    	Rental						modelRental = null;
    	List<Rental>				rentals = null;
    	long 						rentalIDLong = Long.parseLong(rentalID);
    	// check if a club with this name already exists (name is unique)
    	modelRental = objectLayer.createRental();
    	modelRental.setId(rentalIDLong);
    	rentals = objectLayer.findRental(modelRental);
    	if( !(rentals.size() > 0)) {
    		throw new RARException("A Rental with this ID does not exist: " + rentalID);
    	}
    	objectLayer.returnRental(rentals.get(0));
    	
    	Vehicle vehicle = rental.getVehicle();
    	vehicle.setStatus(VehicleStatus.INLOCATION);
    	objectLayer.storeVehicle(vehicle);
       
    	return rental.getId();

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
 

