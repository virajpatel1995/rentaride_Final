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

public class RentARideParamsManager {
	
	//variables for objectlayer and connection
	private ObjectLayer objectLayer = null;
	private Connection conn = null;
	
	public RentARideParamsManager(Connection conn, ObjectLayer objectLayer) {
		this.conn = conn;
		this.objectLayer = objectLayer;
	}//constructor
	
	public void store(RentARideParams rentARideParams) throws RARException{
		String insertParamsTypeSql = "insert into rentARideParams ( membershipPrice, lateFee) values ( ? , ?)";
		String updateParamsTypeSql = "update rentARideParams  set membershipPrice = ? , lateFee = ? where id = ?";
		PreparedStatement stmt = null;
		int inscnt;
		long paramId;

		try {

			if(!rentARideParams.isPersistent())
				stmt = (PreparedStatement) conn.prepareStatement(insertParamsTypeSql);
			else
				stmt = (PreparedStatement) conn.prepareStatement(updateParamsTypeSql);

			if(rentARideParams.getMembershipPrice() > 0)
				stmt.setInt(1,rentARideParams.getMembershipPrice());
			else
				stmt.setNull(1, java.sql.Types.INTEGER);
			if(rentARideParams.getLateFee() > 0)
				stmt.setInt(2,rentARideParams.getLateFee());
			else
				stmt.setNull(2, java.sql.Types.INTEGER);

			if(rentARideParams.isPersistent())
				stmt.setLong(3,  rentARideParams.getId());

			inscnt = stmt.executeUpdate();

			if(!rentARideParams.isPersistent()) {
				if(inscnt == 1) {
					String sql = "select last_insert_id()";
					if(stmt.execute(sql)) {
						//retrieve the result
						ResultSet r =stmt.getResultSet();
						while(r.next()) {
							paramId = r.getLong(1);
							if(paramId > 0)
								rentARideParams.setId(paramId);
						}//while
					}//if
				}//if
			}else {
				if(inscnt < 1)
					throw new RARException("RentARideParamsManager.store: failed to save a RentARideParams");
			}//if else

		}catch (SQLException e) {

			e.printStackTrace();
			throw new RARException("RentARideParamsManager.store: Failed to save a RentARideParams type: " + e);
		}//try catch
	}//store
	
	public RentARideParams restore() throws RARException{
		{
			{
				String selectParamsSql =
						"select * from rentARideParams ";
				Statement    stmt = null;
				StringBuffer query = new StringBuffer( 100 );
				RentARideParams rentARideParams = null;


				// form the query based on the given Person object instance
				query.append( selectParamsSql );


				try {

					stmt = conn.createStatement();

					// retrieve the persistent Administrator objects
					//
					if( stmt.execute( query.toString() ) ) { // statement returned a result
						ResultSet rs = stmt.getResultSet();

						long id;
						int membershipPrice;
						int lateFee;

						if( rs.next() ) {
/**
 *  columnIndex need to match column index in database
 */
							id = rs.getLong( 1);
							membershipPrice = rs.getInt(2);
							lateFee = rs.getInt(3);
							rentARideParams = objectLayer.createRentARideParams();
							rentARideParams.setId(id);
							rentARideParams.setLateFee(lateFee);
							rentARideParams.setMembershipPrice(membershipPrice);
						}

						return rentARideParams;
					}
				}
				catch( Exception e ) {      // just in case...
					throw new RARException( "RentARideManager.restore: Could not restore persistent RentARide object; Root cause: " + e );
				}

				// if we get to this point, it's an error
				throw new RARException( "RentARideManager.restore: Could not restore persistent RentARide objects" );
			}
		}
	}//restore
	
//	public void delete(RentARideParams rentARideParams) throws RARException{
//
//		String deleteRentARideParamsSql = "delete from rentARideParams where id = ?";
//		PreparedStatement stmt = null;
//		int inscnt = 0;
//
//		        if( !rentARideParams.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
//		            return;
//
//		        try {
//		            stmt = (PreparedStatement) conn.prepareStatement( deleteRentARideParamsSql );
//		            stmt.setLong( 1, rentARideParams.getId() );
//		            inscnt = stmt.executeUpdate();
//		            if( inscnt == 1 ) {
//		                return;
//		            }
//		            else
//		                throw new RARException( "RentARideParamsManager.delete: failed to delete a rentARideParams" );
//		        }
//		        catch( SQLException e ) {
//		            e.printStackTrace();
//		            throw new RARException( "RentARideParamsManager.delete: failed to delete a rentARideParams: " + e );
//		            }
//
//
//
//
//	}//delete
	
}//RentARideParamsManager