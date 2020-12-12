package MainPackage;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import Database.DatabaseConnection;

/**
 * Servlet implementation class appointdoctor
 */
@WebServlet("/appointdoctor")
public class appointdoctor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseConnection db1;
	 JsonObject jsonObject;
	 Connection con;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public appointdoctor() {
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
		HttpSession session = request.getSession();
		String usernames = (String) session.getAttribute("user");
		
//		String usernames=request.getParameter("usernames");
		String emails=(String) session.getAttribute("emailid");
		String phoneString=request.getParameter("phoneString");
		String doctorsandspec=request.getParameter("doctor");
		String dates=request.getParameter("dateofuser");
		String times=request.getParameter("timeslot");
		String query1="insert into appointment(username,emailid,phonenumber,doctor,specialization,date,time,status) values(?,?,?,?,?,?,?,?)";

		
		
		
		
		
		
String dividedoct[]=doctorsandspec.split(":");
//message="Appointment received for "+usernames+" on "+dates+" at "+times+" for "+dividedoct[1];
	        PreparedStatement psmt1;
	        InputStream is = null;
				try {
					psmt1 = con.prepareStatement(query1);
					 psmt1.setString(1,usernames);
					 psmt1.setString(2,emails);
					 psmt1.setString(3,phoneString);
					 psmt1.setString(4,doctorsandspec);
					 psmt1.setString(5,doctorsandspec);
					 psmt1.setString(6,dates);
					 psmt1.setString(7,times);
					 psmt1.setString(8,"pending");
					 
					
		   		
		   		 
			        
		   		      
		                   int i1 = psmt1.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.getRequestDispatcher("ViewDoctorslist.jsp")
				.forward(request, response);	

	}

}
