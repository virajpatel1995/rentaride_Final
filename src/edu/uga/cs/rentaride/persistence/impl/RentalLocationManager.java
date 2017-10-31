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
import edu.uga.cs.rentaride.entity.impl.CustomerImpl;
import edu.uga.cs.rentaride.entity.impl.RentalLocationImpl;
import edu.uga.cs.rentaride.entity.impl.VehicleTypeImpl;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.persistence.PersistenceLayer;

public class RentalLocationManager {
	
	//variables for objectlayer and connection
	private ObjectLayer objectLayer = null;
	private Connection conn = null;
	
	public RentalLocationManager(Connection conn, ObjectLayer objectLayer) {
		this.conn = conn;
		this.objectLayer = objectLayer;
	}//constructor
	
	public void store(RentalLocation rentalLocation) throws RARException{
		String insertRentalLocationSql = "insert into rentalLocation ( name, address, capacity) values ( ?, ?, ?)";
		String updateRentalLocationSql = "update person  set name = ?, address = ?, capacity = ?, where id = ?";
		java.sql.PreparedStatement stmt = null;
		int inscnt;
		long rentalLocationId;
		
		try {
	
			if(!rentalLocation.isPersistent())
				stmt = (java.sql.PreparedStatement) conn.prepareStatement(insertRentalLocationSql);
			else
				stmt = (java.sql.PreparedStatement) conn.prepareStatement(updateRentalLocationSql);
			
			if(rentalLocation.getName() != null)
					stmt.setString(1,rentalLocation.getName());
			else
					throw new RARException("RentalLocationManager.save: can't save a Rental Location: Name undefined");

			if(rentalLocation.getAddress() != null)
					stmt.setString(2,rentalLocation.getAddress());
			else
					throw new RARException("RentalLocationManager.save: can't save an Rental Location: Address undefined");

			if(rentalLocation.getCapacity() > 0)
					stmt.setInt(3,rentalLocation.getCapacity());
			else
					throw new RARException("RentalLocationManager.save: can't save a RentalLocation: Capacity undefined");

			if(rentalLocation.isPersistent())
				stmt.setLong(4,  rentalLocation.getId());
		
			inscnt = stmt.executeUpdate();
			
			if(!rentalLocation.isPersistent()) {
				if(inscnt == 1) {
					String sql = "select last_insert_id()";
					if(stmt.execute(sql)) {
						//retrieve the result
						ResultSet r =stmt.getResultSet();
						while(r.next()) {
							rentalLocationId = r.getLong(1);
							if(rentalLocationId > 0)
								rentalLocation.setId(rentalLocationId);
						}//while
					}//if
				}//if
			}else {
				if(inscnt < 1)
					throw new RARException("RentalLocationManager.save: failed to save a Rental Location");
			}//if else
			
		}catch (SQLException e) {

			e.printStackTrace();
				throw new RARException("RentalLocationManager.save: Failed to save a Rental Location: " + e);
		}//try catch
	
	}//store
	
	public List<RentalLocation> restore(RentalLocation rentalLocation) throws RARException{
		{
			String       selectRentalLocationSql =
					"select id, name, address, capacity " +
					"from rentalLocation ";
			Statement    stmt = null;
			StringBuffer query = new StringBuffer( 100 );
			StringBuffer condition = new StringBuffer( 100 );
			List<RentalLocation> rentalLocations = new ArrayList<>();

			condition.setLength( 0 );

			// form the query based on the given Person object instance
			query.append( selectRentalLocationSql );
			if(rentalLocation != null){
				if(rentalLocation.getId() > 0)
					query.append(" where id = " + rentalLocation.getId());
				else if(rentalLocation.getName() != null)
					query.append(" where name = '" + rentalLocation.getName() + "'");
				else{
					if( rentalLocation.getAddress() != null ) {
						if (condition.length() > 0)
							condition.append(" and");
						condition.append(" address = '" + rentalLocation.getAddress() + "'");
					}
					if( rentalLocation.getCapacity() > 0 ) {
						if( condition.length() > 0 )
							condition.append( " and" );
						condition.append( " capacity = '" + rentalLocation.getCapacity() + "'" );
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
					String name;
					String address;
					int capacity;

					while( rs.next() ) {
/**
 *  columnIndex need to match column index in database
 */
						id = rs.getLong( 1);
						name = rs.getString(2);
						address = rs.getString(3);
						capacity = rs.getInt( 4);

						RentalLocation rentalLocation1 = objectLayer.createRentalLocation(name, address, capacity);
						rentalLocation1.setId( id );

						rentalLocations.add( rentalLocation1 );

					}

					return rentalLocations;
				}
			}
			catch( Exception e ) {      // just in case...
				throw new RARException( "RentalLocationManager.restore: Could not restore persistent RentalLocation object; Root cause: " + e );
			}

			// if we get to this point, it's an error
			throw new RARException( "RentalLocationManager.restore: Could not restore persistent RentalLocation objects" );
		}
	}//restore
	
	public void delete(RentalLocation rentalLocation) throws RARException{
		
		String deleteRentalLocationSql = "delete from rentalLocation where id = ?";              
		PreparedStatement stmt = null;
		int inscnt = 0;
		             
		        if( !rentalLocation.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
		            return;
		        
		        try {
		            stmt = (PreparedStatement) conn.prepareStatement( deleteRentalLocationSql );         
		            stmt.setLong( 1, rentalLocation.getId() );
		            inscnt = stmt.executeUpdate();          
		            if( inscnt == 1 ) {
		                return;
		            }
		            else
		                throw new RARException( "RentalLocationManager.delete: failed to delete a RentalLocation" );
		        }
		        catch( SQLException e ) {
		            e.printStackTrace();
		            throw new RARException( "RentalLocationManager.delete: failed to delete a RentalLocation: " + e );       
		            }
		
		
		
		
	}//delete

	public List<Reservation> restoreReservations(RentalLocation rentalLocation) throws RARException {
		{
			String       selectReservationSql = "select r.id, r.pickup, r.length, r.canceled, " +
					"r.userid, r.rentalLocationid, r.vehicleTypeid, " +
					"rl.id, rl.name, rl.address, rl.capacity " +
					"from rentalLocation rl, reservation r " +
					"where r.rentalLocationid = rl.id";
			Statement    stmt = null;
			StringBuffer query = new StringBuffer( 100 );
			StringBuffer condition = new StringBuffer( 100 );
			List<Reservation> reservations = new ArrayList<>();

			condition.setLength( 0 );

			// form the query based on the given Person object instance
			query.append( selectReservationSql );
			if(rentalLocation != null){
				if(rentalLocation.getId() >= 0)		//vehicle id is unique
					query.append(" and rl.id = " + rentalLocation.getId());
				else if(rentalLocation.getName() != null) {
					query.append(" and rl.name = " + rentalLocation.getName());
				}else{
					if( rentalLocation.getAddress() != null )
					condition.append( " and rl.address = '" + rentalLocation.getAddress() + "'" );

					if( rentalLocation.getCapacity() > 0 ) {
						condition.append( " and rl.capacity = '" + rentalLocation.getCapacity() + "'" );
					}

//					if( condition.length() > 0 ) {
//						query.append(  " where " );
//					}
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
				throw new RARException( "RentalLocationManager.restore: Could not restore persistent Reservation object; Root cause: " + e );
			}

			// if we get to this point, it's an error
			throw new RARException( "RentalLocationManager.restore: Could not restore persistent Reservation objects" );
		}
	}

	public List<Vehicle> restoreVehicles(RentalLocation rentalLocation) throws RARException {
		{
			{
				String       selectVehicleSql = "select v.id, make, model, year, mileage, tag, " +
						"lastServiced, status, maintenance, rentalLocationid, vehicleTypeid," +
						"rl.id, rl.name, rl.address, rl.capacity " +
						"from rentalLocation rl, vehicle v " +
						"where rentalLocationid = rl.id";
				Statement    stmt = null;
				StringBuffer query = new StringBuffer( 100 );
				StringBuffer condition = new StringBuffer( 100 );
				List<Vehicle> vehicles = new ArrayList<>();

				condition.setLength( 0 );

				// form the query based on the given Person object instance
				query.append( selectVehicleSql );
				if(rentalLocation != null){
					if(rentalLocation.getId() >= 0)		//vehicle id is unique
						query.append(" and rl.id = " + rentalLocation.getId());
					else if(rentalLocation.getName() != null) {
						query.append(" and rl.name = " + rentalLocation.getName());
					}else{
						if( rentalLocation.getAddress() != null )
							condition.append( " and rl.address = '" + rentalLocation.getAddress() + "'" );

						if( rentalLocation.getCapacity() >= 0 ) {
							condition.append( " and rl.capacity = '" + rentalLocation.getCapacity() + "'" );
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
						int				 year;
						int				 mileage;
						String 			 registrationTag;
						Date			 lastServiced;
						VehicleStatus status;
						VehicleCondition vehicleCondition;
						int rentalLocationid;
						int vehicleTypeid;

						VehicleType		 vehicleType;
						RentalLocation   rentalLocation1;

						Vehicle vehicle;
						while( rs.next() ) {
/**
 *  columnIndex need to match column index in database
 */

							id = rs.getInt(1);
							make = rs.getString(2);
							model = rs.getString(3);
							year = rs.getInt(4);
							mileage = rs.getInt(5);
							registrationTag = rs.getString(6);
							lastServiced = rs.getDate(7);
							status = VehicleStatus.valueOf(rs.getString(8));
							vehicleCondition = VehicleCondition.valueOf(rs.getString(9));
							rentalLocationid = rs.getInt(10);
							vehicleTypeid = rs.getInt(11);

							RentalLocationManager rentalLocationManager = new RentalLocationManager(conn, objectLayer);
							RentalLocation modelRentalLocation = new RentalLocationImpl();
							modelRentalLocation.setId(rentalLocationid);
							modelRentalLocation = rentalLocationManager.restore(modelRentalLocation).get(0);

							VehicleTypeManager vehicleTypeManager = new VehicleTypeManager(conn, objectLayer);
							VehicleType modelVehicleType = new VehicleTypeImpl();
							modelVehicleType.setId(vehicleTypeid);
							modelVehicleType = vehicleTypeManager.restore(modelVehicleType).get(0);


							vehicle = objectLayer.createVehicle(make, model,year, registrationTag, mileage, lastServiced, modelVehicleType, modelRentalLocation, vehicleCondition, status);
							vehicle.setId(id);

							vehicles.add( vehicle);

						}

						return vehicles;
					}
				}
				catch( Exception e ) {      // just in case...
					throw new RARException( "RentalLocationManager.restore: Could not restore persistent Reservation object; Root cause: " + e );
				}

				// if we get to this point, it's an error
				throw new RARException( "RentalLocationManager.restore: Could not restore persistent Reservation objects" );
			}
		}
	}
}//RentalLocationManager