//
// A control class to implement the 'List all persons' use case
//
//


package edu.uga.cs.rentaride.logic.impl;




import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.object.ObjectLayer;






public class FindAllPersonsCtrl {
    
    private ObjectLayer objectLayer = null;
    
    public FindAllPersonsCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }

    public List<Person> findAllPersons()
            throws RARException
    {
        List<Person>      persons  = null;
        
        // retrieve all Person objects
        //
        persons = objectLayer.findPerson( null );

        return persons;
    }
}
