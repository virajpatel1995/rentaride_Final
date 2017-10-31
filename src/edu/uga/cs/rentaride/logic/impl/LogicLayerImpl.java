/**
 * 
 */
package edu.uga.cs.rentaride.logic.impl;

import java.sql.Connection;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.*;
import edu.uga.cs.rentaride.logic.*;
import edu.uga.cs.rentaride.object.*;
import edu.uga.cs.rentaride.object.impl.*;
import edu.uga.cs.rentaride.persistence.*;
import edu.uga.cs.rentaride.persistence.impl.*;
import edu.uga.cs.rentaride.session.*;


/**
 * @author Krys J. Kochut
 *
 */
public class LogicLayerImpl 
    implements LogicLayer
{
    private ObjectLayer objectLayer = null;

    public LogicLayerImpl( Connection conn )
    {
        objectLayer = new ObjectLayerImpl();
        PersistenceLayer persistenceLayer = null;
		try {
			persistenceLayer = new PersistenceLayerImpl( conn, objectLayer );
		} catch (RARException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        objectLayer.setPersistence( persistenceLayer );
        System.out.println( "LogicLayerImpl.LogicLayerImpl(conn): initialized" );
    }
    
    public LogicLayerImpl( ObjectLayer objectLayer )
    {
        this.objectLayer = objectLayer;
        System.out.println( "LogicLayerImpl.LogicLayerImpl(objectLayer): initialized" );
    }

    public List<Club> findAllClubs() 
            throws RARException
    {
        FindAllClubsCtrl ctrlFindAllClubs = new FindAllClubsCtrl( objectLayer );
        return ctrlFindAllClubs.findAllClubs();
    }

    public List<Person> findAllPersons() 
            throws ClubsException
    {
        FindAllPersonsCtrl ctrlFindAllPersons = new FindAllPersonsCtrl( objectLayer );
        return ctrlFindAllPersons.findAllPersons();
    }

    public long joinClub(Person person, Club club) throws ClubsException
    {
        return 0;
    }

    public long joinClub(long personId, String clubName) throws ClubsException
    {
        JoinClubCtrl ctrlJoinClub = new JoinClubCtrl( objectLayer );
        return ctrlJoinClub.joinClub( personId, clubName );
    }

    public long createClub(String clubName, String address, long founderId)
            throws ClubsException
    {
        CreateClubCtrl ctrlCreateClub = new CreateClubCtrl( objectLayer );
        return ctrlCreateClub.createClub( clubName, address, founderId );
    }

    public long createPerson(String userName, String password, String email,
            String firstName, String lastName, String address, String phone)
            throws ClubsException
    {
        CreatePersonCtrl ctrlCreatePerson = new CreatePersonCtrl( objectLayer );
        return ctrlCreatePerson.createPerson( userName, password, email, firstName,
                                              lastName, address, phone );
    }

    public List<Person> findClubMembers(String clubName) throws ClubsException
    {
        FindClubMembersCtrl ctrlFindClubMembers = new FindClubMembersCtrl( objectLayer );
        return ctrlFindClubMembers.findClubMembers( clubName );
    }

    public void logout( String ssid ) throws ClubsException
    {
        SessionManager.logout( ssid );
    }

    public String login( Session session, String userName, String password ) 
            throws ClubsException
    {
        LoginCtrl ctrlVerifyPerson = new LoginCtrl( objectLayer );
        return ctrlVerifyPerson.login( session, userName, password );
    }
}
