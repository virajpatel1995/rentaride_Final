package edu.uga.cs.rentaride.presentation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.User;
import edu.uga.cs.rentaride.logic.LogicLayer;
import edu.uga.cs.rentaride.session.Session;
import edu.uga.cs.rentaride.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Servlet implementation class LoadHomePage
 */
@WebServlet("/LoadHomePage")
public class LoadHomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Configuration cfg;
    static  String          templateDir = "/WEB-INF/templates";
    static  String          resultTemplateName = "myAccountUser.ftl";
    
    
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
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Template    resultTemplate = null;
		 HttpSession    httpSession = null;
		 String         ssid = null;
	     Session        session = null;
	        // load the template
	        try {
	            resultTemplate = cfg.getTemplate( resultTemplateName );
	        } 
	        catch (IOException e) {
	            throw new ServletException( "LoadHomePage.doGet: Can't load template in: " + templateDir + ": " + e.toString());
	        }
	                
	        
	        
	        // set up the response writer
	        response.setContentType("text/html;charset=UTF-8");
	        PrintWriter toClient = response.getWriter();

	   


	        // create the data model
	        Map<String, Object> root = new HashMap<String, Object>();
	        
	        // Session Tracking
			httpSession = request.getSession();
			ssid = (String) httpSession.getAttribute( "ssid" );
	        if( ssid != null ) {
	            System.out.println( "Already have ssid: " + ssid );
	            session = SessionManager.getSessionById( ssid );
	            System.out.println( "Connection: " + session.getConnection() );
	        }
	        else
	            System.out.println( "ssid is null" );
			if(httpSession.getAttribute("user") != null) {
			User user = (User) httpSession.getAttribute( "username");
		    root.put("username", user.getUserName());
	       

			}
	        // connect template with data model
	        try {
	            resultTemplate.process( root, toClient );
	            toClient.flush();
	        } 
	        catch (TemplateException e) {
	            throw new ServletException( "Error while processing FreeMarker template", e);
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
