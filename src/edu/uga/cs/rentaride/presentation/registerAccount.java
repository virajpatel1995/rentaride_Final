package edu.uga.cs.rentaride.presentation;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uga.cs.rentaride.presentation.templateProcessor;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.logic.LogicLayer;
import edu.uga.cs.rentaride.session.Session;
import edu.uga.cs.rentaride.session.SessionManager;
import freemarker.template.Configuration;
//import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.*;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * Servlet implementation class signin
 */
@WebServlet("/CreateAccount")
public class registerAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Configuration cfg = null;
	private templateProcessor templateProcessor = null;
	
	//This will set the LogicLayer to null
	private LogicLayer logicLayer = null;
	
	//This is the folder it will return too
	private String templateDir = "/WEB-INF/CreateAccountTemplates";

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerAccount() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {

		// Create your Configuration instance, and specify if up to what FreeMarker
		// version (here 2.3.25) do you want to apply the fixes that are not 100%
		// backward-compatible. See the Configuration JavaDoc for details.
		cfg = new Configuration();

		// Specify the source where the template files come from.
		//cfg.setServletContextForTemplateLoading(getServletContext(), templateDir);

		// Sets how errors will appear.
		// During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
		// This handler outputs the stack trace information to the client, formatting it so 
		// that it will be usually well readable in the browser, and then re-throws the exception.
		//		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);

		// Don't log exceptions inside FreeMarker that it will thrown at you anyway:
		// Specifies if TemplateException-s thrown by template processing are logged by FreeMarker or not. 
		//		cfg.setLogTemplateExceptions(false);
		templateProcessor = new templateProcessor(cfg, getServletContext(), templateDir);

	}

	public void registerUser1(HttpServletRequest request, HttpServletResponse response){
		
		//This is the file it will sent after it stores all the inputs 
		templateProcessor.setTemplate("CreateFormTwo.ftl");
		
		//Getting the user value from the template
		String status = "";
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		//Setting the session to null
		HttpSession    httpSession = null;
        Session        session = null;
        String         ssid;

        //Getting the http session and store it into the ssid
		httpSession = request.getSession();
		ssid = (String) httpSession.getAttribute( "ssid" );
		
		//Here it will get the existing id
		if( ssid != null ) {

            session = SessionManager.getSessionById( ssid );
        }

		
		//Here it will create the session id 
		if( session == null ){
			
			try {
				
				session = SessionManager.createSession();
			} catch ( Exception e ){
				
				status = e.toString();
				templateProcessor.setTemplate("SigninCreateForm.ftl");
				templateProcessor.addToRoot("status", status);
				templateProcessor.processTemplate(response);
			}
		}
		
		//We are setting the attribute from user into the session
		httpSession.setAttribute("firstName", firstName);
		httpSession.setAttribute("lastName", lastName);
		httpSession.setAttribute("email", email);
		httpSession.setAttribute("password", password);
		
		logicLayer = session.getLogicLayer();
		templateProcessor.addToRoot("status", status);
		
		//Here it will process the response
		templateProcessor.processTemplate(response);
    }
	
	
	public void registerUser2(HttpServletRequest request, HttpServletResponse response){

		//This is the file it will sent after it stores all the inputs 
		templateProcessor.setTemplate("SigninCreateForm.ftl");
		
		//Getting the user value from the template
		String status = "";
		String licenseNumber = request.getParameter("licenseNumber");
		String creditCard = request.getParameter("creditCard");
		String expDate = request.getParameter("expDate");
		String address = request.getParameter("address");
		String state = request.getParameter("state");
		String zipCode = request.getParameter("zipCode");
		
		//Setting the session to null
		HttpSession    httpSession = null;
        Session        session = null;
        String         ssid;
        
        //Getting the http session and store it into the ssid
        httpSession = request.getSession();
		ssid = (String) httpSession.getAttribute( "ssid" );
		
		//Here it will get the existing id
		if( ssid != null ) {
          
            session = SessionManager.getSessionById( ssid );
        }
		
		//Here it will create the session id 
		if( session == null ){
			try {
				
				session = SessionManager.createSession();
			} catch ( Exception e ){
				
				status = e.toString();
				templateProcessor.setTemplate("SigninCreateForm.ftl");
				templateProcessor.addToRoot("status", status);
				templateProcessor.processTemplate(response);
			}
		}
		
		//Here we will retrieve the attribute which was stored in previous form into the session
		String fname = (String) httpSession.getAttribute("firstName");
		String lname = (String) httpSession.getAttribute("lastName");
		String email = (String) httpSession.getAttribute("email");
		String password = (String) httpSession.getAttribute("password");

		logicLayer = session.getLogicLayer();
		
		//Passing the all the values from user into the logic layer
		long num;
		try {
			
			num = logicLayer.registerAccount(fname, lname, email, password, license, card, exp, add, state, zip);
		} catch (RARException e) {
			
			e.printStackTrace();
		} finally {
			
			templateProcessor.addToRoot("status", status);
			templateProcessor.processTemplate(response);
		}
    }
	
	public void toSigninMenu(HttpServletResponse response) {

		//This is the file it will sent after it stores all the inputs 
		templateProcessor.setTemplate("SigninCreateForm.ftl");
		templateProcessor.processTemplate(response);
	} // toSigninOne
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//This will check the user state in sign in or create account
		if (request.getParameter("next") != null) registerUser1(request, response);
		else if (request.getParameter("register") != null) registerUser2(request, response);
		else toSigninMenu(response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}