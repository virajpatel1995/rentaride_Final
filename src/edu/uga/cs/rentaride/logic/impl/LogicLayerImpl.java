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
    public User checkUser(String username, String email) throws RARException {
        Customer customer = objectLayer.createCustomer();
        customer.setUserName(username);
        customer.setEmail(email);
        Administrator administrator = objectLayer.createAdministrator();
        administrator.setUserName(username);
        administrator.setEmail(email);

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
}
