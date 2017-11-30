package edu.uga.cs.rentaride.logic.impl;

import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.UserStatus;
import edu.uga.cs.rentaride.object.ObjectLayer;

public class TerminateMembershipCtrl {

private ObjectLayer objectLayer = null;
	
    public TerminateMembershipCtrl( ObjectLayer objectModel ){
        this.objectLayer = objectModel;
    }
    
	public long TerminateMembership( String customerUser) throws RARException{

		//cheack if customer exists
        Customer 		   customer  = null;
        Customer                modelCustomer = null;
        List<Customer>         customers= null;

        // check if a club with this name already exists (name is unique)
        modelCustomer = objectLayer.createCustomer();
        modelCustomer.setUserName(customerUser);
        customers = objectLayer.findCustomer( modelCustomer );
        if(customers.size()<1) throw new RARException("Customer does not exists");
        customer = customers.get(0);
        customer.setUserStatus(UserStatus.TERMINATED);
		objectLayer.storeCustomer(customer);
        
        return customer.getId();
	}
	
}
