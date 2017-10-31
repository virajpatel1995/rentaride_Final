//
// A control class to implement the 'Create person' use case
//
//

package edu.uga.cs.rentaride.logic.impl;

import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.object.ObjectLayer;




public class CreatePersonCtrl 
{
    
    private ObjectLayer objectLayer = null;
    
    public CreatePersonCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }
    
    public long createPerson( String userName, String password, String email, String firstName, 
                              String lastName, String address, String phone )
          throws RARException
    { 
        Person               person  = null;
        Person               modelPerson  = null;
        List<Person>         persons  = null;

        // check if the userName already exists
        modelPerson = objectLayer.createPerson();
        modelPerson.setUserName( userName );
        persons = objectLayer.findPerson( modelPerson );
        if( persons.size() > 0 )
            person = persons.get( 0 );
        
        // check if the person actually exists, and if so, throw an exception
        if( person != null )
            throw new RARException( "A person with this user name already exists" );
        
        person = objectLayer.createPerson( userName, password, email, firstName, lastName, address, phone);
        objectLayer.storePerson( person );

        return person.getId();
    }
}

