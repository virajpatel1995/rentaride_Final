//
// A control class to implement the 'Create club' use case
//
//

package edu.uga.cs.rentaride.logic.impl;


import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.HourlyPrice;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.entity.VehicleStatus;
import edu.uga.cs.rentaride.entity.VehicleCondition;



public class UpdateHourlyPriceCtrl {
    
    private ObjectLayer objectLayer = null;
    
    public UpdateHourlyPriceCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    
    public long updateHourlyPrice( String vehicleType, int hourlyPrice, int maxHours )
            throws RARException
    { 
        HourlyPrice 		     price = null;
        HourlyPrice				 modelPrice = objectLayer.createHourlyPrice();
        VehicleType				 vehicleType1 = null;
        VehicleType              modelVehicleType = null;
        List<VehicleType>        vehicleTypes= null;
        List<HourlyPrice>        hps= null;

        // check if a type with this name already exists (name is unique)
        modelVehicleType = objectLayer.createVehicleType();
        modelVehicleType.setName(vehicleType);
        vehicleTypes = objectLayer.findVehicleType( modelVehicleType );
        if( vehicleTypes.size() > 0 ) {
        	vehicleType1 = vehicleTypes.get(0);
        	modelPrice.setVehicleType(vehicleType1);
        	hps= objectLayer.findHourlyPrice(modelPrice);
        	if(hps.size() > 0){
        		price = hps.get(0);
        	}
        	price.setVehicleType(vehicleType1);
        	price.setMaxHours(maxHours);
        	price.setPrice(hourlyPrice);
        	objectLayer.storeHourlyPrice(price);
        	return price.getId();
        }
        else {
        	throw new RARException("A Vehicletype with this name does not exist: ");
        }
        
    }
}

