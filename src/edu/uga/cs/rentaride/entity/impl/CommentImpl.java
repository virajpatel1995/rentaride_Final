package edu.uga.cs.rentaride.entity.impl;

import java.util.Date;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.UserStatus;
import edu.uga.cs.rentaride.persistence.impl.Persistence;

public class CommentImpl extends Persistence implements Comment {

	// Comment Attributes
		private String           text;
	    private Date			 date;
	    private Rental			 rental;
	    private Customer		 customer;


	public CommentImpl()
	    {
	        super( -1 );
	        this.text = null;
	        this.date = null;
	        this.rental = null;
	        this.customer = null;
	    }

	    public CommentImpl( String text,
	                       Date date,
	                       Rental rental,
	                       Customer customer)
	    {
	    	super( -1 );
	        this.text = text;
	        this.date = date;
	        this.rental = rental;
	        this.customer = customer;
	    }

	@Override
	public String getText() {
		return text;
	}

	@Override
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public Rental getRental() {
		return rental;
	}

	@Override
	public void setRental(Rental rental) throws RARException {
		this.rental = rental;
	}

	@Override
	public Customer getCustomer() {
		return customer;
	}
	@Override
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
