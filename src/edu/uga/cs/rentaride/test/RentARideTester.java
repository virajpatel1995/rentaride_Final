package edu.uga.cs.rentaride.test;

import java.sql.Connection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.*;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.object.impl.ObjectLayerImpl;
import edu.uga.cs.rentaride.persistence.PersistenceLayer;
import edu.uga.cs.rentaride.persistence.impl.DbUtils;
import edu.uga.cs.rentaride.persistence.impl.PersistenceLayerImpl;

public class RentARideTester {

	public static void main(String[]  args){
		Connection  conn = null;
        ObjectLayer objectLayer = null;
        PersistenceLayer persistence = null;

        // get a database connection                                                                     
        try {
            conn = DbUtils.connect();
        }
        catch(Exception seq) {
            System.err.println( "ReadTest: Unable to obtain a database connection" );
        }

        if( conn == null ) {
            System.out.println( "ReadTest: failed to connect to the database" );
            return;
        }

        try {


            // connect the ObjectModel module to the Persistence module
            objectLayer = new ObjectLayerImpl();
            // obtain a reference to Persistence module and connect it to the ObjectModel
            persistence = new PersistenceLayerImpl( conn, objectLayer );
            objectLayer.setPersistence(persistence);
      	         	

       	
	    	/*
	    	 * 
	    	 *  Create 2 administrators.  WORKS!!!
	    	 *  
	    	 *  
	    	 *  */

        	Administrator admin1 = objectLayer.createAdministrator("S", "Patelll", "admin1", "pwdssss", "shepogden@uga.edu", "1 Dawg Drive", new Date(System.currentTimeMillis()));
        	Administrator admin2 = objectLayer.createAdministrator("J", "Patelll", "admin2", "pws", "virajpatel@uga.edu", "2 Dawg Drive", new Date(System.currentTimeMillis()));
        	persistence.storeAdministrator(admin1);
        	persistence.storeAdministrator(admin2);



        	/*
       	 *
       	 *  Create 2 rental locations. WORKS!!!
       	 *
       	 *
       	 *  */
          
// junwei_branch
//RentalLocation rentalLocation1 = objectLayer.createRentalLocation("xxxxxx", "33 walmart road", 50);
//	RentalLocation rentalLocation2 = objectLayer.createRentalLocation("bbbbbb", "55 mcdonalds road", 10);
       	
      	RentalLocation rentalLocation1 = objectLayer.createRentalLocation("xxxBxxx", "33 walmart road", 50);
      	RentalLocation rentalLocation2 = objectLayer.createRentalLocation("bbbCbbb", "55 mcdonalds road", 10);
          
      	persistence.storeRentalLocation(rentalLocation1);
      	persistence.storeRentalLocation(rentalLocation2);


		/*
	    	 *
	    	 *  Create 2 vehicle types, each with 2 different hourly prices. WORKS!!!!

	    	 *
	    	 *
	    	 *  */
          
// junwei_branch
//	VehicleType vehicleType1 = objectLayer.createVehicleType("bigbigTruck");
//	HourlyPrice hourlyPrice1 = objectLayer.createHourlyPrice(101, 5, vehicleType1);

  	
        	VehicleType vehicleType1 = objectLayer.createVehicleType("bigbigTruck2");
        	HourlyPrice hourlyPrice1 = objectLayer.createHourlyPrice(1019, 7, vehicleType1);

        	persistence.storeVehicleType(vehicleType1);
        	persistence.storeHourlyPrice(hourlyPrice1);

          //junwei_branch
        	//VehicleType vehicleType2 = objectLayer.createVehicleType("bigbigVan");
        	//HourlyPrice hourlyPrice2 = objectLayer.createHourlyPrice(202, 3, vehicleType2);


        	VehicleType vehicleType2 = objectLayer.createVehicleType("bigbigVan2");
        	HourlyPrice hourlyPrice2 = objectLayer.createHourlyPrice(2229, 8, vehicleType2);
        	
        	persistence.storeVehicleType(vehicleType2);
        	persistence.storeHourlyPrice(hourlyPrice2);


        	/*
	    	 *
	    	 *  Create 4 vehicles, 2 of one vehicle type and 2 of the other, assigned to the 2 rental
			locations (2 vehicles per location but with different vehicle types at each location). WORKS!!!!
	    	 *
	    	 *
	    	 *  */


        Vehicle vehicle1 = objectLayer.createVehicle("GMC", "Sierra", 1990, "15ZB35", 100000, new Date(System.currentTimeMillis()), vehicleType1, rentalLocation1, VehicleCondition.GOOD, VehicleStatus.INLOCATION);
        	Vehicle vehicle2 = objectLayer.createVehicle("Honda", "Odyssey", 1998, "1SH5F25", 80000, new Date(System.currentTimeMillis()), vehicleType2, rentalLocation1, VehicleCondition.GOOD, VehicleStatus.INLOCATION);
        	Vehicle vehicle3 = objectLayer.createVehicle("Chevrolet", "Silverado", 2015, "8FJFF445", 30000, new Date(System.currentTimeMillis()), vehicleType1, rentalLocation2, VehicleCondition.GOOD, VehicleStatus.INLOCATION);
        	Vehicle vehicle4 = objectLayer.createVehicle("Toyota", "Sienna", 2000, "MDP5325F", 60000, new Date(System.currentTimeMillis()), vehicleType2, rentalLocation2, VehicleCondition.GOOD, VehicleStatus.INLOCATION);

        	persistence.storeVehicle(vehicle1);
        	persistence.storeVehicle(vehicle2);
        	persistence.storeVehicle(vehicle3);
        	persistence.storeVehicle(vehicle4);


        	/*
	    	 *
	    	 *  Create 2 Customers. WORKS!!!!!
	    	 *
	    	 *
	    	 *  */
  //junwei_branch
  //Customer customer1 = objectLayer.createCustomer("Chase22 ", "Williams", "chasewilliams", "chasepassword", "chase@uga.edu", "3 Dawg Drive", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), "Georgia", "51DGf52G", "10484835823924", new Date(System.currentTimeMillis()), UserStatus.ACTIVE);
  //Customer customer2 = objectLayer.createCustomer("Josh", "Dawson", "joshdawson", "joshpassword", "josh@uga.edu", "4 Dawg Drive", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), "Georgia", "81FH52F6H", "5471975974927459", new Date(System.currentTimeMillis()), UserStatus.ACTIVE);

        	
          Customer customer1 = objectLayer.createCustomer("Chase32 ", "Williams", "chasewilliams", "chasepassword", "chase@uga.edu", "3 Dawg Drive", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), "Georgia", "51DGf52G", "10484835823924", new Date(System.currentTimeMillis()), UserStatus.ACTIVE);
          Customer customer2 = objectLayer.createCustomer("Josh32", "Dawson", "joshdawson", "joshpassword", "josh@uga.edu", "4 Dawg Drive", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), "Georgia", "81FH52F6H", "5471975974927459", new Date(System.currentTimeMillis()), UserStatus.ACTIVE);

    		  persistence.storeCustomer(customer1);
    		  persistence.storeCustomer(customer2);




        	/*
	    	 *
	    	 *  For each customer, create 2 reservations, each with a vehicle type and a rental location. WORKS!!!!
	    	 *
	    	 *
	    	 *  */



     	Reservation reservation1 = objectLayer.createReservation(new Date(System.currentTimeMillis()), 3, vehicleType1, rentalLocation1, customer1);
        	Reservation reservation2 = objectLayer.createReservation(new Date(System.currentTimeMillis()), 4, vehicleType2, rentalLocation1, customer1);
        	Reservation reservation3 = objectLayer.createReservation(new Date(System.currentTimeMillis()), 5, vehicleType1, rentalLocation2, customer2);
        	Reservation reservation4 = objectLayer.createReservation(new Date(System.currentTimeMillis()), 5, vehicleType2, rentalLocation2, customer2);

        	persistence.storeReservation(reservation1);
        	persistence.storeReservation(reservation2);
        	persistence.storeReservation(reservation3);
        	persistence.storeReservation(reservation4);




        	/*
	    	 *
	    	 *  For two of the created reservations (one per customer), create corresponding rentals,
	    	 *  each involving a vehicle (with a correct vehicle type) and a comment. Do not calculate the         WORKS!!!!
            rental charges or late fees but include some “made-up” values.
	    	 *
	    	 *
	    	 *  */


        	Rental rental1 = objectLayer.createRental(new Date(System.currentTimeMillis()), reservation1, vehicle1);
        	Rental rental2 = objectLayer.createRental(new Date(System.currentTimeMillis()),reservation2, vehicle2);


        	Comment comment1 = objectLayer.createComment("Great carrrr", new Date(System.currentTimeMillis()), rental1, customer1);
        	Comment comment2 = objectLayer.createComment("Best car everrrr", new Date(System.currentTimeMillis()), rental2, customer2);

        	rental1.setComment(comment1);
        	rental2.setComment(comment2);

        	persistence.storeRental(rental1);
        	persistence.storeRental(rental2);

        	persistence.storeComment(comment1);
        	persistence.storeComment(comment2);

// junwei_branch
          
            RentARideParams rentARideParams = objectLayer.createRentARideParams();
            rentARideParams.setMembershipPrice(1000);
            rentARideParams.setLateFee(500);

            persistence.storeRentARideConfig(rentARideParams);


        	
  /**************************************************************************
   * 
   *************************** DELETE ***************************************
   *  
   *  
   **************************************************************************/
       
        objectLayer.deleteComment(comment2);
       	objectLayer.deleteComment(comment1);

     	objectLayer.deleteRental(rental2);
      	objectLayer.deleteRental(rental1);
       	
        	objectLayer.deleteReservation(reservation4);
        	objectLayer.deleteReservation(reservation3);
        	objectLayer.deleteReservation(reservation2);
        	objectLayer.deleteReservation(reservation1);
    
        	objectLayer.deleteCustomer(customer2);
        	objectLayer.deleteCustomer(customer1);
        	
        	objectLayer.deleteVehicle(vehicle4);
        	objectLayer.deleteVehicle(vehicle3);
        	objectLayer.deleteVehicle(vehicle2);
        	objectLayer.deleteVehicle(vehicle1);

        	objectLayer.deleteHourlyPrice(hourlyPrice2);
        	objectLayer.deleteVehicleType(vehicleType2);
        	objectLayer.deleteHourlyPrice(hourlyPrice1);
        	objectLayer.deleteVehicleType(vehicleType1);
        	
        	objectLayer.deleteRentalLocation(rentalLocation2);
        	objectLayer.deleteRentalLocation(rentalLocation1);

        	objectLayer.deleteAdministrator(admin2);
        	objectLayer.deleteAdministrator(admin1);
        	
        	
        	
        	
        	
        }
        catch( RARException ce)
        {
            System.err.println( "RARException: " + ce );
        }
        catch( Exception e)
        {
            System.out.flush();
            e.printStackTrace();
            System.err.println( "Exception: " + e );
        }
        finally {
            // close the connection!!!                                                                   
            try {
                conn.close();
            }
            catch( Exception e ) {
                System.err.println( "Exception: " + e );
            }
        }
	}
}
