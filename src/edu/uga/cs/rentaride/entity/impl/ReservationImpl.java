package edu.uga.cs.rentaride.entity.impl;

import java.util.Date;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.persistence.impl.Persistence;

public class ReservationImpl extends Persistence implements Reservation {

	private Date pickupTime;
	private int length;
	private Customer customer;
	private VehicleType vehicleType;
	private RentalLocation rentalLocation;
	private Rental rental;
	
	public ReservationImpl(){
		super(-1);
		this.pickupTime = null;
		this.length = 0;
		this.customer = null;
		this.vehicleType = null;
		this.rentalLocation = null;
		this.rental = null;
	}

	public ReservationImpl(Date pickupTime, int length, Customer customer, VehicleType vehicleType, RentalLocation rentalLocation, Rental rental) {
		super(-1);
		this.pickupTime = pickupTime;
		this.length = length;
		this.customer = customer;
		this.vehicleType = vehicleType;
		this.rentalLocation = rentalLocation;
		this.rental = rental;
	}

	@Override
	public Date getPickupTime() {
		return pickupTime;
	}

	@Override
	public void setPickupTime(Date pickupTime) throws RARException{
		this.pickupTime = pickupTime;
	}

	@Override
	public int getLength() {
		return length;
	}

	@Override
	public void setLength(int length) throws RARException{
		this.length = length;
	}

	@Override
	public Customer getCustomer() {
		return customer;
	}

	@Override
	public void setCustomer(Customer customer) throws RARException{
		this.customer = customer;
	}

	@Override
	public VehicleType getVehicleType() {
		return vehicleType;
	}

	@Override
	public void setVehicleType(VehicleType vehicleType) throws RARException{
		this.vehicleType = vehicleType;
	}

	@Override
	public RentalLocation getRentalLocation() {
		return rentalLocation;
	}

	@Override
	public void setRentalLocation(RentalLocation rentalLocation) throws RARException{
		this.rentalLocation = rentalLocation;
	}

	@Override
	public Rental getRental() {
		return rental;
	}

	@Override
	public void setRental(Rental rental){
		this.rental = rental;
	}
}
