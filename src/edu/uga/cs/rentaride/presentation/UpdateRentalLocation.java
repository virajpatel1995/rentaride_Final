package edu.uga.cs.rentaride.presentation;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.RentalLocation;
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

@WebServlet("UpdateRentalLocation")
public class UpdateRentalLocation extends HttpServlet
    {
        private static final long serialVersionUID = 1L;
        static  String         templateDir = "WEB-INF/templates";
        static  String         resultTemplateName = "myAccountAdmin.ftl";

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
        { Template       resultTemplate = null;
            HttpSession    httpSession = null;
            BufferedWriter toClient = null;
            LogicLayer     logicLayer = null;
            Session        session;
            String         ssid;
            Map<String,Object> root = new HashMap<String,Object>();
            String retMessage = "";


            // Session Tracking
            httpSession = req.getSession();
            ssid = (String) httpSession.getAttribute("ssid");

            session = SessionManager.getSessionById(ssid);
            if(session == null){
                RARError.error( cfg, new BufferedWriter(new OutputStreamWriter(res.getOutputStream(), "UTF-8")),"Session expired or illegal; please log in" );
                return;
            }
            logicLayer = session.getLogicLayer();

            String msg = null;
            String id = null;
            String addr = null;
            String name = null;
            String capacityS = null;
            // Get the form parameters
            //
            id = req.getParameter("rlid");
            name = req.getParameter( "name" );
            addr = req.getParameter( "address" );
            capacityS = req.getParameter( "capacity" );
            System.out.println(id + " " + name);


            try{
                long lid = Long.parseLong(id);
                RentalLocation rl = logicLayer.getRentalLocationById(lid);
                rl.setName(name);
                rl.setAddress(addr);
                rl.setCapacity(Integer.valueOf(capacityS.replace(",","")));
                logicLayer.updateRentalLocation(rl);
                msg = "Update successfully";
            }catch(RARException e) {
                e.printStackTrace();
                msg = e.toString();
            }


            res.setContentType("text/plain");
            res.getWriter().write(msg);


//            toClient.close();


    }


}
