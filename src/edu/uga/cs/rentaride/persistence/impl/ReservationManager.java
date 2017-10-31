package edu.uga.cs.rentaride.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.*;
import edu.uga.cs.rentaride.entity.impl.*;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.persistence.PersistenceLayer;

public class ReservationManager {
	
	//variables for objectlayer and connection
	private ObjectLayer objectLayer = null;
	private Connection conn = null;
	
	public ReservationManager(Connection conn, ObjectLayer objectLayer) {
		this.conn = conn;
		this.objectLayer = objectLayer;
	}//constructor
	
	public void store(Reservation reservation) throws RARException{
		String insertReservationSql = "insert into reservation (pickup, length, canceled, userid, rentalLocationid, vehicleTypeid) values ( ?, ?, ?, ?, ?, ? )";
		String updateReservationSql = "update person  set  pickup = ?, length = ?, canceled = ?, userid = ?, rentalLocationid = ?, vehicleTypeid = ? where id = ?";
		java.sql.PreparedStatement stmt = null;
		int inscnt;
		long reservationId;
		
		if(reservation.getRentalLocation() == null)
			throw new RARException ("Reservation.save: Attempting ot save a Reservation with no RentalLocation defined");
		if(reservation.getVehicleType() == null)
			throw new RARException ("Reservation.save: Attempting ot save a Reservation with no VehicleType defined");
		if(reservation.getCustomer() == null)
			throw new RARException ("Reservation.save: Attempting ot save a Rental with no Reservation defined");
		if(!reservation.getRentalLocation().isPersistent())	
			throw new RARException ("Reservation.save: Attempting ot save a Rental Where RentalLocation is not persistent");
		if(!reservation.getVehicleType().isPersistent())	
			throw new RARException ("Reservation.save: Attempting ot save a Rental Where VehicleType is not persistent");
		if(!reservation.getCustomer().isPersistent())	
			throw new RARException ("Reservation.save: Attempting ot save a Reservation Where Customer is not persistent");
		
		try {
	
			if(!reservation.isPersistent())
				stmt = (java.sql.PreparedStatement) conn.prepareStatement(insertReservationSql);
			else
				stmt = (java.sql.PreparedStatement) conn.prepareStatement(updateReservationSql);
		
			if(reservation.getPickupTime() != null)
				stmt.setDate(1,new java.sql.Date(reservation.getPickupTime().getTime()));
			else
				throw new RARException("ResevrationManager.save: can't save a Reservation: Pickup Time undefined");

			if(reservation.getLength() > 0)
				stmt.setInt(2,reservation.getLength());
			else
				throw new RARException("ResevrationManager.save: can't save a Reservation: Length undefined");
			
			stmt.setBoolean(3, false);;

			stmt.setLong(4,  reservation.getCustomer().getId());
			stmt.setLong(5,  reservation.getRentalLocation().getId());
			stmt.setLong(6,  reservation.getVehicleType().getId());
			
			if(reservation.isPersistent())
				stmt.setLong(8, reservation.getId());
		
			inscnt = stmt.executeUpdate();
			
			if(!reservation.isPersistent()) {
				if(inscnt == 1) {
					String sql = "select last_insert_id()";
					if(stmt.execute(sql)) {
						//retrieve the result
						ResultSet r =stmt.getResultSet();
						while(r.next()) {
							reservationId = r.getLong(1);
							if(reservationId > 0)
								reservation.setId(reservationId);
						}//while
					}//if
				}//if
			}else {
				if(inscnt < 1)
					throw new RARException("ReservationManager.save: failed to save a reservation");
			}//if else
			
		}catch (SQLException e) {

			e.printStackTrace();
				throw new RARException("ReservationManager.save: Failed to save a Reservation: " + e);
		}//try catch
	}//store
	
	public List<Reservation> restore(Reservation reservation) throws RARException{

		{
			String       selectReservationSql = "select id, pickup, length, canceled, " +
					"userid, rentalLocationid, vehicleTypeid from reservation";
			Statement    stmt = null;
			StringBuffer query = new StringBuffer( 100 );
			StringBuffer condition = new StringBuffer( 100 );
			List<Reservation> reservations = new ArrayList<>();

			condition.setLength( 0 );

			// form the query based on the given Person object instance
			query.append( selectReservationSql );
			if(reservation != null){
				if(reservation.getId() >= 0)		//vehicle id is unique
					query.append(" where id = " + reservation.getId());
				else {

					if( reservation.getPickupTime() != null )
						condition.append( " pickup = '" + reservation.getPickupTime() + "'" );

					if( reservation.getLength() >= 0 ) {
						if( condition.length() > 0 )
							condition.append( " and" );
						condition.append( " length = '" + reservation.getLength() + "'" );
					}

					if( reservation.getCustomer() != null && reservation.getCustomer().getId() >= 0 ) {
						if( condition.length() > 0 )
							condition.append( " and" );
						condition.append( " userid = '" + reservation.getCustomer().getId() + "'" );
					}

					if( reservation.getRentalLocation() != null &&reservation.getRentalLocation().getId() >= 0 ) {
						if( condition.length() > 0 )
							condition.append( " and" );
						condition.append( " rentalLocationid = '" + reservation.getRentalLocation().getId() + "'" );
					}

					if( reservation.getVehicleType() != null &&reservation.getVehicleType().getId() >= 0 ) {
						if( condition.length() > 0 )
							condition.append( " and" );
						condition.append( " vehicleTypeid = '" + reservation.getVehicleType().getId() + "'" );
					}

					if( condition.length() > 0 ) {
						query.append(  " where " );
						query.append( condition );
					}
				}
			}

			try {

				stmt = conn.createStatement();

				// retrieve the persistent Administrator objects
				//
				if( stmt.execute( query.toString() ) ) { // statement returned a result
					ResultSet rs = stmt.getResultSet();

					long id;
					Date pickupDate;
					int length;
					int customerid;
					int rentalLocationid;
					int vehicleTypeid;
					Reservation reservation1;


					while( rs.next() ) {
/**
 *  columnIndex need to match column index in database
 */
						id = rs.getInt(1);
						pickupDate = rs.getDate(2);
						length = rs.getInt(3);
						//skipped cancelled
						customerid = rs.getInt(5);
						rentalLocationid = rs.getInt(6);
						vehicleTypeid = rs.getInt(7);

						PersistenceLayer persistenceLayer = Persistence.getPersistencvalayer();

						CustomerManager customerManager = new CustomerManager(conn, objectLayer);
						Customer modelCustomer = new CustomerImpl();
						modelCustomer.setId(customerid);
						modelCustomer = customerManager.restore(modelCustomer).get(0);

						RentalLocationManager rentalLocationManager = new RentalLocationManager(conn, objectLayer);
						RentalLocation modelRentalLocation = new RentalLocationImpl();
						modelRentalLocation.setId(rentalLocationid);
						modelRentalLocation = rentalLocationManager.restore(modelRentalLocation).get(0);

						VehicleTypeManager vehicleTypeManager = new VehicleTypeManager(conn, objectLayer);
						VehicleType modelVehicleType = new VehicleTypeImpl();
						modelVehicleType.setId(vehicleTypeid);
						modelVehicleType = vehicleTypeManager.restore(modelVehicleType).get(0);


						reservation1 = objectLayer.createReservation(pickupDate, length, modelVehicleType, modelRentalLocation, modelCustomer);
						reservation1.setId(id);

						reservations.add( reservation1);

					}

					return reservations;
				}
			}
			catch( Exception e ) {      // just in case...
				throw new RARException( "ReservationManager.restore: Could not restore persistent Reservation object; Root cause: " + e );
			}

			// if we get to this point, it's an error
			throw new RARException( "ReservationManager.restore: Could not restore persistent Reservation objects" );
		}
	}//restore


	
	public void delete(Reservation reservation) throws RARException{
		
		String deleteReservationSql = "delete from reservation where id = ?";              
		PreparedStatement stmt = null;
		int inscnt = 0;
		             
		        if( !reservation.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
		            return;
		        
		        try {
		            stmt = (PreparedStatement) conn.prepareStatement( deleteReservationSql );         
		            stmt.setLong( 1, reservation.getId() );
		            inscnt = stmt.executeUpdate();          
		            if( inscnt == 1 ) {
		                return;
		            }
		            else
		                throw new RARException( "ReservationManager.delete: failed to delete a reservation" );
		        }
		        catch( SQLException e ) {
		            e.printStackTrace();
		            throw new RARException( "ReservationManager.delete: failed to delete a reservation: " + e );       
		            }
		
		
		
	}//delete
	
}//ReservationManager