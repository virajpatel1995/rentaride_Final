package edu.uga.cs.rentaride.logic.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Administrator;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.User;
import edu.uga.cs.rentaride.entity.UserStatus;
import edu.uga.cs.rentaride.object.ObjectLayer;

public class registerAccountCtrl {

private ObjectLayer objectLayer = null;
	
    public registerAccountCtrl( ObjectLayer objectModel ){
        this.objectLayer = objectModel;
    }
    
	public long createAccount( String firstName, String lastName, String userName, String email, String password, String driverNo, String cardNo, String expDate, String address, String state, String zip ) throws RARException, ParseException{

		//cheack if customer exists
		Customer modelCustomer = objectLayer.createCustomer();
        modelCustomer.setUserName( userName );
        List<Customer> customers = objectLayer.findCustomer( modelCustomer );


        if( customers.size() > 0 ) throw new RARException("SessionManager.Register: Invalid User Name already in use");

		
	    	//Creating the customer
	    	Customer		customer = null;
	    	
	    	//combining the address
	    	address = address + ", " + state + ", "+ zip;
			
	    	//TODO
	    Date date1 = new Date();
	    Date date2 = new Date();
	    Date date3 = new Date();
	    	//Passing into the object layer for create customer 
	    	customer = objectLayer.createCustomer(firstName, lastName, userName, password, email, address, date1, date2, state, driverNo, cardNo, new SimpleDateFormat("dd-mm-yyy").parse(expDate), UserStatus.ACTIVE);
	   
	    	objectLayer.storeCustomer(customer);
	    

	    	return customer.getId();
    }
}

