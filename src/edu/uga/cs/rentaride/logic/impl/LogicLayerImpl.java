/**
 *
 */
package edu.uga.cs.rentaride.logic.impl;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.*;
import edu.uga.cs.rentaride.entity.impl.RentalLocationImpl;
import edu.uga.cs.rentaride.logic.*;
import edu.uga.cs.rentaride.object.*;
import edu.uga.cs.rentaride.object.impl.*;
import edu.uga.cs.rentaride.persistence.*;
import edu.uga.cs.rentaride.persistence.impl.*;
import edu.uga.cs.rentaride.presentation.CreateRentalLocation;
import edu.uga.cs.rentaride.session.*;


/**
 * @author Krys J. Kochut
 */
public class LogicLayerImpl
        implements LogicLayer {
    private ObjectLayer objectLayer = null;

    public LogicLayerImpl(Connection conn) {
        objectLayer = new ObjectLayerImpl();
        PersistenceLayer persistenceLayer = null;
        try {
            persistenceLayer = new PersistenceLayerImpl(conn, objectLayer);
        } catch (RARException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        objectLayer.setPersistence(persistenceLayer);
        System.out.println("LogicLayerImpl.LogicLayerImpl(conn): initialized");
    }

    public LogicLayerImpl(ObjectLayer objectLayer) {
        this.objectLayer = objectLayer;
        System.out.println("LogicLayerImpl.LogicLayerImpl(objectLayer): initialized");
    }

    /*
        public List<Club> findAllClubs()
                throws RARException
        {
            FindAllRARCtrl ctrlFindAllClubs = new FindAllRARCtrl( objectLayer );
            return ctrlFindAllClubs.findAllClubs();
        }

        public List<Person> findAllPersons()
                throws RARException
        {
            FindAllPersonsCtrl ctrlFindAllPersons = new FindAllPersonsCtrl( objectLayer );
            return ctrlFindAllPersons.findAllPersons();
        }

        public long joinClub(Person person, Club club) throws RARException
        {
            return 0;
        }

        public long joinClub(long personId, String clubName) throws RARException
        {
            JoinRARCtrl ctrlJoinClub = new JoinRARCtrl( objectLayer );
            return ctrlJoinClub.joinClub( personId, clubName );
        }

        public long createClub(String clubName, String address, long founderId)
                throws RARException
        {
            CreatRARCtrl ctrlCreateClub = new CreatRARCtrl( objectLayer );
            return ctrlCreateClub.createClub( clubName, address, founderId );
        }

        public long createPerson(String userName, String password, String email,
                String firstName, String lastName, String address, String phone)
                throws RARException
        {
            CreatePersonCtrl ctrlCreatePerson = new CreatePersonCtrl( objectLayer );
            return ctrlCreatePerson.createPerson( userName, password, email, firstName,
                                                  lastName, address, phone );
        }

        public List<Person> findClubMembers(String clubName) throws RARException
        {
            FindRARMembersCtrl ctrlFindClubMembers = new FindRARMembersCtrl( objectLayer );
            return ctrlFindClubMembers.findClubMembers( clubName );
        }
    */
    public void logout(String ssid) throws RARException {
        SessionManager.logout(ssid);
    }

    public String login(Session session, String userName, String password)
            throws RARException {
        LoginCtrl ctrlVerifyPerson = new LoginCtrl(objectLayer);
        return ctrlVerifyPerson.login(session, userName, password);
    }


    @Override
    public long registerAccount(String fName, String lName, String email, String password, String username, String licNumber,
                                String cardNo, String expDate, String address, String state, String zip) throws RARException, ParseException {
        //objectLayer.storeCustomer(customer);
        //return customer.getId();
    		registerAccountCtrl ctrlVerifyAccount = new registerAccountCtrl(objectLayer);
    		return ctrlVerifyAccount.createAccount(fName, lName, username, email, password, licNumber, cardNo, expDate, address, state, zip);
    }

    //
	@Override
	public long createRentalLocation(String locationName, String address, int locationCapacity) throws RARException {
		CreateRentalLocationCtrl ctrlRentalLocation = new CreateRentalLocationCtrl(objectLayer);
		return ctrlRentalLocation.createRentalLocation(locationName, address, locationCapacity);
	}

	@Override
	public long createComment(String comm, Long ren) throws RARException{
		CreateCommentCtrl ctrlComment = new CreateCommentCtrl(objectLayer);
		return ctrlComment.createComment(comm, ren);
	}

	@Override
	public long CreateVehicle(String make, String model, int year, int mileage, String tag, String location, String type) throws RARException{
		CreateVehicleCtrl ctrlVehicle = new CreateVehicleCtrl(objectLayer);
		return ctrlVehicle.createVehicle(  make,  model,  year,  mileage , tag,  location, type);

	}

	@Override
	public long UpdateVehicle( int mileage, String tag, String location, boolean maintence) throws RARException{
		UpdateVehicleCtrl ctrlVehicle = new UpdateVehicleCtrl(objectLayer);
		return ctrlVehicle.UpdateVehicle(mileage, tag, location,maintence);
	}

	@Override
	public long CreateVehicleType( String vehicleType ) throws RARException{
		CreateVehicleTypeCtrl ctrlVehicleType = new CreateVehicleTypeCtrl(objectLayer);
		return ctrlVehicleType.CreateVehicleType( vehicleType );
	}

	@Override
	public long UpdateVehicleType( String oldVehicleType, String newVehicleType ) throws RARException{
		UpdateVehicleTypeCtrl ctrlVehicleType = new UpdateVehicleTypeCtrl(objectLayer);
		return ctrlVehicleType.UpdateVehicleType( oldVehicleType, newVehicleType );
	}

    @Override
    public User checkUser(String username, String firstName, String lastName) throws RARException {
        Customer customer = objectLayer.createCustomer();
        customer.setUserName(username);
        //customer.setEmail(email);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        Administrator administrator = objectLayer.createAdministrator();
        administrator.setUserName(username);
       // administrator.setEmail(email);
        administrator.setFirstName(firstName);
        administrator.setLastName(lastName);

        User user = null;
        List<Customer> customers = objectLayer.findCustomer(customer);
        List<Administrator> administrators = objectLayer.findAdministrator(administrator);

        if (customers.size() > 0) {
            user = customers.get(0);
        } else if (administrators.size() > 0) {
            user = administrators.get(0);
        }
        return user;
    }

    @Override
    public void updatePassword(String password, User user) throws RARException {
        user.setPassword(password);
        if (user instanceof Administrator) {
            objectLayer.storeAdministrator((Administrator) user);
        } else if (user instanceof Customer) {
            objectLayer.storeCustomer((Customer) user);
        }
    }

    @Override
    public long UpdateHourlyPrice(String vehicleType, int hourlyPrice, int maxHours) throws RARException {
    	UpdateHourlyPriceCtrl ctrlHourlyPrice = new UpdateHourlyPriceCtrl(objectLayer);
		return ctrlHourlyPrice.updateHourlyPrice(vehicleType, hourlyPrice, maxHours);
    }

    @Override
    public RentARideParams getRenARideParams() throws RARException {
        return objectLayer.findRentARideParams();
    }

    @Override
    public void updateRenARideParams(RentARideParams rentARideParams) throws RARException {
        objectLayer.storeRentARideParams(rentARideParams);
    }

    @Override
    public List<RentalLocation> getAllRentalLocations() throws RARException {
        return objectLayer.findRentalLocation(null);
    }

    @Override
    public List<VehicleType> getAllVehicleTypes() throws RARException {
        return objectLayer.findVehicleType(null);
    }
	@Override
	public long updateCustomer(String username, String fName, String lName, String email, String address, String card, String expire) throws RARException {

		UpdateCustomerCtrl ctrlCustomer = new UpdateCustomerCtrl(objectLayer);
		return ctrlCustomer.updateCustomer(username, fName, lName, email, address, card, expire);
 

	}
	@Override
	public long updateAdministrator(String username, String fName, String lName, String email, String address) throws RARException {

		UpdateAdministratorCtrl ctrlAdministrator = new UpdateAdministratorCtrl(objectLayer);
		return ctrlAdministrator.updateAdministrator(username, fName, lName, email, address);

	}

    @Override
    public RentalLocation getRentalLocationById(long id) throws RARException {
        RentalLocation rentalLocation = objectLayer.createRentalLocation();
        rentalLocation.setId(id);
        return objectLayer.findRentalLocation(rentalLocation).get(0);
    }

    @Override
    public void updateRentalLocation(RentalLocation rl) throws RARException {
        objectLayer.storeRentalLocation(rl);
    }
		

	@Override
	public long placeRental(String reservationIdS, String vehicleTag) throws RARException {
		PlaceRentalCtrl ctrlPlacer = new PlaceRentalCtrl(objectLayer);
		return ctrlPlacer.placeRental(reservationIdS, vehicleTag );
	}

		@Override
	public void CancelMembership(User user) throws RARException {
		user.setUserStatus(UserStatus.CANCELLED);
		objectLayer.storeCustomer((Customer)user);
	}

		@Override
		public long CancelReservation(String res) throws RARException {
			CancelReservationCtrl ctrlCancelReservation = new CancelReservationCtrl(objectLayer);
			return ctrlCancelReservation.cancelReservation(res);
		}

    @Override
    public List<Vehicle> getAllVehicle() throws RARException {
        return objectLayer.findVehicle(null);
    }

    @Override
    public Vehicle getVehicleById(long vid) throws RARException {
        Vehicle vehicle = objectLayer.createVehicle();
        vehicle.setId(vid);
        return objectLayer.findVehicle(vehicle).get(0);

    }

    @Override
    public RentalLocation getRentalLocationByName(String rentalLocationName) throws RARException {
        RentalLocation rentalLocation = objectLayer.createRentalLocation();
        rentalLocation.setName(rentalLocationName);
        return objectLayer.findRentalLocation(rentalLocation).get(0);
    }

    @Override
    public void updateVehicle(Vehicle vehicle) throws RARException {
        objectLayer.storeVehicle(vehicle);
    }
		@Override
		public long TerminateMembership(String customerUser) throws RARException {
			TerminateMembershipCtrl ctrlTerminateMembership = new TerminateMembershipCtrl(objectLayer);
			return ctrlTerminateMembership.TerminateMembership(customerUser);
		}


    @Override
		public long placeReservation(String time, String dur, String loc, String type, String uName) throws RARException {
			PlaceReservationCtrl ctrlPlaceReservation = new PlaceReservationCtrl(objectLayer);
			return ctrlPlaceReservation.placeReservation(time, dur, loc, type, uName);
		}

    @Override
    public List<Customer> getAllCustomer() throws RARException {
        return objectLayer.findCustomer(null);
    }


		@Override
		public List<Reservation> getReservations(String c) throws RARException{
			GetReservationCtrl ctrlGetReservation = new GetReservationCtrl(objectLayer);
			return ctrlGetReservation.getReservation(c);
		}

    @Override
    public List<Reservation> getAllReservation() throws RARException {
        return objectLayer.findReservation(null);
    }

    @Override
    public List<Comment> getAllComment() throws RARException {
        return objectLayer.findComment(null);
    }

	@Override
	public long returnRental(long rentalIDLong) throws RARException {
		ReturnRentalCtrl ctrlReturnRental = new ReturnRentalCtrl(objectLayer);
		return ctrlReturnRental.returnRental(rentalIDLong);
	}

    @Override
    public List<Vehicle> getVehicleByLocationAndType(String location, String vt) throws RARException {

        Vehicle vehicle = objectLayer.createVehicle();
        if(!location.isEmpty()) {
            RentalLocation rentalLocation = objectLayer.createRentalLocation();
            rentalLocation.setName(location);
            vehicle.setRentalLocation(objectLayer.findRentalLocation(rentalLocation).get(0));
        }
        VehicleType vehicleType = objectLayer.createVehicleType();
        vehicleType.setName(vt);
        vehicle.setVehicleType(objectLayer.findVehicleType(vehicleType).get(0));

        return objectLayer.findVehicle(vehicle);
    }

    @Override
    public List<Rental> getRentalByCustomer(String userName) throws RARException {
        Rental rental = objectLayer.createRental();
        Customer customer = objectLayer.createCustomer();
        customer.setUserName(userName);
        rental.setCustomer(objectLayer.findCustomer(customer).get(0));
        return objectLayer.findRental(rental);
    }
}
