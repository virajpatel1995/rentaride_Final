package edu.uga.cs.rentaride.logic.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Administrator;
import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.User;
import edu.uga.cs.rentaride.entity.UserStatus;
import edu.uga.cs.rentaride.object.ObjectLayer;

public class CreateCommentCtrl {

private ObjectLayer objectLayer = null;
	
    public CreateCommentCtrl( ObjectLayer objectModel ){
        this.objectLayer = objectModel;
    }
    
	public long createComment( String comm, long ren) throws RARException{

		//cheack if customer exists
		Rental modelRental = objectLayer.createRental();
        modelRental.setId(ren);
        List<Rental> rentals = objectLayer.findRental( modelRental );
        

        if( rentals.size() > 0 ) {
        		

    			Comment modelComment = objectLayer.createComment();
            modelComment.setRental(rentals.get(0));
            List<Comment> comments = objectLayer.findComment( modelComment );
        
            if(comments.size() > 0) {
            		comments.get(0).setText(comm);
            		objectLayer.storeComment(comments.get(0));
            		return comments.get(0).getId();
            }else {

            	//Creating the comment
    	   Rental rental = rentals.get(0);
    			
    	   Comment comment = null;
    	   comment  = objectLayer.createComment(comm, new Date(), rental, rental.getCustomer());
    	
    	    	objectLayer.storeComment(comment);

    	    	return comment.getId();
            }
        }else{
        		throw new RARException("SessionManager.CreateComment: Invalid Rental Location ");
        }

		
	    	
    }
}

