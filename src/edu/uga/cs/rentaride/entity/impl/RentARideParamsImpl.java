package edu.uga.cs.rentaride.entity.impl;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.RentARideParams;
import edu.uga.cs.rentaride.persistence.impl.Persistence;

public class RentARideParamsImpl extends Persistence implements RentARideParams{

	private int membershipPrice;
	private int lateFee;

	public RentARideParamsImpl(){
		super(-1);
		this.membershipPrice = 0;
		this.lateFee = 0;
	}

	public RentARideParamsImpl(int membershipPrice, int lateFee) {
		super(-1);
		this.membershipPrice = membershipPrice;
		this.lateFee = lateFee;
	}


	@Override
	public int getMembershipPrice() {
		return membershipPrice;
	}

	@Override
	public void setMembershipPrice(int membershipPrice) throws RARException {
		this.membershipPrice = membershipPrice;
	}

	@Override
	public int getLateFee() {
		return lateFee;
	}

	@Override
	public void setLateFee(int lateFee) throws RARException {
		this.lateFee = lateFee;
	}
}
