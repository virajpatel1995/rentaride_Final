package edu.uga.cs.rentaride.entity.impl;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.*;
import edu.uga.cs.rentaride.persistence.impl.Persistence;

import java.util.Date;
import java.util.List;

public class RentalLocationImpl extends Persistence implements RentalLocation {

    private String name;
    private String address;
    private int capacity;
    private List<Reservation> reservations;
    private List<Vehicle> vehicles;

    public RentalLocationImpl(String name, String address, int capacity) {
        super(-1);
        this.name = name;
        this.address = address;
        this.capacity = capacity;
    }
    public RentalLocationImpl(){
        super(-1);
        this.name = null;
        this.address = null;
        this.capacity = 0;
    }

    @Override
    public void setName(String name) throws RARException{
        this.name = name;
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
    public int getCapacity() {
        return capacity;
    }

    @Override
    public void setCapacity(int capacity) throws RARException{
        this.capacity = capacity;
    }

    @Override
    public List<Reservation> getReservations() throws RARException {
        if( this == null )
            if( isPersistent() ) {
                reservations= getPersistencvalayer().restoreReservationRentalLocation(this);
            }
            else
                throw new RARException( "This retanl object is not persistent" );
        return reservations;
    }

    @Override
    public List<Vehicle> getVehicles() throws RARException {
        if( this == null )
            if( isPersistent() ) {
                vehicles= getPersistencvalayer().restoreVehicleRentalLocation(this);
            }
            else
                throw new RARException( "This retanl object is not persistent" );
        return vehicles;
    }

    @Override
	public String getName() {
		return name;
	}


}
