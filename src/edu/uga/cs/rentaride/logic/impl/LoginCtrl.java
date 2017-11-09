package edu.uga.cs.rentaride.logic.impl;

import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Administrator;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.User;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.session.Session;
import edu.uga.cs.rentaride.session.SessionManager;

public class LoginCtrl
{ 
    private ObjectLayer objectLayer = null;
    
    public LoginCtrl( ObjectLayer objectLayer )
    {
        this.objectLayer = objectLayer;
    }
    
    public String login( Session session, String userName, String password )
            throws RARException
    {
        String ssid = null;
        
        Customer modelCustomer = objectLayer.createCustomer();
        modelCustomer.setUserName( userName );
        modelCustomer.setPassword( password );
        List<Customer> customers = objectLayer.findCustomer( modelCustomer );
        
        Administrator modelAdmin = objectLayer.createAdministrator();
        modelAdmin.setUserName( userName );
        modelAdmin.setPassword( password );
        List<Administrator> administrators = objectLayer.findAdministrator( modelAdmin );
        User user;
        if( customers.size() > 0 ) {
            user = customers.get( 0 );
        }else if(administrators.size() > 0) {
            user = administrators.get(0);
        }else {
            throw new RARException("SessionManager.login: Invalid User Name or Password PLZ");
        }
        session.setUser(user);
        ssid = SessionManager.storeSession(session);

        return ssid;
    }
}
