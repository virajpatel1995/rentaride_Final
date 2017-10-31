package edu.uga.clubs.logic.impl;

import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.entity.Person;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.session.Session;
import edu.uga.cs.session.SessionManager;

public class LoginCtrl
{ 
    private ObjectLayer objectLayer = null;
    
    public LoginCtrl( ObjectLayer objectLayer )
    {
        this.objectLayer = objectLayer;
    }
    
    public String login( Session session, String userName, String password )
            throws ClubsException
    {
        String ssid = null;
        
        Person modelPerson = objectLayer.createPerson();
        modelPerson.setUserName( userName );
        modelPerson.setPassword( password );
        List<Person> persons = objectLayer.findPerson( modelPerson );
        if( persons.size() > 0 ) {
            Person person = persons.get( 0 );
            session.setUser( person );
            ssid = SessionManager.storeSession( session );
        }
        else
            throw new RARException( "SessionManager.login: Invalid User Name or Password" );
        
        return ssid;
    }
}
