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

public class CustomerManager {
	
	//variables for objectlayer and connection
	private ObjectLayer objectLayer = null;
	private Connection conn = null;
	
	public CustomerManager(Connection conn, ObjectLayer objectLayer) throws RARException{
		this.conn = conn;
		this.objectLayer = objectLayer;
	}//constructor
	
	public void store(Customer customer) throws RARException{
		String insertCustomerSql = "insert into user ( type, firstName, lastName, userName, password, email, address, createdDate, memberUntil, licState, licNumber, ccNumber, ccExpiration, status ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
		String updateCustomerSql = "update person  set type = ? firstName = ?, lastName = ?, userName = ?, password = ?, email = ?, address = ?, createdDate = ?, memberUntil = ?, licState = ?, licNumber = ?, ccNumber = ?, ccExpiration = ?, status = ? where id = ?";
		java.sql.PreparedStatement stmt = null;
		int inscnt;
		long userId;
		
		try {
	
			if(!customer.isPersistent())
				stmt = (java.sql.PreparedStatement) conn.prepareStatement(insertCustomerSql);
			else
				stmt = (java.sql.PreparedStatement) conn.prepareStatement(updateCustomerSql);
		
			stmt.setString(1,  "Customer");
			
			if(customer.getFirstName() != null)
					stmt.setString(2,customer.getFirstName());
			else
					throw new RARException("CustomerManager.save: can't save a Customer: FirstName undefined");

			if(customer.getLastName() != null)
					stmt.setString(3,customer.getLastName());
			else
					throw new RARException("CustomerManager.save: can't save a Customer: LastName undefined");

			if(customer.getUserName() != null)
					stmt.setString(4,customer.getUserName());
			else
					throw new RARException("CustomerManager.save: can't save a Customer: UserName undefined");

			if(customer.getPassword() != null)
					stmt.setString(5,customer.getPassword());
			else
					throw new RARException("CustomerManager.save: can't save a Customer: Password undefined");

			if(customer.getEmail() != null)
					stmt.setString(6,customer.getEmail());
			else
					throw new RARException("CustomerManager.save: can't save a Customer: Email undefined");

			if(customer.getAddress() != null)
					stmt.setString(7,customer.getAddress());
			else
					throw new RARException("CustomerManager.save: can't save a Customer: Address undefined");

			if(customer.getCreatedDate() != null)
					stmt.setDate(8,new java.sql.Date(customer.getCreatedDate().getTime()));
			else
					throw new RARException("CustomerManager.save: can't save a Customer: Created Date undefined");
			
			if(customer.getMemberUntil() != null)
				stmt.setDate(9,new java.sql.Date(customer.getMemberUntil().getTime()));
			else
				throw new RARException("CustomerManager.save: can't save a Customer: Member Until undefined");
		
			if(customer.getLicenseState() != null)
				stmt.setString(10,customer.getLicenseState());
			else
				throw new RARException("CustomerManager.save: can't save a Customer: License State undefined");
			
			if(customer.getLicenseNumber() != null)
				stmt.setString(11,customer.getLicenseNumber());
			else
				throw new RARException("CustomerManager.save: can't save a Customer: License Number undefined");
			
			if(customer.getCreditCardNumber() != null)
				stmt.setString(12,customer.getCreditCardNumber());
			else
				throw new RARException("CustomerManager.save: can't save a Customer: Credit Card Number undefined");

			if(customer.getCreditCardExpiration() != null)
				stmt.setDate(13,new java.sql.Date(customer.getCreditCardExpiration().getTime()));
			else
				throw new RARException("CustomerManager.save: can't save a Customer: Credit Card Expiration undefined");

			if(customer.getUserStatus() != null)
				stmt.setString(14,customer.getUserStatus().toString());
			else
				throw new RARException("CustomerManager.save: can't save a Customer: User Status undefined");
			
			if(customer.isPersistent())
				stmt.setLong(15,  customer.getId());
		
			inscnt = stmt.executeUpdate();
			
			if(!customer.isPersistent()) {
				if(inscnt == 1) {
					String sql = "select last_insert_id()";
					if(stmt.execute(sql)) {
						//retrieve the result
						ResultSet r =stmt.getResultSet();
						while(r.next()) {
							userId = r.getLong(1);
							if(userId > 0)
								customer.setId(userId);
						}//while
					}//if
				}//if
			}else {
				if(inscnt < 1)
					throw new RARException("CustomerManager.save: failed to save a Customer");
			}//if else
			
		}catch (SQLException e) {

			e.printStackTrace();
				throw new RARException("CustomerManager.save: Failed to save a Customer: " + e);
		}//try catch
	
	}//store
	
	public List<Customer> restore(Customer customer) throws RARException{
		{
			String       selectCustomerSql = "select id, type, firstName, lastName, userName, " +
					"password, email, address, createdDate, memberUntil, licState, licNumber," +
					" ccNumber, ccExpiration, status " +
					"from user " +
					"where type = 'Customer' ";
			Statement    stmt = null;
			StringBuffer query = new StringBuffer( 100 );
			StringBuffer condition = new StringBuffer( 100 );
			List<Customer> customers= new ArrayList<>();

			condition.setLength( 0 );

			// form the query based on the given Person object instance
			query.append( selectCustomerSql );
			if(customer != null){
				if(customer.getId() >= 0)		//customer id is unique
					query.append(" and id = " + customer.getId());
				else if (customer.getUserName() != null) // customer username is unique
					query.append(" and username = '" + customer.getUserName() + "'");
				else {
					if( customer.getPassword() != null )
						condition.append( " and password = '" + customer.getPassword() + "'" );

					if( customer.getEmail() != null ) {
						condition.append( " and email = '" + customer.getEmail() + "'" );
					}

					if( customer.getFirstName() != null ) {
						condition.append( " and firstName = '" + customer.getFirstName() + "'" );
					}

					if( customer.getLastName() != null ) {
						condition.append( " and lastName = '" + customer.getLastName() + "'" );
					}

					if( customer.getAddress() != null ) {
						condition.append( " and address = '" + customer.getAddress() + "'" );
					}

					if( customer.getCreatedDate() != null ) {
						condition.append( " and createdDate = '" + customer.getCreatedDate() + "'" );
					}
					if( customer.getMemberUntil() != null ) {
						condition.append( " and memberUntil = '" + customer.getMemberUntil() + "'" );
					}
					if( customer.getLicenseState() != null ) {
						condition.append( " and licState = '" + customer.getLicenseState() + "'" );
					}
					if( customer.getCreditCardNumber() != null ) {
						condition.append( " and ccNumber = '" + customer.getCreditCardNumber() + "'" );
					}
					if( customer.getLicenseNumber() != null ) {
						condition.append( " and licNumber = '" + customer.getLicenseNumber() + "'" );
					}
					if( customer.getCreditCardExpiration() != null ) {
						condition.append( " and ccExpiration = '" + customer.getCreditCardExpiration() + "'" );
					}
					if( customer.getUserStatus() != null ) {
						condition.append( " and status = '" + customer.getUserStatus() + "'" );
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
					String firstName;
					String lastName;
					String userName;
					String password;
					String email;
					String address;
					String state;
					String licenseNumber;
					String cardNumber;
					Date createdDate;
					Date memberUntil;
					Date cardExpiration;
					UserStatus userStatus;
					while( rs.next() ) {
/**
 *  columnIndex need to match column index in database
 */
						id = rs.getLong( 1 );
						firstName = rs.getString( 3 );
						lastName = rs.getString( 4 );
						userName = rs.getString( 5 );
						password = rs.getString( 6 );
						email = rs.getString( 7 );
						address = rs.getString( 8 );
						createdDate = rs.getDate( 9 );
						memberUntil = rs.getDate( 10 );
						state = rs.getString( 11 );
						licenseNumber = rs.getString( 12 );
						cardNumber = rs.getString( 13 );
						cardExpiration = rs.getDate( 14 );
						userStatus = UserStatus.valueOf(rs.getString(15));

						Customer customer1 = objectLayer.createCustomer( firstName, lastName, userName,password, email, address, createdDate, memberUntil, state, licenseNumber, cardNumber, cardExpiration, userStatus);
						customer1.setId( id );

						customers.add( customer1 );

					}

					return customers;
				}
			}
			catch( Exception e ) {      // just in case...
				throw new RARException( "AdministratorManager.restore: Could not restore persistent Administrator object; Root cause: " + e );
			}

			// if we get to this point, it's an error
			throw new RARException( "AdministratorManager.restore: Could not restore persistent Administrator objects" );
		}
	}//restore
	
	
	public void delete(Customer customer) throws RARException{
		String deleteCustomerSql = "delete from user where id = ?";              
		PreparedStatement stmt = null;
		int inscnt;
		             
		        if( !customer.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
		            return;
		        
		        try {
		            stmt = (PreparedStatement) conn.prepareStatement( deleteCustomerSql );         
		            stmt.setLong( 1, customer.getId() );
		            inscnt = stmt.executeUpdate();          
		            if( inscnt == 1 ) {
		                return;
		            }
		            else
		                throw new RARException( "CustomerManager.delete: failed to delete a Customer" );
		        }
		        catch( SQLException e ) {
		            e.printStackTrace();
		            throw new RARException( "CustomerManager.delete: failed to delete a Customer: " + e );       
		            }
	}//delete
	
}//CustomerManager