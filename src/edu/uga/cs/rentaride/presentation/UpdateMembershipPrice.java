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

import edu.uga.cs.rentaride.entity.RentARideParams;
import edu.uga.cs.rentaride.entity.User;
import edu.uga.cs.rentaride.session.Session;
import edu.uga.cs.rentaride.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import edu.uga.cs.rentaride.logic.*;

import edu.uga.cs.rentaride.RARException;

@WebServlet("UpdateMembershipPrice")

public class UpdateMembershipPrice
    extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
    static  String         templateDir = "WEB-INF/templates";
  //  static  String         resultTemplateName = "myAccountAdmin.ftl";

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
        HttpSession    httpSession = null;
        BufferedWriter toClient = null;
        String	       priceS = "";
       LogicLayer     logicLayer = null;
        String         ssid = null;
        String	msg = null;
        String  resultTemplateName = "index.ftl";
        Session session = null;
        
        httpSession = req.getSession();
        ssid = (String) httpSession.getAttribute("ssid");
        
  System.out.println("I'm here!");
       
		if (ssid != null) {
            session = SessionManager.getSessionById(ssid);
        }
		
		if( session == null ){
            try {
                session = SessionManager.createSession();
            } catch ( Exception e ){
                RARError.error( cfg, toClient, e );
            }
        }
		
        logicLayer = session.getLogicLayer();
        
        try {
            resultTemplate = cfg.getTemplate( resultTemplateName );
        }
        catch (IOException e) {
            throw new ServletException( "ResetPassword.doPost: Can't load template in: " + templateDir + ": " + e.toString());
        }

        // Get the form parameters
        
        priceS = req.getParameter( "membershipPrice" );
        System.out.println(priceS);
        double price = Double.parseDouble(priceS);
        System.out.println("Testinggggggg " + price);

        try {
        	double membershipPrice = logicLayer.membershipPrice(price);
          //  System.out.println("Heloooooooooooooo " + price);

//            if (membershipPrice != null) { 
//                msg = "Invalid price";
//            } else {
                logicLayer.membershipPrice(membershipPrice);
                msg = "Price Successfully Updated";
           // }
        }
        catch( RARException e) {

        } catch ( Exception e ) {
            RARError.error( cfg, toClient, e );
            return;
        }

        res.setContentType("text/plain");
        res.getWriter().write(msg);

    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        doPost(req,res);
    }
}



