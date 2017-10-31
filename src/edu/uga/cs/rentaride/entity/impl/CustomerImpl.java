package edu.uga.cs.rentaride.entity.impl;

import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.UserStatus;
import edu.uga.cs.rentaride.persistence.impl.Persistence;

public class CustomerImpl extends Persistence implements Customer {

		// Customer Attributes
		private String            userName;
	    private String            password;
	    private String            email;
	    private String            firstName;
	    private String            lastName;
	    private String            address;
	    private String		  	  state;
	    private String	 		  licenseNumber;
	    private String			  cardNumber;
	    private Date			  createdDate;
	    private Date			  memberUntil;
	    private Date			  cardExpiration;
	    private UserStatus		  userStatus;
	    private List<Reservation> reservations;
	    private List<Comment>	  comments;
	    private List<Rental>      rentals;

	    
	    public CustomerImpl()
	    {
	        super( -1 );
	        this.userName = null;
	        this.password = null;
	        this.email = null;
	        this.firstName = null;
	        this.lastName = null;
	        this.address = null;
	        this.state = null;
	        this.licenseNumber = null;
	        this.cardNumber = null;
	        this.createdDate = null;
	        this.memberUntil = null;
	        this.cardExpiration = null;
	        this.userStatus = null;
	    }

	    public CustomerImpl( String userName,
	                       String password,
	                       String email,
	                       String firstName,
	                       String lastName,
	                       String address,
	                       String state,
	                       String licenseNumber,
	                       String cardNumber,
	                       Date createdDate,
	                       Date memberUntil,
	                       Date cardExpiration,
							 UserStatus userStatus)
	    {
	        super( -1 );
	        this.userName = userName;
	        this.password = password;
	        this.email = email;
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.address = address;
	        this.state = state;
	        this.licenseNumber = licenseNumber;
	        this.cardNumber = cardNumber;
	        this.createdDate = createdDate;
	        this.memberUntil = memberUntil;
	        this.cardExpiration = cardExpiration;
	        this.userStatus = userStatus;
	    }
	
	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String getUserName() {
		return userName;
	}

	@Override
	public void setUserName(String userName) throws RARException {
		this.userName = userName;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Date getCreatedDate() {
		return createdDate;
	}

	@Override
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public UserStatus getUserStatus() {
		return userStatus;
	}

	@Override
	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	@Override
	public Date getMemberUntil() {
		return memberUntil;
	}

	@Override
	public void setMemberUntil(Date memberUntil) throws RARException {
		this.memberUntil = memberUntil;
	}

	@Override
	public String getLicenseState() {
		return state;
	}

	@Override
	public void setLicenseState(String state) {
		this.state = state;
	}

	@Override
	public String getLicenseNumber() {
		return licenseNumber;
	}

	@Override
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	@Override
	public String getCreditCardNumber() {
		return cardNumber;
	}

	@Override
	public void setCreditCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	@Override
	public Date getCreditCardExpiration() {
		return cardExpiration;
	}

	@Override
	public void setCreditCardExpiration(Date cardExpiration) {
		this.cardExpiration = cardExpiration;
	}

	
	//FINISH******************************************************************
	/*@Override
	public List<Reservation> getReservations() throws RARException {
		if( reservations  == null )
            if( isPersistent() ) {
                Reservation reservation = new ReservationImpl();
                reservation.setCustomer( this );
                reservations = getPersistenvalayer().restoreReservation( reservation );
                //System.out.println(  "Person.getClubsMembership: lazy traversal" );                         
            }

        return reservations;
	}*/
	@Override
	public List<Reservation> getReservations() throws RARException {
        if( reservations == null )
            if( isPersistent() ) {
                Reservation reservation = new ReservationImpl();
                reservation.setCustomer( this );
                reservations = getPersistencvalayer().restoreReservation(reservation);
            }
            else
                throw new RARException( "This reservation object is not persistent" );

        return reservations;
	}

	@Override
	public List<Comment> getComments() throws RARException {
        if( comments == null )
            if( isPersistent() ) {
                Comment comment = new CommentImpl();
                comment.setCustomer(this);
                comments= getPersistencvalayer().restoreComment(comment);
            }
            else
                throw new RARException( "This comment object is not persistent" );

        return comments;
    }

	@Override
	public List<Rental> getRentals() throws RARException {
        if( rentals == null )
            if( isPersistent() ) {
                Rental rental = new RentalImp();
                rental.setCustomer( this );
                rentals= getPersistencvalayer().restoreRental(rental);
            }
            else
                throw new RARException( "This retanl object is not persistent" );
        return rentals;
	}
	//FINISH******************************************************************
}
