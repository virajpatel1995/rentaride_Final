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

import edu.uga.cs.rentaride.entity.impl.RentARideParamsImpl;
import edu.uga.cs.rentaride.logic.LogicLayer;
import edu.uga.cs.rentaride.session.Session;
import edu.uga.cs.rentaride.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import edu.uga.cs.rentaride.logic.*;

import edu.uga.cs.rentaride.RARException;

@WebServlet("UpdateProfile")

public class UpdateProfile
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
        LogicLayer     logicLayer = null;
        Session        session;
        String         ssid;
        Map<String,Object> root = new HashMap<String,Object>();
        String retMessage = "";
        long customerId = 0;

        
        String fName = "";
        String lName = "";
        String email = "";
        String address = "";
        
       String creditCardNum = "";
       String expirationDate = "";
        
        

        // Session Tracking
        httpSession = req.getSession();
        ssid = (String) httpSession.getAttribute("ssid");
//        if (ssid != null) {
//            System.out.println("Already have ssid: " + ssid);
//            session = SessionManager.getSessionById(ssid);
//            System.out.println("Connection: " + session.getConnection());
//        } else
//            System.out.println("ssid is null");
//
        session = SessionManager.getSessionById(ssid);
        if(session == null){
            RARError.error( cfg, new BufferedWriter(new OutputStreamWriter(res.getOutputStream(), "UTF-8")),"Session expired or illegal; please log in" );
            return;
        }
        logicLayer = session.getLogicLayer();
        fName = req.getParameter( "fName" );
        lName = req.getParameter( "lName" );
        email = req.getParameter( "email" );
        address = req.getParameter( "address" );
        creditCardNum = req.getParameter("card");
        expirationDate = req.getParameter("expire");
        String msg = null;

        try{
            
        	
        	customerId = logicLayer.updateCustomer(	session.getUser().getUserName(), fName, lName, email, address, creditCardNum, expirationDate);
            msg = "Your Profile has been successfully updated";
        }catch(Exception e) {
            msg = "Something goes wrong";
            e.printStackTrace();
        }

        res.setContentType("text/plain");
        res.getWriter().write(msg);

    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        doPost(req,res);
    }
}
