//
// A control class to implement the 'Join a club' use case
//
//


package edu.uga.cs.rentaride.logic.impl;




import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.object.ObjectLayer;





public class JoinRARCtrl {
    
    private ObjectLayer objectLayer = null;

    public JoinRARCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }

    public long joinClub( long personId, String clubName )
            throws RARException
    {
        Person               person = null;
        Person               modelPerson = null;
        List<Person>         persons = null;
        Club                 club = null;
        Club                 modelClub = null;
        List<Club>           clubs = null;
        Membership           membership = null;
        Membership           modelMembership = null;
        List<Membership>     memberships = null;

        modelClub = objectLayer.createClub();
        modelClub.setName( clubName );
        clubs = objectLayer.findClub( modelClub );
        if( clubs.size() > 0 ) {
            club = clubs.get( 0 );
            System.out.println( "CtrlJoinClub.joinClub: found club: " + club );
        }
        if( club == null )
            throw new RARException( "Club does not exist; name: " + clubName );

        modelPerson = objectLayer.createPerson();
        modelPerson.setId( personId );
        persons = objectLayer.findPerson( modelPerson );
        if( persons.size() > 0 ) {
            person = persons.get( 0 );
            System.out.println( "CtrlJoinClub.joinClub: found person: " + person );
        }
        if( person == null )
            throw new RARException( "Person does not exist; id: " + personId );

        modelMembership = objectLayer.createMembership();
        modelMembership.setPerson( person );
        modelMembership.setClub( club );
        memberships = objectLayer.findMembership( modelMembership );
        if( memberships.size() > 0 )
            throw new RARException( "This person is already a member of this club" );

        membership = objectLayer.createMembership( person, club, new Date() );
        objectLayer.storeMembership( membership );

        return membership.getId();
    }
}
