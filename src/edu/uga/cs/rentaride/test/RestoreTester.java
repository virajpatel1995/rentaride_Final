package edu.uga.cs.rentaride.test;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.*;
import edu.uga.cs.rentaride.entity.impl.*;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.object.impl.ObjectLayerImpl;
import edu.uga.cs.rentaride.persistence.PersistenceLayer;
import edu.uga.cs.rentaride.persistence.impl.DbUtils;
import edu.uga.cs.rentaride.persistence.impl.PersistenceLayerImpl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Junwei on 10/23/2017.
 */
public class RestoreTester  {
    public static void main(String[] args) throws RARException {
        Connection conn = null;
        ObjectLayer objectLayer = null;
        PersistenceLayer persistence = null;

        // get a database connection
        try {
            conn = DbUtils.connect();
        } catch (Exception seq) {
            System.err.println("ReadTest: Unable to obtain a database connection");
        }

        if (conn == null) {
            System.out.println("ReadTest: failed to connect to the database");
            return;
        }

        // connect the ObjectModel module to the Persistence module
        objectLayer = new ObjectLayerImpl(persistence);
        // obtain a reference to Persistence module and connect it to the ObjectModel
        persistence = new PersistenceLayerImpl( conn, objectLayer );

//        Administrator administrator = new AdministratorImpl();
//        administrator.setId(2);
//
//        List<Administrator> administratorArrayList = persistence.restoreAdministrator(administrator);
//        printAdminList(administratorArrayList);

//        Comment comment = new CommentImpl();
//        comment.setText("Great carrrr");
//
//        List<Comment> comments = persistence.restoreComment(comment);
//        printCommentList(comments);

//        Customer customer = new CustomerImpl();
//        customer.setLastName("Dawson");
//
//        List<Customer> customers = persistence.restoreCustomer(customer);
//        printCustomerList(customers);

//        HourlyPrice hourlyPrice = new HourlyPriceImpl();
//        hourlyPrice.setPrice(5);
//        List<HourlyPrice> hourlyPrices = persistence.restoreHourlyPrice(null);
//        printPriceList(hourlyPrices);

        RentalLocation rentalLocation = new RentalLocationImpl();
        rentalLocation.setName("bbbbbb");
        List<RentalLocation> rentalLocations = persistence.restoreRentalLocation(rentalLocation);
//        printReservationList(persistence.restoreReservationRentalLocation(rentalLocations.get(0)));
        printVehicleList(persistence.restoreVehicleRentalLocation(rentalLocations.get(0)));
//        printRentalLocationList(rentalLocations);

//        Rental rental = new RentalImp();
//        rental.setId(13);
//        List<Rental> rentals = persistence.restoreRental(rental);
//        printRentalList(rentals);

//        Reservation reservation = new ReservationImpl();
//        reservation.setLength(5);
//        List<Reservation> reservations = persistence.restoreReservation(null);
//        printReservationList(reservations);

//        Vehicle vehicle = new VehicleImpl();
//        vehicle.setYear(2015);
//        List<Vehicle> vehicles = persistence.restoreVehicle(vehicle);
//        printVehicleList(vehicles);

//        VehicleType vehicleType = new VehicleTypeImpl();
//        vehicleType.setName("bigbigTruck");
//        List<VehicleType> vehicleTypes = persistence.restoreVehicleType(vehicleType);
//        printVehicleTypeList(vehicleTypes);

    }

    public static void printAdminList(List<Administrator> list) {
        System.out.println("Printing out admin list");
        for (Administrator admin: list) {
            System.out.println("ID: " + admin.getId());
            System.out.println("First Name: " + admin.getFirstName());
            System.out.println("Last Name: " + admin.getLastName());
            System.out.println("UserName: " + admin.getUserName());
            System.out.println("Email: " + admin.getEmail());
            System.out.println("Password: " + admin.getPassword());
            System.out.println("Created Date: " + admin.getCreatedDate());
            System.out.println("Address: " + admin.getAddress());
            System.out.println();
        }
    }
    public static void printCustomerList(List<Customer> list) {
        System.out.println("Printing out admin list");
        for (Customer customer: list) {
            System.out.println("ID: " + customer.getId());
            System.out.println("First Name: " + customer.getFirstName());
            System.out.println("Last Name: " + customer.getLastName());
            System.out.println("UserName: " + customer.getUserName());
            System.out.println("Email: " + customer.getEmail());
            System.out.println("Password: " + customer.getPassword());
            System.out.println("Created Date: " + customer.getCreatedDate());
            System.out.println("Address: " + customer.getAddress());
            System.out.println();
        }
    }
    public static void printCommentList(List<Comment> list) {
        System.out.println("Printing out comment  list");
        for (Comment comment: list) {
            System.out.println("ID: " + comment.getId());
            System.out.println("Comment date: " + comment.getDate());
            System.out.println("Text: " + comment.getText());
            System.out.println("Rental Id: " + comment.getRental().getId());
            System.out.println();
        }
    }
    public static void printPriceList(List<HourlyPrice> list) {
        System.out.println("Printing out Hourly Price list");
        for (HourlyPrice hourlyPrice: list) {
            System.out.println("ID: " + hourlyPrice.getId());
            System.out.println("Price: " + hourlyPrice.getPrice());
            System.out.println("Max hours: " + hourlyPrice.getMaxHours());
            System.out.println("Vehicle Type name: " + hourlyPrice.getVehicleType().getName());
            System.out.println();
        }
    }
    public static void printVehicleTypeList(List<VehicleType> list) {
        System.out.println("Printing out Hourly Price list");
        for (VehicleType vehicleType: list) {
            System.out.println("ID: " + vehicleType.getId());
            System.out.println("Name: " + vehicleType.getName());
            System.out.println();
        }
    }
    public static void printRentalLocationList(List<RentalLocation> list) {
        System.out.println("Printing out Rental Location list");
        for (RentalLocation rentalLocation: list) {
            System.out.println("ID: " + rentalLocation.getId());
            System.out.println("Name: " + rentalLocation.getName());
            System.out.println("Address: " + rentalLocation.getAddress());
            System.out.println("Capacity: " + rentalLocation.getCapacity());
            System.out.println();
        }
    }
    public static void printRentalList(List<Rental> list) {
        System.out.println("Printing out Rental  list");
        for (Rental rental: list) {
            System.out.println("ID: " + rental.getId());
            System.out.println("Charges: " + rental.getCharges());
            System.out.println("Late?: " + rental.getLate());
            System.out.println("Pickup Time: " + rental.getPickupTime());
            System.out.println("Return Time: " + rental.getReturnTime());
            System.out.println("Customer name: " + rental.getCustomer().getFirstName()+ " " + rental.getCustomer().getLastName());
            System.out.println("Vehicle: " + rental.getVehicle().getMake() + " " + rental.getVehicle().getModel());
            System.out.println("Comment: " + rental.getComment().getText());
            System.out.println();
        }
    }
    public static void printReservationList(List<Reservation> list) {
        System.out.println("Printing out Reservation  list");
        for (Reservation reservation: list) {
            System.out.println("ID: " + reservation.getId());
            System.out.println("Length: " + reservation.getLength());
            System.out.println("Rental Location?: " + reservation.getRentalLocation().getName());
            System.out.println("Pickup Time: " + reservation.getPickupTime());
            System.out.println("Vehicle Type: " + reservation.getVehicleType().getName());
            System.out.println("Customer name: " + reservation.getCustomer().getFirstName()+ " " + reservation.getCustomer().getLastName());
            System.out.println();
        }
    }
    public static void printVehicleList (List<Vehicle> list) {
        System.out.println("Printing out Reservation  list");
        for (Vehicle vehicle: list) {
            System.out.println("ID: " + vehicle.getId());
            System.out.println("Vehicle: " + vehicle.getMake() + " " + vehicle.getModel());
            System.out.println("Year: " + vehicle.getYear());
            System.out.println("Millage : " + vehicle.getMileage());
            System.out.println("Rental Location: " + vehicle.getRentalLocation().getName());
            System.out.println("Last Serviced: " + vehicle.getLastServiced());
            System.out.println("Vehicle Type: " + vehicle.getVehicleType().getName());
            System.out.println("Registration Tag : " + vehicle.getRegistrationTag());
            System.out.println("Status : " + vehicle.getStatus());
            System.out.println("Condition: " + vehicle.getCondition());
            System.out.println();
        }
    }
}
