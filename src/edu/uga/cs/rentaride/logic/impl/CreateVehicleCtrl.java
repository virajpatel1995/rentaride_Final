//
// A control class to implement the 'Create club' use case
//
//

package edu.uga.cs.rentaride.logic.impl;


import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.entity.VehicleStatus;
import edu.uga.cs.rentaride.entity.VehicleCondition;



public class CreateVehicleCtrl {
    
    private ObjectLayer objectLayer = null;
    
    public CreateVehicleCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    
    public long createVehicle( String make, String model, int year, int mileage, String tag, String location, String type)
            throws RARException
    { 
        Vehicle 		    vehicle  = null;
        Vehicle                modelVehicle = null;
        List<Vehicle>          vehicles= null;

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
 

