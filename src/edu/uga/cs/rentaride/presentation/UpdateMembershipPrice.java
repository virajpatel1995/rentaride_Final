package edu.uga.cs.rentaride.presentation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uga.cs.rentaride.entity.User;
import edu.uga.cs.rentaride.logic.LogicLayer;
import edu.uga.cs.rentaride.session.Session;
import edu.uga.cs.rentaride.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import edu.uga.cs.rentaride.RARException;

@WebServlet("UpdateMembershipPrice")

public class UpdateMembershipPrice
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
        Template       resultTemplate = null;
        BufferedWriter toClient = null;
        String	       name = null;
        String	       addr = null;
        String capacityS = null;
        int      	   capacity = 0;
        LogicLayer     logicLayer = null;
        HttpSession    httpSession;
        Session        session;
        String         ssid;
        long			rentalLocationId = 0;
        Map<String,Object> root = new HashMap<String,Object>();
        String retMessage = "";

        // Load templates from the WEB-INF/templates directory of the Web app.
        //
        try {
            resultTemplate = cfg.getTemplate( resultTemplateName );
        }
        catch (IOException e) {
            throw new ServletException(
                    "Can't load template in: " + templateDir + ": " + e.toString());
        }

        // Prepare the HTTP response:
        // - Use the charset of template for the output
        // - Use text/html MIME-type
        //
        toClient = new BufferedWriter(
                new OutputStreamWriter( res.getOutputStream(), resultTemplate.getEncoding() )
                );

        res.setContentType("text/html; charset=" + resultTemplate.getEncoding());



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
        root.put("username", user.getUserName());

        logicLayer = session.getLogicLayer();
        if( logicLayer == null ) {
        		RARError.error( cfg, toClient, "Session expired or illegal; please log in" );
            return;
        }

        // Get the form parameters
        //
        name = req.getParameter( "locationName" );
        addr = req.getParameter( "locationAddress" );
        String zip = req.getParameter( "locationZip" );
        String state = req.getParameter( "locationState" );
        capacityS = req.getParameter( "locationCapacity" );

        try{
            capacity = Integer.valueOf(capacityS);
            rentalLocationId = logicLayer.createRentalLocation( name, addr + " " + state + " " + zip, capacity );
        }catch(Exception e) {
        		capacity = 0;
        }


        try {
            resultTemplate.process( root, toClient );
            toClient.flush();
        } 
        catch (TemplateException e) {
            throw new ServletException( "Error while processing FreeMarker template", e);
        }

        toClient.close();

  }
}

