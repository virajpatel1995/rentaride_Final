//
// A control class to implement the 'Create club' use case
//
//

package edu.uga.cs.rentaride.logic.impl;


import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Administrator;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.entity.VehicleStatus;
import edu.uga.cs.rentaride.entity.VehicleCondition;



public class UpdateAdministratorCtrl {
    
    private ObjectLayer objectLayer = null;
    
    public UpdateAdministratorCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    
    public long updateAdministrator( String username, String fName, String lName, String email, String address, String city, String state, String zip)
            throws RARException
    { 
        Administrator 		   administrator  = null;
        Administrator                modelAdministrator = null;
        List<Administrator>         administrators= null;

        // check if a club with this name already exists (name is unique)
        modelAdministrator = objectLayer.createAdministrator();
        modelAdministrator.setUserName(username);;
        administrators = objectLayer.findAdministrator( modelAdministrator );
        if( administrators.size() > 0 ) {
        	administrator = administrators.get(0);
        	administrator.setFirstName(fName);
        administrator.setLastName(lName);
        administrator.setEmail(email);
        administrator.setAddress(address + ", " + city +", "+ state+", " + zip);
           
            objectLayer.storeAdministrator(administrator);
            return administrator.getId();
        }
        else {

        	throw new RARException("A Administrator with this username does not already exists: " + username);
        }
        // create the vehicle
    }
}

 

