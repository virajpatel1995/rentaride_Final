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



public class GetReservationCtrl {
    
    private ObjectLayer objectLayer = null;
    
    public GetReservationCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    
    public List<Reservation> getReservation( String uName)
            throws RARException
    { 
        
        
       Customer modelCustomer = objectLayer.createCustomer();
        modelCustomer.setUserName(uName);;
        List<Customer>customers = objectLayer.findCustomer( modelCustomer );
        if( !(customers.size() > 0 ))  
        	throw new RARException("A Cystomer with this name does not exists: " + uName);
        
      
       
        Reservation reservation = objectLayer.createReservation();
        reservation.setCustomer(customers.get(0));
        return objectLayer.findReservation(reservation);
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
 

