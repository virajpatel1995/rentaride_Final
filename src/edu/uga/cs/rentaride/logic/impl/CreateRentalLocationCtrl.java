//
// A control class to implement the 'Create club' use case
//
//

package edu.uga.cs.rentaride.logic.impl;


import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.object.ObjectLayer;



public class CreateRentalLocationCtrl {
    
    private ObjectLayer objectLayer = null;
    
    public CreateRentalLocationCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    
    public long createRentalLocation( String name, String addr, int capacity )
            throws RARException
    { 
        RentalLocation 		    location  = null;
        RentalLocation                modelLocation = null;
        List<RentalLocation>          rentalLocations= null;

        // check if a club with this name already exists (name is unique)
        modelLocation = objectLayer.createRentalLocation();
        modelLocation.setName( name );
        rentalLocations = objectLayer.findRentalLocation( modelLocation );
        if( rentalLocations.size() > 0 ) {
            RentalLocation newRentalLocation = rentalLocations.get(0);
            newRentalLocation.setAddress(addr);
            newRentalLocation.setCapacity(capacity);
            objectLayer.storeRentalLocation(newRentalLocation);
            return newRentalLocation.getId();
//            throw new RARException("A Location with this name already exists: " + name);
        }
        // create the location
        location = objectLayer.createRentalLocation( name, addr, capacity );
        objectLayer.storeRentalLocation( location );

        return location.getId();
    }
}

