package edu.uga.cs.rentaride.entity.impl;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.HourlyPrice;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.persistence.impl.Persistence;

public class HourlyPriceImpl extends Persistence implements HourlyPrice{

	// HourlyPrice Attributes
		    private int			 	 maxHours;
		    private int				 price;
		    private VehicleType		 vehicleType;
		    
		    public HourlyPriceImpl()
		    {
		        super( -1 );
		        this.maxHours = 0;
		        this.price = 0;
		        this.vehicleType = null;
		    }

		    public HourlyPriceImpl( int maxHours,
		                       int price,
		                       VehicleType vehicleType
		                      )
		    {
		    	super( -1 );
		        this.maxHours = maxHours;
		        this.price = price;
		        this.vehicleType = vehicleType;
		    }

	@Override
	public int getMaxHours() {
		return maxHours;
	}

	@Override
	public void setMaxHours(int maxHours) throws RARException {
		this.maxHours = maxHours;
	}

	@Override
	public int getPrice() {
		return price;
	}

	@Override
	public void setPrice(int price) throws RARException {
		this.price = price;
	}

	@Override
	public VehicleType getVehicleType() {
		return vehicleType;
	}

	@Override
	public void setVehicleType(VehicleType vehicleType) throws RARException {
		this.vehicleType = vehicleType;
	}

}
