package edu.uga.cs.rentaride.presentation;
 
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
 
@WebServlet("/uploadServlet")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB

public class FileUploadDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
	
     
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
		String uploadImage = "INSERT INTO image (image) values (?)";

        InputStream inputStream = null; // input stream of the upload file
         
        // obtains the upload file part in this multipart request
        
        Part filePart = request.getPart("photo");
        
        
        //if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
             
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
       // }
    		String connnectionURL = "jdbc:mysql://uml.cs.uga.edu:3306/team10";
        Connection con = null; // connection to the database
        
        String message = null;  // message will be sent back to client
         
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(connnectionURL, "team10", "trycatch");

			ResultSet rs = null;
			PreparedStatement pstmt = null;
			
             pstmt = con.prepareStatement(uploadImage);
          
            if (inputStream != null) {
                // fetches input stream of the upload file for the blob column
                pstmt.setBlob(1, inputStream);
            }
 
            // sends the statement to the database server
            int row = pstmt.executeUpdate();
            if (row > 0) {
                message = "File uploaded and saved into database";
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
            message = "ERROR: " + ex.getMessage();
            ex.printStackTrace();
            
        } finally {
            
                try {
                	if(con !=null)
                    con.close();
                } catch (SQLException ex) {
                }
            }
        
        
        
        
            // sets the message in request scope
            request.setAttribute("Message", message);
             
            // forwards to the message page
            getServletContext().getRequestDispatcher("/Message.jsp").forward(request, response);
        }
    }
