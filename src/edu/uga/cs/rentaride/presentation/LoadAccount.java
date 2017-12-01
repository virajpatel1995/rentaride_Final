package edu.uga.cs.rentaride.presentation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.*;
import edu.uga.cs.rentaride.logic.LogicLayer;
import edu.uga.cs.rentaride.session.Session;
import edu.uga.cs.rentaride.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Servlet implementation class LoadHomePage
 */
@WebServlet("LoadAccount")
public class LoadAccount extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Configuration cfg;
    static String templateDir = "/WEB-INF/templates";
    String adminPage = "myAccountAdmin.ftl";
    String customerPage = "myAccountUser.ftl";
   // String adminPage = "virajTest.ftl";


    public void init() {
        // Prepare the FreeMarker configuration;
        // - Load templates from the WEB-INF/templates directory of the Web app.
        //
        cfg = new Configuration();
        cfg.setServletContextForTemplateLoading(
                getServletContext(),
                "WEB-INF/templates"
        );
    }


    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // create the data model
        Template resultTemplate = null;
        HttpSession httpSession = null;
        String ssid = null;
        Session session = null;
        String resultTemplateName = "";
        Map<String, Object> root = new HashMap<String, Object>();



        // Session Tracking
        httpSession = request.getSession();
        ssid = (String) httpSession.getAttribute("ssid");
        if (ssid != null) {
            System.out.println("Already have ssid: " + ssid);
            session = SessionManager.getSessionById(ssid);
            System.out.println("Connection: " + session.getConnection());
        } else
            System.out.println("ssid is null");

        session = SessionManager.getSessionById(ssid);
        LogicLayer logicLayer = session.getLogicLayer();
        if(session == null){
            RARError.error( cfg, new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "UTF-8")),"Session expired or illegal; please log in" );
            return;
        }
        User user = session.getUser();
        root.put("username", user.getUserName());

        if (user instanceof Administrator){
            resultTemplateName = adminPage;
            try {
                RentARideParams rentARideParams = logicLayer.getRenARideParams();
                if(rentARideParams != null){
                String mp = (rentARideParams.getMembershipPrice()/100.0)+"";
                String lf = (rentARideParams.getLateFee()/100.0)+"";
                    System.out.println("membership price is " + mp);
                    System.out.println("late fee is " + lf);
                root.put("mprice", mp);
                root.put("latefee", lf);
                }
            } catch (RARException e) {
                e.printStackTrace();
            }

//            Map<String, String> rlMap = new HashMap<>();
            try {
                List<RentalLocation> rentalLocationList = logicLayer.getAllRentalLocations();
                List<String> rlnames = new ArrayList<String>();
                for (RentalLocation rl: rentalLocationList) {
                    rlnames.add(rl.getName());
                }
                List<VehicleType> vehicleTypeList = logicLayer.getAllVehicleTypes();
                List<String> vtnames = new ArrayList<String>();
                for (VehicleType vt: vehicleTypeList) {
                    vtnames.add(vt.getName());
                }
                root.put("rentalLocationList", rlnames);
                root.put("rentalLocationList", rlnames);
                root.put("rentalLocations", rentalLocationList);
                root.put("vehicleTypeList", vtnames);

                List<Vehicle> vehicleList = logicLayer.getAllVehicle();
                root.put("vehicleList", vehicleList);

                List<Customer> customerList = logicLayer.getAllCustomer();
                root.put("customerList",customerList );

                List<Reservation> reservationList = logicLayer.getAllReservation();
                root.put("reservationList", reservationList);

                List<Comment> commentList = logicLayer.getAllComment();
                root.put("commentList", commentList);
                System.out.println(commentList.toString());

            } catch (RARException e) {
                e.printStackTrace();
            }
            
            		root.put("firstName", user.getFirstName());
            		root.put("lastName", user.getLastName());
            		root.put("email", user.getEmail());
            		root.put("address", user.getAddress());
                    
        } else if(user instanceof Customer) {
        	resultTemplateName = customerPage;
            List<RentalLocation> rentalLocationList = null;
            try {
                rentalLocationList = logicLayer.getAllRentalLocations();
            List<String> rlnames = new ArrayList<String>();
            for (RentalLocation rl: rentalLocationList) {
                rlnames.add(rl.getName());
            }
            List<VehicleType> vehicleTypeList = logicLayer.getAllVehicleTypes();
            List<String> vtnames = new ArrayList<String>();
            for (VehicleType vt: vehicleTypeList) {
                vtnames.add(vt.getName());
            }
            root.put("rentalLocationList", rlnames);
            root.put("vehicleTypeList", vtnames);

    		List<Reservation> reservationList = logicLayer.getReservations(user.getUserName());
    		root.put("reservationList",reservationList);
            } catch (RARException e) {
                e.printStackTrace();
            }
        	root.put("firstName", user.getFirstName());
    		root.put("lastName", user.getLastName());
    		root.put("email", user.getEmail());
    		root.put("address", user.getAddress());
    		root.put("card", ((Customer) user).getCreditCardNumber());
    		Date date = ((Customer) user).getCreditCardExpiration();
    		String sdate = new SimpleDateFormat("MM-yyyy").format(date);
    		root.put("expire", sdate);

        }
        // init the template
        try {
            resultTemplate = cfg.getTemplate(resultTemplateName);
        } catch (IOException e) {
            throw new ServletException("LoadAccount.doGet: Can't load template in: " + templateDir + ": " + e.toString());
        }
        BufferedWriter toClient = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), resultTemplate.getEncoding()));
        response.setContentType("text/html; charset=" + resultTemplate.getEncoding());

        try {
            resultTemplate.process(root, toClient);
            toClient.flush();
        } catch (TemplateException e) {
            throw new ServletException("Error while processing FreeMarker template", e);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
