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

@WebServlet("CancelMembership")

public class CancelMembership
    extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
    static  String         templateDir = "WEB-INF/templates";
    static  String         resultTemplateName = "index.html";

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
        LogicLayer     logicLayer = null;
        HttpSession    httpSession;
        Session        session = null;
        String         ssid;
        Map<String,Object> root = new HashMap<String,Object>();
        String retMessage = "";
        User user = null;

        // Load templates from the WEB-INF/templates directory of the Web app.
        //
        try {
            resultTemplate = cfg.getTemplate( resultTemplateName );
        }
        catch (IOException e) {
            throw new ServletException(
                    "Can't load template in: " + templateDir + ": " + e.toString());
        }

        
        httpSession = req.getSession();
        if( httpSession != null ) {
            ssid = (String) httpSession.getAttribute( "ssid" );
            if( ssid != null ) {
                System.out.println( "Already have ssid: " + ssid );
                session = SessionManager.getSessionById( ssid );
                user = session.getUser();
                if( session == null ) {
                    RARError.error( cfg, toClient, "Session expired or illegal; please log in" );
                    return; 
                }
                logicLayer = session.getLogicLayer();
                try {
                    logicLayer.logout( ssid );
                    httpSession.removeAttribute("ssid");
                    httpSession.invalidate();
                    System.out.println( "Invalidated http session" );
                }
                catch( RARException e ) {
                    e.printStackTrace();
                }
            }
            else
                System.out.println( "ssid is null" );
        }
        else
            System.out.println( "No http session" );
        // Prepare the HTTP response:
        // - Use the charset of template for the output
        // - Use text/html MIME-type
        //
        toClient = new BufferedWriter(
                new OutputStreamWriter( res.getOutputStream(), resultTemplate.getEncoding() )
                );

        res.setContentType("text/html; charset=" + resultTemplate.getEncoding());




        logicLayer = session.getLogicLayer();
        if( logicLayer == null ) {
        		RARError.error( cfg, toClient, "Session expired or illegal; please log in" );
            return;
        }
            try {
				logicLayer.CancelMembership(user);
			} catch (RARException e1) {
				e1.printStackTrace();
			}

            root.put("message", "You have Cancelled your membership from Rent A Ride");

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

