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
import edu.uga.cs.rentaride.entity.impl.RentalImp;
import edu.uga.cs.rentaride.object.ObjectLayer;

public class CommentManager {
	
	//variables for objectlayer and connection
	private ObjectLayer objectLayer = null;
	private Connection conn = null;
	
	public CommentManager(Connection conn, ObjectLayer objectLayer) throws RARException{
		this.conn = conn;
		this.objectLayer = objectLayer;
	}//constructor
	
	public void store(Comment comment) throws RARException{
		String insertCommentSql = "insert into comment ( comment, commentDate, rentalid ) values ( ?, ?, ? )";
		String updateCommentSql = "update person  set comment = ?, commentDate = ?, rentalid = ? where id = ?";
		java.sql.PreparedStatement stmt = null;
		int inscnt;
		long commentId;
		
		if(comment.getRental() == null)
			throw new RARException ("CommentManager.save: Attempting ot save a Comment with no Rental defined");
		if(!comment.getRental().isPersistent())	
			throw new RARException ("CommentManager.save: Attempting ot save a Comment with no Rental is not persistent");
		
		try {
	
			if(!comment.isPersistent())
				stmt = (java.sql.PreparedStatement) conn.prepareStatement(insertCommentSql);
			else
				stmt = (java.sql.PreparedStatement) conn.prepareStatement(updateCommentSql);
		
			if(comment.getText() != null)
				stmt.setString(1,comment.getText());
			else
				throw new RARException("CommentManager.save: can't save an Comment: Text undefined");

			if(comment.getDate() != null)
				stmt.setDate(2,new java.sql.Date(comment.getDate().getTime()));
			else
				stmt.setNull(2,  java.sql.Types.DATE);
			
			stmt.setLong(3,  comment.getRental().getId());
			
			if(comment.isPersistent())
				stmt.setLong(4,  comment.getId());
		
			inscnt = stmt.executeUpdate();
			
			if(!comment.isPersistent()) {
				if(inscnt == 1) {
					String sql = "select last_insert_id()";
					if(stmt.execute(sql)) {
						//retrieve the result
						ResultSet r =stmt.getResultSet();
						while(r.next()) {
							commentId = r.getLong(1);
							if(commentId > 0)
								comment.setId(commentId);
						}//while
					}//if
				}//if
			}else {
				if(inscnt < 1)
					throw new RARException("CommentManager.save: failed to save a comment");
			}//if else
			
		}catch (SQLException e) {

			e.printStackTrace();
				throw new RARException("CommentManager.save: Failed to save a comment: " + e);
		}//try catch
	}//store
	
	public List<Comment> restore(Comment comment) throws RARException{
		{
			String       selectCommentSql = "select id, comment, commentdate, rentalid from comment";
			Statement    stmt = null;
			StringBuffer query = new StringBuffer( 100 );
			StringBuffer condition = new StringBuffer( 100 );
			List<Comment> comments = new ArrayList<>();

			condition.setLength( 0 );

			query.append( selectCommentSql );
			if(comment != null){
				if(comment.getId() >= 0)
					query.append(" where id = " + comment.getId());
				else {
					if( comment.getText() != null )
						condition.append( " comment = '" + comment.getText() + "'" );

					if( comment.getDate() != null ) {
						if( condition.length() > 0 )
							condition.append( " and" );
						condition.append( " commentdate = '" + comment.getDate() + "'" );
					}

					if( comment.getRental() != null) {
						if( condition.length() > 0 )
							condition.append( " and" );
						condition.append( " rentalid = '" + comment.getRental().getId() + "'" );
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
					String text;
					Date date;
					long rentalid;

					while( rs.next() ) {
/**
 *  columnIndex need to match column index in database
 */
						id = rs.getLong( 1 );
						text = rs.getString( 2 );
						date = rs.getDate( 3 );
						rentalid = rs.getLong(4);

						Rental rental = new RentalImp();
						rental.setId(rentalid);// rental model object based on rentalId from comment table

						// find the rental object given only id
						RentalManager rentalManager = new RentalManager(conn, objectLayer);
						rental = rentalManager.restore(rental).get(0);
						Comment comment1 = objectLayer.createComment(text, date, rental, rental.getCustomer());
						comment1.setId(id);

						comments.add( comment1 );

					}

					return comments;
				}
			}
			catch( Exception e ) {      // just in case...
				throw new RARException( "AdministratorManager.restore: Could not restore persistent Administrator object; Root cause: " + e );
			}

			// if we get to this point, it's an error
			throw new RARException( "AdministratorManager.restore: Could not restore persistent Administrator objects" );
		}}//restore
	
	public void delete(Comment comment) throws RARException{
		
		String deleteCommentSql = "delete from comment where id = ?";              
		PreparedStatement stmt = null;
		int inscnt = 0;
		             
		        if( !comment.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
		            return;
		        
		        try {
		            stmt = (PreparedStatement) conn.prepareStatement( deleteCommentSql );         
		            stmt.setLong( 1, comment.getId() );
		            inscnt = stmt.executeUpdate();          
		            if( inscnt == 1 ) {
		                return;
		            }
		            else
		                throw new RARException( "CommentManager.delete: failed to delete a Comment" );
		        }
		        catch( SQLException e ) {
		            e.printStackTrace();
		            throw new RARException( "CommentManager.delete: failed to delete a Comment: " + e );       
		            }
		
		
		
		
		
		
	}//delete
	
}//CommentManager