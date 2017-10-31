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
import edu.uga.cs.rentaride.object.ObjectLayer;

public class VehicleManager {
	
	//variables for objectlayer and connection
	private ObjectLayer objectLayer = null;
	private Connection conn = null;
	
	public VehicleManager(Connection conn, ObjectLayer objectLayer) {
		this.conn = conn;
		this.objectLayer = objectLayer;
	}//constructor
	
	public void store(Vehicle vehicle) throws RARException{
		String insertVehicleSql = "insert into vehicle (make, model, year, mileage, tag, lastServiced, status, maintenance, rentalLocationid, vehicleTypeid) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String updateVehicleSql = "update person  set  make = ?, model = ?, year = ?, mileage = ?, tag = ?, lastServiced = ?, status = ?, maintenance = ?, rentalLocationid = ?, vehicleTypeid = ? where id = ?";
		java.sql.PreparedStatement stmt = null;
		int inscnt;
		long vehicleId;
		
		if(vehicle.getRentalLocation() == null)
			throw new RARException ("Vehicle.save: Attempting ot save a Vehicle with no RentalLocation defined");
		if(vehicle.getVehicleType() == null)
			throw new RARException ("Vehicle.save: Attempting ot save a Vehicle with no VehicleType defined");
		if(!vehicle.getRentalLocation().isPersistent())	
			throw new RARException ("Vehicle.save: Attempting ot save a vehcile Where RentalLocation is not persistent");
		if(!vehicle.getVehicleType().isPersistent())	
			throw new RARException ("Vehicle.save: Attempting ot save a Vehicle Where VehicleType is not persistent");
			
		try {
	
			if(!vehicle.isPersistent())
				stmt = (java.sql.PreparedStatement) conn.prepareStatement(insertVehicleSql);
			else
				stmt = (java.sql.PreparedStatement) conn.prepareStatement(updateVehicleSql);
		
			if(vehicle.getMake() != null)
				stmt.setString(1,vehicle.getMake());
			else
				throw new RARException("VehicleManager.save: can't save a Vehicle: Make undefined");

			if(vehicle.getModel() != null)
				stmt.setString(2,vehicle.getModel());
			else
				throw new RARException("VehicleManager.save: can't save a Vehicle: Model undefined");

			if(vehicle.getYear() > 0)
				stmt.setInt(3,vehicle.getYear());
			else
				throw new RARException("VehicleManager.save: can't save a Vehcile: Year undefined");
			
			if(vehicle.getMileage() > 0)
				stmt.setInt(4,vehicle.getMileage());
			else
				throw new RARException("VehicleManager.save: can't save a Vehcile: Mileage undefined");
			
			if(vehicle.getRegistrationTag() != null)
				stmt.setString(5,vehicle.getRegistrationTag());
			else
				throw new RARException("VehicleManager.save: can't save a Vehicle: Tag undefined");
			
			if(vehicle.getLastServiced() != null)
				stmt.setDate(6,new java.sql.Date(vehicle.getLastServiced().getTime()));
			else
				stmt.setNull(6,  java.sql.Types.DATE);
			
			if(vehicle.getStatus() != null)
				stmt.setString(7,vehicle.getStatus().toString());
			else
				throw new RARException("VehicleManager.save: can't save a Vehicle: Status undefined");
			
			if(vehicle.getCondition() != null)
				stmt.setString(8,vehicle.getCondition().toString());
			else
				throw new RARException("VehicleManager.save: can't save a Vehicle: Condititon undefined");
			
			stmt.setLong(9,  vehicle.getRentalLocation().getId());
			stmt.setLong(10,  vehicle.getVehicleType().getId());
			
			if(vehicle.isPersistent())
				stmt.setLong(11, vehicle.getId());
		
			inscnt = stmt.executeUpdate();
			
			if(!vehicle.isPersistent()) {
				if(inscnt == 1) {
					String sql = "select last_insert_id()";
					if(stmt.execute(sql)) {
						//retrieve the result
						ResultSet r =stmt.getResultSet();
						while(r.next()) {
							vehicleId = r.getLong(1);
							if(vehicleId > 0)
								vehicle.setId(vehicleId);
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
	
	public List<Vehicle> restore(Vehicle vehicle) throws RARException{
		{
			String       selectVehicleSql = "select v.id, make, model, year, mileage, tag, " +
					"lastServiced, status, maintenance, rentalLocationid, vehicleTypeid," +
					"vt.name, rl.name, rl.address, rl.capacity " +
					"from vehicle v, vehicleType vt, rentalLocation rl " +
					"where rentalLocationid = rl.id and  vehicleTypeid = vt.id";
			Statement    stmt = null;
			StringBuffer query = new StringBuffer( 100 );
			StringBuffer condition = new StringBuffer( 100 );
			List<Vehicle> vehicles= new ArrayList<>();

			condition.setLength( 0 );

			// form the query based on the given Person object instance
			query.append( selectVehicleSql );
			if(vehicle != null){
				if(vehicle.getId() >= 0)		//vehicle id is unique
					query.append(" and v.id = " + vehicle.getId());
				else if (vehicle.getRegistrationTag() != null) // vehicle username is unique
					query.append(" and tag = '" + vehicle.getRegistrationTag() + "'");
				else {

					if( vehicle.getMake() != null )
						condition.append( " and make = '" + vehicle.getMake() + "'" );

					if( vehicle.getModel() != null ) {

						condition.append( " and model = '" + vehicle.getModel() + "'" );
					}

					if( vehicle.getYear() != 0 ) {

						condition.append( " and year = '" + vehicle.getYear() + "'" );
					}

					if( vehicle.getMileage() != 0 ) {

						condition.append( " and mileage = '" + vehicle.getMileage() + "'" );
					}

					if( vehicle.getLastServiced() != null ) {

						condition.append( " and lastServiced = '" + vehicle.getLastServiced() + "'" );
					}

					if( vehicle.getStatus() != null ) {

						condition.append( " and status = '" + vehicle.getStatus() + "'" );
					}
					if( vehicle.getCondition() != null ) {

						condition.append( " and maintenance = '" + vehicle.getCondition() + "'" );
					}
					if(vehicle.getRentalLocation() != null && vehicle.getRentalLocation().getId() > 0 ) {

						condition.append( " and rentalLocationid = '" + vehicle.getRentalLocation().getId() + "'" );
					}
					if( vehicle.getVehicleType() != null && vehicle.getVehicleType().getId() > 0 ) {

						condition.append( " and vehicleTypeid = '" + vehicle.getVehicleType().getId() + "'" );
					}

					query.append( condition );

				}
			}

			try {

				stmt = conn.createStatement();

				// retrieve the persistent Administrator objects
				//
				if( stmt.execute( query.toString() ) ) { // statement returned a result
					ResultSet rs = stmt.getResultSet();

					long id;
					String			 make;
					String			 model;
					String 			 registrationTag;
					int				 year;
					int				 mileage;
					Date			 lastServiced;
					VehicleStatus 	 status;
					VehicleCondition vehicleCondition;
					int rentalLocationid;
					int vehicleTypeid;
					String 			 vtname;
					String 			 rlname;
					String 			 rladdress;
					int capacity;

					VehicleType		 vehicleType;
					RentalLocation   rentalLocation;

					while( rs.next() ) {
/**
 *  columnIndex need to match column index in database
 */
						id = rs.getLong( 1 );
						make = rs.getString( 2 );
						model = rs.getString( 3 );
						year = rs.getInt( 4 );
						mileage = rs.getInt( 5 );
						registrationTag = rs.getString( 6 );
						lastServiced = rs.getDate( 7 );
						status = VehicleStatus.valueOf(rs.getString( 8 ));
						vehicleCondition = VehicleCondition.valueOf(rs.getString( 9 ));
						rentalLocationid = rs.getInt(10);
						vehicleTypeid = rs.getInt(11);
						vtname = rs.getString( 12 );
						rlname = rs.getString( 13 );
						rladdress = rs.getString( 14 );
						capacity = rs.getInt( 15 );

						vehicleType = objectLayer.createVehicleType(vtname);
						vehicleType.setId(vehicleTypeid);
						rentalLocation = objectLayer.createRentalLocation(rlname, rladdress, capacity);
						rentalLocation.setId(rentalLocationid);
						Vehicle vehicle1 = objectLayer.createVehicle(make, model, year, registrationTag, mileage, lastServiced, vehicleType, rentalLocation, vehicleCondition, status);
						vehicle1.setId( id );

						vehicles.add( vehicle1 );

					}

					return vehicles;
				}
			}
			catch( Exception e ) {      // just in case...
				throw new RARException( "VehicleManager.restore: Could not restore persistent Vehicle object; Root cause: " + e );
			}

			// if we get to this point, it's an error
			throw new RARException( "VehicleManager.restore: Could not restore persistent Vehicle objects" );
		}
	}//restore
	
	public void delete(Vehicle vehicle) throws RARException{
		
		String deleteVehicleSql = "delete from vehicle where id = ?";              
		PreparedStatement stmt = null;
		int inscnt = 0;
		             
		        if( !vehicle.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
		            return;
		        
		        try {
		            stmt = (PreparedStatement) conn.prepareStatement( deleteVehicleSql );         
		            stmt.setLong( 1, vehicle.getId() );
		            inscnt = stmt.executeUpdate();          
		            if( inscnt == 1 ) {
		                return;
		            }
		            else
		                throw new RARException( "VehicleManager.delete: failed to delete a vehicle" );
		        }
		        catch( SQLException e ) {
		            e.printStackTrace();
		            throw new RARException( "VehicleManager.delete: failed to delete a vehicle: " + e );       
		            }
		
		
		
	}//delete
	
}//VehicleManager