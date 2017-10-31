package edu.uga.cs.rentaride.entity.impl;

import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.VehicleCondition;
import edu.uga.cs.rentaride.entity.VehicleStatus;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.persistence.impl.Persistence;

public class VehicleImpl extends Persistence implements Vehicle {

	// Vehicle Attributes
    private String			 make;
    private String			 model;
    private String 			 registrationTag;
    private int				 year;
    private int				 mileage;
    private Date			 lastServiced;
    private VehicleStatus 	 status;
    private VehicleCondition condition;
    private VehicleType		 vehicleType;
    private RentalLocation   rentalLocation;
    private List<Rental> 	rentals;
    
    public VehicleImpl()
    {
        super( -1 );
        this.make = null;
        this.model = null;
        this.registrationTag = null;
        this.year = 0;
        this.mileage = 0;
        this.lastServiced = null;
        this.status = null;
        this.condition = null;
        this.vehicleType = null;
        this.rentalLocation = null;
    }

    public VehicleImpl(String make,
    				   String model,
    				   String registrationTag,
                       int year,
                       int mileage,
                       Date lastServiced,
                       VehicleStatus status,
                       VehicleCondition condition,
                       VehicleType vehicleType,
                       RentalLocation rentalLocation)
    {
    	super( -1 );
    	this.make = make;
    	this.model = model;
    	this.registrationTag = registrationTag;
        this.year = year;
        this.mileage = mileage;
        this.lastServiced = lastServiced;
        this.status = status;
        this.condition = condition;
        this.vehicleType = vehicleType;
        this.rentalLocation = rentalLocation;
    }

	@Override
	public String getMake() {
		return make;
	}

	@Override
	public void setMake(String make) {
		this.make = make;
	}

	@Override
	public String getModel() {
		return model;
	}

	@Override
	public void setModel(String model) {
		this.model = model;
	}

	@Override
	public int getYear() {
		return year;
	}

	@Override
	public void setYear(int year) throws RARException {
		this.year = year;
	}

	@Override
	public String getRegistrationTag() {
		return registrationTag;
	}

	@Override
	public void setRegistrationTag(String registrationTag) {
		this.registrationTag = registrationTag;
	}

	@Override
	public int getMileage() {
		return mileage;
	}

	@Override
	public void setMileage(int mileage) throws RARException {
		this.mileage = mileage;
	}

	@Override
	public Date getLastServiced() {
		return lastServiced;
	}

	@Override
	public void setLastServiced(Date lastServiced) {
		this.lastServiced = lastServiced;
	}

	@Override
	public VehicleStatus getStatus() {
		return status;
	}

	@Override
	public void setStatus(VehicleStatus status) {
		this.status = status;
	}

	@Override
	public VehicleCondition getCondition() {
		return condition;
	}

	@Override
	public void setCondition(VehicleCondition condition) {
		this.condition = condition;
	}

	@Override
	public VehicleType getVehicleType() {
		return vehicleType;
	}

	@Override
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	@Override
	public RentalLocation getRentalLocation() {
		return rentalLocation;
	}

	@Override
	public void setRentalLocation(RentalLocation rentalLocation) throws RARException {
		this.rentalLocation	= rentalLocation;
	}

	//FINISH***************************************************************************
	@Override
	public List<Rental> getRentals() throws RARException {
		if( rentals == null )
			if( isPersistent() ) {
				Rental rental = new RentalImp();
				rental.setVehicle( this );
				rentals = getPersistencvalayer().restoreRental(rental);
			}
			else
				throw new RARException( "This rental object is not persistent" );

		return rentals;
	}
	//FINISH***************************************************************************

}
