package edu.uga.cs.rentaride.logic;


import java.text.ParseException;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.session.*;

import edu.uga.cs.rentaride.RARException;


public interface LogicLayer
{
	

	public long registerAccount ( String fName, String lName, String email, String password, String username, String driverNo, String cardNo, String expDate, String address, String state, String zip ) throws RARException, ParseException;

	public void               logout( String ssid ) throws RARException;
    public String             login( Session session, String userName, String password ) throws RARException;
    public long               createRentalLocation( String locationName, String address, int locationCapacity ) throws RARException;


}
