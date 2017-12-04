package edu.uga.cs.rentaride.logic.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Administrator;
import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.User;
import edu.uga.cs.rentaride.entity.UserStatus;
import edu.uga.cs.rentaride.object.ObjectLayer;

public class CancelReservationCtrl {

private ObjectLayer objectLayer = null;
	
    public CancelReservationCtrl( ObjectLayer objectModel ){
        this.objectLayer = objectModel;
    }
    
	public long cancelReservation( String res) throws RARException{

		//cheack if customer exists
		Reservation 		    reservation  = null;
        Reservation                modelReservation = null;
        List<Reservation>          reservations= null;
        modelReservation = objectLayer.createReservation();
        modelReservation.setId(Long.parseLong(res));
        reservations = objectLayer.findReservation(modelReservation);
        if(reservations.size()<1) throw new RARException("Reservation does not exists");
        reservation = reservations.get(0);
        
        java.util.Date d = new Date();
        
        if(d.getTime() > reservation.getPickupTime().getTime()  +3600000) {
        	
            long charges = objectLayer.findRentARideParams().getLateFee();
            //Charge Credit Card with charges
            throw new RARException("Your card has been charged for cancelling one hour past the acceptable time.");
        
        }else {
        
        objectLayer.cancelReservation(reservation);
        }
        return reservation.getId();
        

		
	    	
    }
}

