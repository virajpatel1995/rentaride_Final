package edu.uga.cs.rentaride.presentation;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
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



@WebServlet("/imageUploadServlet")






public class imageUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public imageUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String getImagefromDb = "select * from image";
		//String getImagefromDb = "SELECT image from image WHERE id=2;";

		//String uploadImage = "INSERT INTO image (image) values (?)";
		
	
		String connnectionURL = "jdbc:mysql://uml.cs.uga.edu:3306/team10";
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(connnectionURL, "team10", "trycatch");
			
			ResultSet rs = null;
			PreparedStatement pstmt = null;
			OutputStream img;
			pstmt = con.prepareStatement(getImagefromDb);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				byte barrar[] = rs.getBytes(2);

				response.setContentType("image/png");
				img = response.getOutputStream();
				img.write(barrar);
				img.flush();
				img.close();
				
			}
			
			
			
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			try {
				
				if(con !=null)
					con.close();
				
			}catch(Exception ex) {
				
			}
		}
		
		
		
		
		
		
		
		
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		
		
		
		doGet(request, response);
	}

}
