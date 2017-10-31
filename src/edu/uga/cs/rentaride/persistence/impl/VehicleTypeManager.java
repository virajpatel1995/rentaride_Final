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
import edu.uga.cs.rentaride.entity.Administrator;
import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.HourlyPrice;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.RentARideParams;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.object.ObjectLayer;

public class VehicleTypeManager {
	
	//variables for objectlayer and connection
	private ObjectLayer objectLayer = null;
	private Connection conn = null;
	
	public VehicleTypeManager(Connection conn, ObjectLayer objectLayer) {
		this.conn = conn;
		this.objectLayer = objectLayer;
	}//constructor
	
	public void store(VehicleType vehicleType) throws RARException{
		String insertVehicleTypeSql = "insert into vehicleType ( name) values ( ?)";
		String updateVehicleTypeSql = "update person  set name = ? where id = ?";
		java.sql.PreparedStatement stmt = null;
		int inscnt;
		long vehicleTypeId;
		
		try {
	
			if(!vehicleType.isPersistent())
				stmt = (java.sql.PreparedStatement) conn.prepareStatement(insertVehicleTypeSql);
			else
				stmt = (java.sql.PreparedStatement) conn.prepareStatement(updateVehicleTypeSql);
			
			if(vehicleType.getName() != null)
					stmt.setString(1,vehicleType.getName());
			else
					throw new RARException("VehicleTypeManager.save: can't save a VehicleType Location: Name undefined");

			if(vehicleType.isPersistent())
				stmt.setLong(2,  vehicleType.getId());
		
			inscnt = stmt.executeUpdate();
			
			if(!vehicleType.isPersistent()) {
				if(inscnt == 1) {
					String sql = "select last_insert_id()";
					if(stmt.execute(sql)) {
						//retrieve the result
						ResultSet r =stmt.getResultSet();
						while(r.next()) {
							vehicleTypeId = r.getLong(1);
							if(vehicleTypeId > 0)
								vehicleType.setId(vehicleTypeId);
						}//while
					}//if
				}//if
			}else {
				if(inscnt < 1)
					throw new RARException("VehicleTypeManager.save: failed to save a VehicleType");
			}//if else
			
		}catch (SQLException e) {

			e.printStackTrace();
				throw new RARException("VehicleTypeManager.save: Failed to save a Vehicle type: " + e);
		}//try catch
	}//store
	
	public List<VehicleType> restore(VehicleType vehicleType) throws RARException{
		{
			String selectVehicleTypeSql =
					"select id, name from vehicleType ";
			Statement    stmt = null;
			StringBuffer query = new StringBuffer( 100 );
			StringBuffer condition = new StringBuffer( 100 );
			List<VehicleType> vehicleTypes = new ArrayList<>();

			condition.setLength( 0 );

			// form the query based on the given Person object instance
			query.append( selectVehicleTypeSql );
			if(vehicleType != null){
				if(vehicleType.getId() >= 0)
					query.append(" where id = " + vehicleType.getId());
				else if(vehicleType.getName() != null)
					query.append(" where name = '" + vehicleType.getName() + "'");
				else{

//					if( condition.length() > 0 ) {
//						query.append(  " where " );
//						query.append( condition );
//					}
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


					while( rs.next() ) {
/**
 *  columnIndex need to match column index in database
 */
						id = rs.getLong( 1);
						name = rs.getString(2);


						VehicleType vehicleType1 = objectLayer.createVehicleType(name);
						vehicleType1.setId( id );

						vehicleTypes.add( vehicleType1 );

					}

					return vehicleTypes;
				}
			}
			catch( Exception e ) {      // just in case...
				throw new RARException( "VehicleTypeManager.restore: Could not restore persistent VehicleType object; Root cause: " + e );
			}

			// if we get to this point, it's an error
			throw new RARException( "VehicleTypeManager.restore: Could not restore persistent VehicleType objects" );
		}
	}//restore
	
	public void delete(VehicleType vehicleType) throws RARException{
		
		String deleteVehicleTypeSql = "delete from vehicleType where id = ?";              
		PreparedStatement stmt = null;
		int inscnt = 0;
		             
		        if( !vehicleType.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
		            return;
		        
		        try {
		            stmt = (PreparedStatement) conn.prepareStatement( deleteVehicleTypeSql );         
		            stmt.setLong( 1, vehicleType.getId() );
		            inscnt = stmt.executeUpdate();          
		            if( inscnt == 1 ) {
		                return;
		            }
		            else
		                throw new RARException( "VehicleTypeManager.delete: failed to delete a vehicleType" );
		        }
		        catch( SQLException e ) {
		            e.printStackTrace();
		            throw new RARException( "VehicleTypeManager.delete: failed to delete a vehicleType: " + e );       
		            }
		
		
		
	}//delete
	
}//VehicleTypeManager