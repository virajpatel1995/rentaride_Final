// Gnu Emacs C++ mode:  -*- Java -*-
//
// Class:       CreateClub
//
// Type:        Servlet
//
// K.J. Kochut
//
//
//


package edu.uga.cs.rentaride.presentation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uga.cs.rentaride.entity.*;
import edu.uga.cs.rentaride.logic.LogicLayer;
import edu.uga.cs.rentaride.session.Session;
import edu.uga.cs.rentaride.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import edu.uga.cs.rentaride.RARException;



// Servlet class CreateClub
//
// doPost starts the execution of the Create Club Use Case
//
//   parameters:
//
//	club_name    string
//	club_address string
//      founder_id   long (as a string)
//
@WebServlet("UpdateVehicle")

public class UpdateVehicle
    extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
    static  String         templateDir = "WEB-INF/templates";
    static  String         resultTemplateName = "myAccountAdmin.ftl";

    private Configuration  cfg; 

    public void init() 
    {
        // Prepare the FreeMarker configuration;
        // - Load templates from the WEB-INF/templates directory of the Web app.
        //
        cfg = new Configuration();
        cfg.setServletContextForTemplateLoading(
                getServletContext(), 
                "WEB-INF/templates"
                );
    }

    public void doPost( HttpServletRequest req, HttpServletResponse res )
            throws ServletException, IOException
    {
        LogicLayer     logicLayer = null;
        HttpSession    httpSession;
        Session        session;
        String         ssid;
        Map<String,Object> root = new HashMap<String,Object>();
        String retMessage = "";

        // Session Tracking
        httpSession = req.getSession();
        ssid = (String) httpSession.getAttribute("ssid");
        if (ssid != null) {
            System.out.println("Already have ssid: " + ssid);
            session = SessionManager.getSessionById(ssid);
            System.out.println("Connection: " + session.getConnection());
        } else
            System.out.println("ssid is null");
        session = SessionManager.getSessionById(ssid);
        if(session == null){
            RARError.error( cfg, new BufferedWriter(new OutputStreamWriter(res.getOutputStream(), "UTF-8")),"Session expired or illegal; please log in" );
            return;
        }
        User user = session.getUser();
        logicLayer = session.getLogicLayer();
        if( logicLayer == null ) {
            RARError.error( cfg, new BufferedWriter(new OutputStreamWriter(res.getOutputStream(), "UTF-8")),"LogicLayer is null" );
            return;
        }
        String vehicleId =  null;
        String localMake =  null;
        String localModel =  null;
        String localYear =  null;
        String localRegistrationTag =  null;
        String localMileage =  null;
        String localLastServiced =  null;
        String localStatus =  null;
        String localCondition =  null;
        String localRentalLocation =  null;

        vehicleId = req.getParameter("pid");
        localMake = req.getParameter("pMake");
        localModel = req.getParameter("pModel");
        localYear = req.getParameter("pYear");
        localRegistrationTag = req.getParameter("pRegistrationTag");
        localMileage = req.getParameter("pMileage");
        localLastServiced = req.getParameter("pLastServiced");
        localStatus = req.getParameter("pStatus");
        localCondition = req.getParameter("pCondition");
        localRentalLocation = req.getParameter("pRentalLocation");
        System.out.println("Parsing: " +vehicleId +
                " " + localMake +
                " " + localModel +
                " " + localYear +
                " " + localRegistrationTag +
                " " + localMileage +
                " " + localLastServiced +
                " " + localStatus +
                " " + localCondition +
                " " + localRentalLocation
        );
        try{
            long vid = Long.parseLong(vehicleId);
            Vehicle vehicle = logicLayer.getVehicleById(vid);
            vehicle.setMake (localMake);
            vehicle.setModel (localModel);
            vehicle.setYear (Integer.parseInt(localYear.replace(",","")));
            vehicle.setRegistrationTag (localRegistrationTag);
            vehicle.setMileage (Integer.parseInt(localMileage.replace(",","")));


            try {
                vehicle.setLastServiced((new SimpleDateFormat("MMM d,yyyy").parse(localLastServiced)));
            }catch(ParseException e) {
                System.out.println("Can't parse this date format: " + localLastServiced);
                throw new RARException("Date not valid: "+ localLastServiced);
            }


            vehicle.setStatus (VehicleStatus.valueOf(localStatus));
            vehicle.setCondition (VehicleCondition.valueOf(localCondition));
            vehicle.setRentalLocation (logicLayer.getRentalLocationByName(localRentalLocation));

            logicLayer.updateVehicle(vehicle);
            retMessage = "Update successfully";
        }catch(RARException e) {
            e.printStackTrace();
            retMessage = e.toString();
        }
        res.setContentType("text/plain");
        res.getWriter().write(retMessage);


//        try{
//            mileage = Integer.valueOf(mileageS);
//            String Test = "True";
//            if (maintenanceS.equalsIgnoreCase(Test)) maintenance = true;
//            vehicleId = logicLayer.UpdateVehicle(mileage, tag, location, maintenance);
//        }catch(Exception e) {
//        	e.printStackTrace();
//        }
//
//
//        try {
//            resultTemplate.process( root, toClient );
//            toClient.flush();
//        }
//        catch (TemplateException e) {
//            throw new ServletException( "Error while processing FreeMarker template", e);
//        }
//
//        toClient.close();

  }
}

