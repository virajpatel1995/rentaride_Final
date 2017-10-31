//
// A control class to implement the 'Create club' use case
//
//

package edu.uga.cs.rentaride.logic.impl;


import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.object.ObjectLayer;





public class CreatRARCtrl {
    
    private ObjectLayer objectLayer = null;
    
    public CreatRARCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    
    public long createClub( String club_name, String club_addr, long founderId )
            throws RARException
    { 
        Date 		    estab = null;
        Club 		    club  = null;
        Club                modelClub = null;
        List<Club>          clubs = null;
        Person              founder = null;
        Person              modelPerson = null;
        List<Person>        persons = null;

        // check if a club with this name already exists (name is unique)
        modelClub = objectLayer.createClub();
        modelClub.setName( club_name );
        clubs = objectLayer.findClub( modelClub );
        if( clubs.size() > 0 )
            throw new RARException( "A club with this name already exists: " + club_name );

        // retrieve the founder person
        modelPerson = objectLayer.createPerson();
        modelPerson.setId( founderId );
        persons = objectLayer.findPerson( modelPerson );
        if( persons.size() > 0 ) {
            founder = persons.get( 0 );
        }

        // check if the person (founder) actually exists
        if( founder == null )
            throw new RARException( "A person with this id does not exist: " + founderId );

        // create the club
        estab = new Date();		// mark it as created now
        club = objectLayer.createClub( club_name, club_addr, estab, founder );
        objectLayer.storeClub( club );

        return club.getId();
    }
}

