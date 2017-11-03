package edu.uga.cs.rentaride.logic;


import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.*;
import edu.uga.cs.rentaride.session.*;

import edu.uga.cs.rentaride.RARException;


public interface LogicLayer
{
	

	public void               logout( String ssid ) throws RARException;
    public String             login( Session session, String userName, String password ) throws RARException;
	

}
