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



public class UpdateVehicleTypeCtrl {
    
    private ObjectLayer objectLayer = null;
    
    public UpdateVehicleTypeCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    
    public long UpdateVehicleType( String oldType, String newType )
            throws RARException
    { 
        VehicleType 		     vehicleType  = null;
        VehicleType              modelVehicleType = null;
        List<VehicleType>        vehicleTypes= null;

        // check if a type with this name already exists (name is unique)
        modelVehicleType = objectLayer.createVehicleType();
        modelVehicleType.setName(oldType);
        vehicleTypes = objectLayer.findVehicleType( modelVehicleType );
        if( vehicleTypes.size() > 0 ) {
        	vehicleType = vehicleTypes.get(0);
        	vehicleType.setName(newType);
        	objectLayer.storeVehicleType(vehicleType);
        	return vehicleType.getId();
        }
        else {
        	throw new RARException("A Vehicletype with this name does not exist: ");
        }
        
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
 

