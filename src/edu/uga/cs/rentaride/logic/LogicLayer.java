package edu.uga.cs.rentaride.logic;


import java.text.ParseException;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.*;
import edu.uga.cs.rentaride.session.*;

import edu.uga.cs.rentaride.RARException;


public interface LogicLayer
{
	

	public long 				registerAccount ( String fName, String lName, String email, String password, String username, String driverNo, String cardNo, String expDate, String address, String state, String zip ) throws RARException, ParseException;
	public void               logout( String ssid ) throws RARException;
    public String             login( Session session, String userName, String password ) throws RARException;
    public long               createRentalLocation( String locationName, String address, int locationCapacity ) throws RARException;
    public User checkUser(String username, String firstName, String lastName) throws RARException;
    public void updatePassword(String password, User user) throws RARException;
    public void CancelMembership(User user) throws RARException;
    public long createComment(String comm, Long ren) throws RARException;
    public long CreateVehicle(String make, String model, int year, int mileage, String tag, String location, String type) throws RARException;
    public long UpdateVehicle( int mileage, String tag, String location, boolean maintence) throws RARException;
    public long CreateVehicleType( String vehicleType ) throws RARException;
    public long UpdateVehicleType( String oldVehicleType, String newVehicleType ) throws RARException;
    public long UpdateHourlyPrice( String vehicleType, int hourlyPrice, int maxHours ) throws RARException;
    
    public  RentARideParams getRenARideParams() throws RARException;

    void updateRenARideParams(RentARideParams rentARideParams) throws RARException;
    List<RentalLocation> getAllRentalLocations() throws RARException;
    List<VehicleType> getAllVehicleTypes() throws RARException;

    public long updateAdministrator (String username, String fName, String lName, String email, String address) throws RARException;

	public long updateCustomer (String username, String fName, String lName, String email, String address, String card, String expire) throws RARException;


    RentalLocation getRentalLocationById(long id) throws RARException;

    void updateRentalLocation(RentalLocation rl) throws RARException;
	public long CancelReservation(String res) throws RARException;
	public long placeRental(String reservationIdS, String vehicleTag) throws RARException;

    List<Vehicle> getAllVehicle() throws RARException;

    Vehicle getVehicleById(long vid) throws RARException;

    RentalLocation getRentalLocationByName(String localRentalLocation) throws RARException;

    void updateVehicle(Vehicle vehicle) throws RARException;
}
