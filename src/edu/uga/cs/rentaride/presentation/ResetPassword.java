package edu.uga.cs.rentaride.presentation;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.User;
import edu.uga.cs.rentaride.logic.LogicLayer;
import edu.uga.cs.rentaride.session.Session;
import edu.uga.cs.rentaride.session.SessionManager;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Junwei on 11/15/2017.
 */
    @WebServlet("ResetPassword")
public class ResetPassword extends HttpServlet
    {
        private static final long serialVersionUID = 1L;

        static  String  templateDir = "WEB-INF/templates";

        private Configuration cfg;

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

        public void doPost(HttpServletRequest req, HttpServletResponse res )
                throws ServletException, IOException
        {
            Template resultTemplate = null;
            HttpSession httpSession = null;
            BufferedWriter toClient = null;
            String         username = null;
            String         email = null;
            String         password = null;
            String         ssid = null;
            String msg = null;
            Session session = null;
            LogicLayer logicLayer = null;
            String  resultTemplateName = "index.ftl";

            //Getting the http session and store it into the ssid
            httpSession = req.getSession();
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
            // Prepare the HTTP response:
            // - Use the charset of template for the output
            // - Use text/html MIME-type
            //
//            toClient = new BufferedWriter( new OutputStreamWriter( res.getOutputStream(), resultTemplate.getEncoding() ) );
//            res.setContentType("text/html; charset=" + resultTemplate.getEncoding());


            username = req.getParameter( "username" );
            email = req.getParameter( "email" );
            password = req.getParameter( "pass" );


            try {
                User user = logicLayer.checkUser(username, email );
                if (user == null) { //username and email must be match
                    msg = "Invalid username or email";
                } else {
                    logicLayer.updatePassword(password, user);
                    msg = "Successfully reset password";
                }
            }
            catch( RARException e) {
//                resultTemplateName = "loginRegister.ftl";
//                msg = "Invalid username or Password";
//                resultTemplate = cfg.getTemplate( resultTemplateName );
//                toClient = new BufferedWriter( new OutputStreamWriter( res.getOutputStream(), resultTemplate.getEncoding() ) );
//                res.setContentType("text/html; charset=" + resultTemplate.getEncoding());

            } catch ( Exception e ) {
                RARError.error( cfg, toClient, e );
                return;
            }

            res.setContentType("text/plain");
            res.getWriter().write(msg);












//            // Setup the data-model
//            //
//            Map<String, String> root = new HashMap<String, String>();
//
//            // Build the data-model
//            //
//            root.put( "username", username );
//            httpSession.setAttribute( "username", username );
//
//            root.put( "message", msg );
//
//            // Merge the data-model and the template
//            //
//            try {
//                resultTemplate.process( root, toClient );
//                toClient.flush();
//            }
//            catch (TemplateException e) {
//                throw new ServletException( "Error while processing FreeMarker template", e);
//            }
//
           // toClient.close();
        }


        public void doGet(HttpServletRequest req, HttpServletResponse res)
                throws IOException, ServletException {
            doPost(req,res);
        }






}
