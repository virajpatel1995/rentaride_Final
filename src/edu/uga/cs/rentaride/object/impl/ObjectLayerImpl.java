package edu.uga.cs.rentaride.object.impl;

import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.*;
import edu.uga.cs.rentaride.entity.impl.*;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.persistence.PersistenceLayer;
import edu.uga.cs.rentaride.persistence.impl.Persistence;

import edu.uga.cs.rentaride.entity.impl.AdministratorImpl;
import edu.uga.cs.rentaride.entity.impl.CustomerImpl;
import edu.uga.cs.rentaride.entity.impl.RentalImp;
import edu.uga.cs.rentaride.entity.impl.RentalLocationImpl;
import edu.uga.cs.rentaride.entity.impl.ReservationImpl;
import edu.uga.cs.rentaride.entity.impl.VehicleTypeImpl;



public class ObjectLayerImpl implements ObjectLayer {
	
	PersistenceLayer persistence = null;

	
	public ObjectLayerImpl(){
        this.persistence = null;
        System.out.println( "ObjectLayerImpl.ObjectLayerImpl(): initialized" );
    }
	
	public ObjectLayerImpl( PersistenceLayer persistence ){
        this.persistence = persistence;
        System.out.println( "ObjectLayerImpl.ObjectLayerImpl(persistence): initialized" );
    }
	
	@Override
	public Administrator createAdministrator() {
		AdministratorImpl Administrator = new AdministratorImpl();

		Persistence.setPersistencvalayer(persistence);
		return Administrator;
		
	}

	@Override
	public void setPersistence(PersistenceLayer persistence) {
		this.persistence = persistence;
	}//setPersistence
	
	@Override
	public Administrator createAdministrator(String firstName, String lastName, String userName, String password,
			String email, String address, Date createDate) throws RARException {

		AdministratorImpl Administrator = new AdministratorImpl(userName, password, email, firstName, lastName, address, createDate, UserStatus.ACTIVE);
		Persistence.setPersistencvalayer(persistence);
		return Administrator;
			}

	
	@Override
	public List<Administrator> findAdministrator(Administrator modelAdministrator) throws RARException {
		
		return persistence.restoreAdministrator(modelAdministrator);
	}

	@Override
	public void storeAdministrator(Administrator administrator) throws RARException {
		
		persistence.storeAdministrator(administrator);		
	}

	@Override
	public void deleteAdministrator(Administrator administrator) throws RARException {
		persistence.deleteAdministrator(administrator);
		
	}
	
	@Override
	public Customer createCustomer() {
    
		CustomerImpl Customer = new CustomerImpl();
		Persistence.setPersistencvalayer(persistence);
		return Customer;
	}

	@Override
	public Customer createCustomer(String firstName, String lastName, String userName, String password, String email,
			String address, Date createDate, Date membershipExpiration, String licenseState, String licenseNumber,
			String cardNumber, Date cardExpiration, UserStatus userStatus) throws RARException {
	
		CustomerImpl Customer = new CustomerImpl(userName, password, email, firstName, lastName, address, licenseState, licenseNumber, cardNumber,createDate, membershipExpiration, cardExpiration, userStatus);
		Persistence.setPersistencvalayer(persistence);
		return Customer;
		
	}

	
	@Override
	public List<Customer> findCustomer(Customer modelCustomer) throws RARException {
		return persistence.restoreCustomer(modelCustomer);
	}

	@Override
	public void storeCustomer(Customer customer) throws RARException {
		persistence.storeCustomer(customer);
		
	}

	@Override
	public RentalLocation createRentalLocation(String name, String address, int capacity) throws RARException {

		RentalLocationImpl RentalLocation = new RentalLocationImpl(name, address, capacity);
		Persistence.setPersistencvalayer(persistence);
		return RentalLocation;
	}

	@Override
	public RentalLocation createRentalLocation() {

		RentalLocationImpl RentalLocation = new RentalLocationImpl(null, null, 0);
		Persistence.setPersistencvalayer(persistence);
		return RentalLocation;
	}

	@Override
	public List<RentalLocation> findRentalLocation(RentalLocation modelRentalLocation) throws RARException {
		return persistence.restoreRentalLocation(modelRentalLocation);
	}

	@Override
	public void storeRentalLocation(RentalLocation rentalLocation) throws RARException {
		persistence.storeRentalLocation(rentalLocation);
	}

	@Override
	public void deleteRentalLocation(RentalLocation rentalLocation) throws RARException {
		persistence.deleteRentalLocation(rentalLocation);
	}

	@Override
	public Reservation createReservation(Date pickupTime, int rentalLength, VehicleType vehicleType,
			RentalLocation rentalLocation, Customer customer) throws RARException {
    
		ReservationImpl Reservation = new ReservationImpl(pickupTime, rentalLength, customer, vehicleType, rentalLocation, null);
		Persistence.setPersistencvalayer(persistence);
		return Reservation;
	}

	@Override
	public Reservation createReservation() {

		ReservationImpl Reservation = new ReservationImpl();
		Persistence.setPersistencvalayer(persistence);
		return Reservation;
	}

	@Override
	public List<Reservation> findReservation(Reservation modelReservation) throws RARException {
		return  persistence.restoreReservation(modelReservation);
	}

	@Override
	public void storeReservation(Reservation reservation) throws RARException {
		 persistence.restoreReservation(reservation);
		
	}

	@Override
	public void deleteReservation(Reservation reservation) throws RARException {
		 persistence.deleteReservation(reservation);
		
	}

	@Override
	public Rental createRental(Date pickupTime, Reservation reservation, Vehicle vehicle) throws RARException {

		RentalImp Rental = new RentalImp(pickupTime, null, false, 0, reservation, vehicle, reservation.getCustomer(), null);
		Persistence.setPersistencvalayer(persistence);
		return Rental;
	}

	@Override
	public Rental createRental() {

		RentalImp Rental = new RentalImp();
		Persistence.setPersistencvalayer(persistence);
		return Rental;
	}

	@Override
	public List<Rental> findRental(Rental modelRental) throws RARException {
		
		return  persistence.restoreRental(modelRental);

	}

	@Override
	public void storeRental(Rental rental) throws RARException {
		  persistence.restoreRental(rental);
	
	}

	@Override
	public void deleteRental(Rental rental) throws RARException {
		  persistence.deleteRental(rental);

		
	}

	@Override
	public VehicleType createVehicleType(String name) throws RARException {

		VehicleTypeImpl VehicleType = new VehicleTypeImpl(name);
		Persistence.setPersistencvalayer(persistence);
		return VehicleType;
	}

	@Override
	public VehicleType createVehicleType() {

		VehicleTypeImpl VehicleType = new VehicleTypeImpl();
		Persistence.setPersistencvalayer(persistence);
		return VehicleType;
	}

	@Override
	public List<VehicleType> findVehicleType(VehicleType modelVehicleType) throws RARException {
		return persistence.restoreVehicleType(modelVehicleType);

	}

	@Override
	public void storeVehicleType(VehicleType vehicleType) throws RARException {
		  persistence.restoreVehicleType(vehicleType);
		
	}

	@Override
	public void deleteVehicleType(VehicleType vehicleType) throws RARException {
		  persistence.deleteVehicleType(vehicleType);

	}

	@Override
	public Vehicle createVehicle(String make, String model, int year, String registrationTag, int mileage,
			Date lastServiced, VehicleType vehicleType, RentalLocation rentalLocation,
			VehicleCondition vehicleCondition, VehicleStatus vehicleStatus) throws RARException {
		Persistence.setPersistencvalayer(persistence);
		return new VehicleImpl(make, model,registrationTag,year,mileage,lastServiced,vehicleStatus,vehicleCondition,vehicleType,rentalLocation);
	}

	@Override
	public Vehicle createVehicle() {
		Persistence.setPersistencvalayer(persistence);
		return new VehicleImpl();
	}

	@Override
	public List<Vehicle> findVehicle(Vehicle modelVehicle) throws RARException {
		return persistence.restoreVehicle(modelVehicle);
	}

	@Override
	public void storeVehicle(Vehicle vehicle) throws RARException {
		persistence.storeVehicle(vehicle);
	}

	@Override
	public void deleteVehicle(Vehicle vehicle) throws RARException {
		persistence.deleteVehicle(vehicle);
	}

	@Override
	public Comment createComment(String text, Date date, Rental rental, Customer customer) throws RARException {
		Comment comment = new CommentImpl(text, date,rental,customer);
		Persistence.setPersistencvalayer(persistence);
		return comment;
	}

	@Override
	public Comment createComment() {
		Comment comment = new CommentImpl();
		Persistence.setPersistencvalayer(persistence);
		return comment;
	}

	@Override
	public List<Comment> findComment(Comment modelComment) throws RARException {

		return persistence.restoreComment(modelComment);
	}

	@Override
	public void storeComment(Comment comment) throws RARException {
		persistence.storeComment(comment);
		
	}

	@Override
	public void deleteComment(Comment comment) throws RARException {
		persistence.deleteComment(comment);
	}

	@Override
	public HourlyPrice createHourlyPrice(int maxHrs, int price, VehicleType vehicleType) throws RARException {
		HourlyPrice hourlyPrice = new HourlyPriceImpl(maxHrs, price, vehicleType);
		Persistence.setPersistencvalayer(persistence);
		return hourlyPrice;
	}

	@Override
	public HourlyPrice createHourlyPrice() {
		Persistence.setPersistencvalayer(persistence);
		return null;
	}

	@Override
	public List<HourlyPrice> findHourlyPrice(HourlyPrice modelHourlyPrice) throws RARException {
		return persistence.restoreHourlyPrice(modelHourlyPrice);
	}

	@Override
	public void storeHourlyPrice(HourlyPrice hourlyPrice) throws RARException {
		persistence.storeHourlyPrice(hourlyPrice);
	}

	@Override
	public void deleteHourlyPrice(HourlyPrice hourlyPrice) throws RARException {
		persistence.deleteHourlyPrice(hourlyPrice);
	}

	@Override
	public RentARideParams createRentARideParams() {
		RentARideParams rentARideParams = new RentARideParamsImpl();
		Persistence.setPersistencvalayer(persistence);
		return rentARideParams;
	}

	@Override
	public RentARideParams findRentARideParams() throws RARException {
		return persistence.restoreRentARideConfig();
	}

	@Override
	public void storeRentARideParams(RentARideParams rentARideParams) throws RARException {
		persistence.storeRentARideConfig(rentARideParams);

	}
	
	@Override
	public void deleteCustomer(Customer customer) throws RARException{
		persistence.deleteCustomer(customer);
	}//deleteCustoemr

}
