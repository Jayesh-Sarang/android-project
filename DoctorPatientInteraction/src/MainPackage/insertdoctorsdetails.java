package MainPackage;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseConnection;

import com.google.gson.JsonObject;

/**
 * Servlet implementation class insertdoctorsdetails
 */
@WebServlet("/insertdoctorsdetails")
public class insertdoctorsdetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseConnection db1;
	 JsonObject jsonObject;
	 Connection con;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertdoctorsdetails() {
        super();
        db1 = new DatabaseConnection();
		con=db1.dbconnection();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String doctorname=request.getParameter("doctorname");
		String hospitalname=request.getParameter("hospitalname");
		String availability=request.getParameter("availability");
		String education=request.getParameter("education");
		String operation=request.getParameter("operation");
		String about=request.getParameter("about");
		
		
		String query1="insert into doctorsdetails(doctorname,hospitalname,availability,education,operation,about) values(?,?,?,?,?,?)";


			
	        PreparedStatement psmt1;
	        InputStream is = null;
				try {
					psmt1 = con.prepareStatement(query1);
					 psmt1.setString(1,doctorname);
					 psmt1.setString(2,hospitalname);
					 psmt1.setString(3,availability);
					 psmt1.setString(4,education);
					 psmt1.setString(5,operation);
					 psmt1.setString(6,about);
					
		   		
		   		 
			        
		   		      
		                   int i1 = psmt1.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				RequestDispatcher rs = request.getRequestDispatcher("insertdoctordetails.jsp");
				rs.forward(request,response);
				
		
	
	}

}
