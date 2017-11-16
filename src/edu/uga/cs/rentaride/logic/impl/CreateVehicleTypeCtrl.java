//
// A control class to implement the 'Create club' use case
//Author: Shepherd Ogden 11/15/17
//

package edu.uga.cs.rentaride.logic.impl;


import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.object.ObjectLayer;



public class CreateVehicleTypeCtrl {
    
    private ObjectLayer objectLayer = null;
    
    public CreateVehicleTypeCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    
    public long createVehicleType( String name )
            throws RARException
    { 
        VehicleType 		       type  = null;
        VehicleType                modelType = null;
        List<VehicleType>          vehicleTypes = null;

        // check if a club with this name already exists (name is unique)
        modelType = objectLayer.createVehicleType();
        modelType.setName( name );

        vehicleTypes = objectLayer.findVehicleType(modelType);
        if( vehicleTypes.size() > 0) {
        	VehicleType newVehicleType = vehicleTypes.get(0);
        	newVehicleType.setName( name );
        	objectLayer.storeVehicleType(newVehicleType);
        	return newVehicleType.getId();
        }
        
        // create the vehicleType
        type = objectLayer.createVehicleType( name );
        objectLayer.storeVehicleType( type );

        return type.getId();
    }
}

