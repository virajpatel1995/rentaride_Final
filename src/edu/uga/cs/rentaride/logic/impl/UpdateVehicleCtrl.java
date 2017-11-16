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



public class UpdateVehicleCtrl {
    
    private ObjectLayer objectLayer = null;
    
    public UpdateVehicleCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    
    public long UpdateVehicle( String mileage, String tag, String location, boolean maintence)
            throws RARException
    { 
        Vehicle 		    vehicle  = null;
        Vehicle                modelVehicle = null;
        List<Vehicle>          vehicles= null;

        // check if a club with this name already exists (name is unique)
        modelVehicle = objectLayer.createVehicle();
        modelVehicle.setRegistrationTag(tag);
        vehicles = objectLayer.findVehicle( modelVehicle );
        if( vehicles.size() > 0 ) {
        vehicle = vehicles.get(0);
            vehicle.setMileage(Integer.parseInt(mileage));
            RentalLocation                modelLocation = null;
            List<RentalLocation>          rentalLocations= null;
            modelLocation = objectLayer.createRentalLocation();
            modelLocation.setName( location );
            rentalLocations = objectLayer.findRentalLocation( modelLocation );
            vehicle.setRentalLocation(rentalLocations.get(0));
            vehicle.setLastServiced(new Date());
            if(maintence) vehicle.setCondition(VehicleCondition.NEEDSMAINTENANCE);
            else vehicle.setCondition(VehicleCondition.GOOD);
            objectLayer.storeVehicle(vehicle);
            return vehicle.getId();
        }
        else {

        	throw new RARException("A Vehicle with this tag does not already exists: " + tag);
        }
        // create the vehicle
    }
}

 

