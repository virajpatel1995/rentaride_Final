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



public class CreateVehicleTypeCtrl {
    
    private ObjectLayer objectLayer = null;
    
    public CreateVehicleTypeCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    
    public long CreateVehicleType( String type )
            throws RARException
    { 
        VehicleType 		     vehicleType  = null;
        VehicleType              modelVehicleType = null;
        List<VehicleType>        vehicleTypes= null;

        // check if a type with this name already exists (name is unique)
        modelVehicleType = objectLayer.createVehicleType();
        modelVehicleType.setName(type);
        vehicleTypes = objectLayer.findVehicleType( modelVehicleType );
        if( vehicleTypes.size() > 0 ) {
        	throw new RARException("A Vehicletype with this name already exists: ");
        }
        vehicleType = objectLayer.createVehicleType(type);
        objectLayer.storeVehicleType( vehicleType );
        return vehicleType.getId();
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
 

