//
// A control class to implement the 'Create club' use case
//
//

package edu.uga.cs.rentaride.logic.impl;


import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.entity.VehicleStatus;
import edu.uga.cs.rentaride.entity.VehicleCondition;



public class UpdateCustomerCtrl {
    
    private ObjectLayer objectLayer = null;
    
    public UpdateCustomerCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    
    public long updateCustomer( String username, String fName, String lName, String email, String address, String city, String state, String zip)
            throws RARException
    { 
        Customer 		   customer  = null;
        Customer                modelCustomer = null;
        List<Customer>         customers= null;

        // check if a club with this name already exists (name is unique)
        modelCustomer = objectLayer.createCustomer();
        modelCustomer.setUserName(username);;
        customers = objectLayer.findCustomer( modelCustomer );
        if( customers.size() > 0 ) {
        customer = customers.get(0);
            customer.setFirstName(fName);
            customer.setLastName(lName);
            customer.setEmail(email);
            customer.setAddress(address + ", " + city +", "+ state+", " + zip);
           
            objectLayer.storeCustomer(customer);
            return customer.getId();
        }
        else {

        	throw new RARException("A Customer with this username does not already exists: " + username);
        }
        // create the vehicle
    }
}

 

