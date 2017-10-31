package edu.uga.cs.rentaride.logic;


import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.*;
import edu.uga.clubs.session.Session;
import edu.uga.cs.rentaride.session;

import edu.uga.cs.rentaride.RARException;


public interface LogicLayer
{
    public List<Club>         findAllClubs() throws RARException;
    public List<Person>       findAllPersons() throws RARException;
    public long               joinClub( Person person, Club club ) throws RARException;
    public long               joinClub( long personId, String clubName ) throws RARException;
    public long               createClub( String clubName, String address, long founderId ) throws RARException;
    public long               createPerson( String userName, String password, String email, String firstName, 
                                            String lastName, String address, String phone ) throws RARException;
    public List<Person>       findClubMembers( String clubName ) throws RARException;
    public void               logout( String ssid ) throws RARException;
    public String             login( Session session, String userName, String password ) throws RARException;
}
