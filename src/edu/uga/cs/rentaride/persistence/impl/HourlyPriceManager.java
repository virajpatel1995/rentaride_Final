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

public class HourlyPriceManager {
	
	//variables for objectlayer and connection
	private ObjectLayer objectLayer = null;
	private Connection conn = null;
	
	public HourlyPriceManager(Connection conn, ObjectLayer objectLayer) {
		this.conn = conn;
		this.objectLayer = objectLayer;
	}//constructor
	
	public void store(HourlyPrice hourlyPrice) throws RARException{
		String insertHourlyPriceSql = "insert into hourlyPrice ( maxHrs, price, vehicleTypeid ) values ( ?, ?, ? )";
		String updateHourlyPriceSql = "update person  set maxHrs = ?, price = ?, vehicleTypeid = ? where id = ?";
		java.sql.PreparedStatement stmt = null;
		int inscnt;
		long hourlyPriceId;
		
		if(hourlyPrice.getVehicleType() == null)
			throw new RARException ("HourlyPriceManager.save: Attempting ot save a HourlyPrice with no VehicleType defined");
		if(!hourlyPrice.getVehicleType().isPersistent())	
			throw new RARException ("HourlyPriceManager.save: Attempting ot save a HourlyPrice with no VehicleType is not persistent");
		
		try {
	
			if(!hourlyPrice.isPersistent())
				stmt = (java.sql.PreparedStatement) conn.prepareStatement(insertHourlyPriceSql);
			else
				stmt = (java.sql.PreparedStatement) conn.prepareStatement(updateHourlyPriceSql);
		
			if(hourlyPrice.getMaxHours() > 0)
					stmt.setInt(1,hourlyPrice.getMaxHours());
			else
					throw new RARException("HourlyPriceManager.save: can't save a Hourly Price: max hours undefined");

			if(hourlyPrice.getPrice() > 0)
					stmt.setInt(2,hourlyPrice.getPrice());
			else
					throw new RARException("HourlyPriceManager.save: can't save a HourlyPrice: Price undefined");

			stmt.setLong(3,  hourlyPrice.getVehicleType().getId());
			
			if(hourlyPrice.isPersistent())
				stmt.setLong(3,  hourlyPrice.getId());
		
			inscnt = stmt.executeUpdate();
			
			if(!hourlyPrice.isPersistent()) {
				if(inscnt == 1) {
					String sql = "select last_insert_id()";
					if(stmt.execute(sql)) {
						//retrieve the result
						ResultSet r =stmt.getResultSet();
						while(r.next()) {
							hourlyPriceId = r.getLong(1);
							if(hourlyPriceId > 0)
								hourlyPrice.setId(hourlyPriceId);
						}//while
					}//if
				}//if
			}else {
				if(inscnt < 1)
					throw new RARException("HourlyPriceManager.save: failed to save a hourly price");
			}//if else
			
		}catch (SQLException e) {

			e.printStackTrace();
				throw new RARException("HourlyPriceManager.save: Failed to save a Hourly Price: " + e);
		}//try catch
	}//store
	
	public List<HourlyPrice> restore(HourlyPrice hourlyPrice) throws RARException{
		{
			String       selectPriceSql = "select vt.name, hp.id, hp.maxHrs, hp.price, hp.vehicleTypeid " +
					"from hourlyPrice hp, vehicleType vt " +
					"where hp.vehicleTypeid = vt.id";
			Statement    stmt = null;
			StringBuffer query = new StringBuffer( 100 );
			StringBuffer condition = new StringBuffer( 100 );
			List<HourlyPrice> hourlyPrices = new ArrayList<>();

			condition.setLength( 0 );

			// form the query based on the given Person object instance
			query.append( selectPriceSql );
			if(hourlyPrice != null){
				if(hourlyPrice.getId() >= 0)
					query.append(" and hp.id = " + hourlyPrice.getId());
				else {
					if( hourlyPrice.getMaxHours() > 0 )
						condition.append( " and hp.maxHrs = '" + hourlyPrice.getMaxHours() + "'" );

					if( hourlyPrice.getPrice() > 0 ) {
						condition.append( " and hp.price = '" + hourlyPrice.getPrice() + "'" );
					}

					if( hourlyPrice.getVehicleType() != null ) {
						condition.append( " and hp.vehicleTypeid = " + hourlyPrice.getVehicleType().getId() );
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
					int maxHrs;
					int price;
					String vehicleTypeName;

					while( rs.next() ) {
/**
 *  columnIndex need to match column index in database
 */
						vehicleTypeName = rs.getString(1);
						id = rs.getLong( 2);
						maxHrs = rs.getInt( 3 );
						price = rs.getInt( 4);

						VehicleType vehicleType = objectLayer.createVehicleType(vehicleTypeName);

						HourlyPrice hourlyPrice1 = objectLayer.createHourlyPrice(maxHrs, price, vehicleType );
						hourlyPrice1.setId( id );

						hourlyPrices.add( hourlyPrice1 );

					}

					return hourlyPrices;
				}
			}
			catch( Exception e ) {      // just in case...
				throw new RARException( "HourlyPriceManager.restore: Could not restore persistent Administrator object; Root cause: " + e );
			}

			// if we get to this point, it's an error
			throw new RARException( "HourlyPriceManager.restore: Could not restore persistent Administrator objects" );
		}
	}//restore
	
	public void delete(HourlyPrice HourlyPrice) throws RARException{
		
		String deleteHourlyPriceSql = "delete from hourlyPrice where id = ?";              
		PreparedStatement stmt = null;
		int inscnt = 0;
		             
		        if( !HourlyPrice.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
		            return;
		        
		        try {
		            stmt = (PreparedStatement) conn.prepareStatement( deleteHourlyPriceSql );         
		            stmt.setLong( 1, HourlyPrice.getId() );
		            inscnt = stmt.executeUpdate();          
		            if( inscnt == 1 ) {
		                return;
		            }
		            else
		                throw new RARException( "HourlyPricerManager.delete: failed to delete a HourlyPrice" );
		        }
		        catch( SQLException e ) {
		            e.printStackTrace();
		            throw new RARException( "HourlyPriceManager.delete: failed to delete a HourlyPrice: " + e );       
		            }
		
		
		
	}//delete
	
}//HourlyPriceManager