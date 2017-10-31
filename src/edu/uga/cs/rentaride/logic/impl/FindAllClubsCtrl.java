//
// A control class to implement the 'List all clubs' use case
//
//


package edu.uga.cs.rentaride.logic.impl;




import java.util.List;


import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.object.ObjectLayer;



public class FindAllClubsCtrl {
    
    private ObjectLayer objectLayer = null;
    
    public FindAllClubsCtrl( ObjectLayer objectModel )
    {
        this.objectLayer = objectModel;
    }

    public List<Club> findAllClubs()
            throws RARException
    {
        List<Club> 	clubs  = null;
        
        // retrieve all Club objects
        //
        clubs = objectLayer.findClub( null );

        return clubs;
    }
}
