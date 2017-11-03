package edu.uga.cs.rentaride.logic;


import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.*;
import edu.uga.cs.rentaride.session.*;

import edu.uga.cs.rentaride.RARException;


public interface LogicLayer
{
	
	/*
    public List<Rental>         findAllRentals() throws RARException;
    public List<Customer>       findAllCustomers() throws RARException;
    public long               joinClub( Customer customer, Club club ) throws RARException;
    public long               joinClub( long customerId, String clubName ) throws RARException;
    public long               createClub( String clubName, String address, long founderId ) throws RARException;
    public long               createCustomer( String firstName, String lastName, String userName, String password, String email,  
                                            String address, String licState, String licNumber, String ccNumber, String ccExpiration ) throws RARException;
    public List<Customer>       findClubMembers( String clubName ) throws RARException;
   */
	//public List<Administrator> findAllAdministrators() throws RARException;
	public void               logout( String ssid ) throws RARException;
    public String             login( Session session, String userName, String password ) throws RARException;
	

}
