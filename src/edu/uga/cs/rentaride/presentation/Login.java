package edu.uga.cs.rentaride.presentation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;//
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import edu.uga.cs.rentaride.logic.impl.LoginCtrl;
import edu.uga.cs.rentaride.logic.LogicLayer;
import edu.uga.cs.rentaride.session.Session;
import edu.uga.cs.rentaride.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.User;


@WebServlet("Login")
public class Login 
    extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    static  String  templateDir = "WEB-INF/templates";



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
        String         username = null;
        String         password = null;
        String         ssid = null;
        String msg = null;
        Session        session = null;
        LogicLayer     logicLayer = null;
        String  resultTemplateName = "index.ftl";
        
        httpSession = req.getSession();
        ssid = (String) httpSession.getAttribute( "ssid" );
        if( ssid != null ) {
            System.out.println( "Already have ssid: " + ssid );
            session = SessionManager.getSessionById( ssid );
            System.out.println( "Connection: " + session.getConnection() );
        }
        else
            System.out.println( "ssid is null" );
        // Load templates from the WEB-INF/templates directory of the Web app.
        //

        try {
            resultTemplate = cfg.getTemplate( resultTemplateName );
        }
        catch (IOException e) {
            throw new ServletException( "Login.doPost: Can't load template in: " + templateDir + ": " + e.toString());
        }
        // Prepare the HTTP response:
        // - Use the charset of template for the output
        // - Use text/html MIME-type
        //
        toClient = new BufferedWriter( new OutputStreamWriter( res.getOutputStream(), resultTemplate.getEncoding() ) );
        res.setContentType("text/html; charset=" + resultTemplate.getEncoding());

        if( session == null ) {
            try {
                session = SessionManager.createSession();
            }
            catch ( Exception e ) {
                RARError.error( cfg, toClient, e );
                return;
            }
        }
        
        logicLayer = session.getLogicLayer();
        
        // Get the parameters
        //
        username = req.getParameter( "username" );
        password = req.getParameter( "passwordlogin" );

        if( username == null || password == null ) {
            RARError.error( cfg, toClient, "Missing user name or password" );
            return;
        }
        
        try {          
            ssid = logicLayer.login( session, username, password );
            System.out.println( "Obtained ssid: " + ssid );
            httpSession.setAttribute( "ssid", ssid );
            User user = session.getUser();
            System.out.println( "Connection: " + session.getConnection() );
        } 
        catch( RARException e) {
        		if(e.getMessage().equals("User is Cancelled")) {

                    resultTemplateName = "loginRegister.ftl";
                    msg = "User has cancelled their account";
                    resultTemplate = cfg.getTemplate( resultTemplateName );
                    toClient = new BufferedWriter( new OutputStreamWriter( res.getOutputStream(), resultTemplate.getEncoding() ) );
                    res.setContentType("text/html; charset=" + resultTemplate.getEncoding());
        			
        		}else
        			if(e.getMessage().equals("User is Terminated")) {

                        resultTemplateName = "loginRegister.ftl";
                        msg = "An administrator has terminated your account \\(>w<)/";
                        resultTemplate = cfg.getTemplate( resultTemplateName );
                        toClient = new BufferedWriter( new OutputStreamWriter( res.getOutputStream(), resultTemplate.getEncoding() ) );
                        res.setContentType("text/html; charset=" + resultTemplate.getEncoding());
            			
        		}
        		else {
            resultTemplateName = "loginRegister.ftl";
            msg = "Invalid username or Password";
            resultTemplate = cfg.getTemplate( resultTemplateName );
            toClient = new BufferedWriter( new OutputStreamWriter( res.getOutputStream(), resultTemplate.getEncoding() ) );
            res.setContentType("text/html; charset=" + resultTemplate.getEncoding());
        		}
        } catch ( Exception e ) {
            RARError.error( cfg, toClient, e );
        		return;
        }
        


       


       

       

    

        // Setup the data-model
        //
        Map<String, String> root = new HashMap<String, String>();

        // Build the data-model
        //
        root.put( "username", username );
        httpSession.setAttribute( "username", username );

        root.put( "message", msg );

        // Merge the data-model and the template
        //
        try {
            resultTemplate.process( root, toClient );
            toClient.flush();
        } 
        catch (TemplateException e) {
            throw new ServletException( "Error while processing FreeMarker template", e);
        }

        toClient.close();
    }
    
    
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
    		doPost(req,res);
}
    
    
    
}

