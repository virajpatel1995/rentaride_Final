package edu.uga.cs.rentaride.presentation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.User;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.logic.LogicLayer;
import edu.uga.cs.rentaride.session.Session;
import edu.uga.cs.rentaride.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Servlet implementation class LoadHomePage
 */
@WebServlet("LoadMakeReservation")
public class LoadMakeReservation extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Configuration cfg;
    static String templateDir = "/WEB-INF/templates";
    String destination = "myAccountUser.ftl";



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
        String resultTemplateName = destination;
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
        if(session == null){
            RARError.error( cfg, new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "UTF-8")),"Session expired or illegal; please log in" );
            return;
        }
        LogicLayer logicLayer = session.getLogicLayer();

        User user = session.getUser();
        root.put("username", user.getUserName());

        try {
            List<RentalLocation> rentalLocationList = logicLayer.getAllRentalLocations();
            List<VehicleType> vehicleTypeList = logicLayer.getAllVehicleTypes();
            List<String> rlname = new ArrayList<>();
            List<String> vtname = new ArrayList<>();
            rentalLocationList.forEach(rentalLocation -> rlname.add(rentalLocation.getName()));
            vehicleTypeList.forEach(vehicleType -> vtname.add(vehicleType.getName()));

            root.put("rentalLocations", rlname);
            root.put("vehicleTypeList", vtname);
        } catch (RARException e) {
            e.printStackTrace();
        }


        // init the template
        try {
            resultTemplate = cfg.getTemplate(resultTemplateName);
        } catch (IOException e) {
            throw new ServletException("LoadReservation.doGet: Can't load template in: " + templateDir + ": " + e.toString());
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
