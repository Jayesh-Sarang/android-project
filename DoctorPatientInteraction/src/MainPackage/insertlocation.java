package MainPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseConnection;

import com.google.gson.JsonObject;

/**
 * Servlet implementation class insertlocation
 */
@WebServlet("/insertlocation")
public class insertlocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseConnection db1;
	 JsonObject jsonObject;
	 Connection con;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertlocation() {
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
		
		
		String hospitalname=request.getParameter("hospitalname");
		String location=request.getParameter("location");
		String operations=request.getParameter("operations");
		String lattitude=request.getParameter("lattitude");
		String longitude=request.getParameter("longitude");
		
		String machines=request.getParameter("machines");
		String facility=request.getParameter("facility");
		String doctors=request.getParameter("doctors");
		String staffs=request.getParameter("staffs");
		String beds=request.getParameter("beds");
		String phonenumber=request.getParameter("phonenumber");
		
		
		String query1="insert into hospitallist values(?,?,?,?,?,?,?,?,?,?,?)";


			
	        PreparedStatement psmt1;
	        InputStream is = null;
				try {
					psmt1 = con.prepareStatement(query1);
					 psmt1.setString(1,hospitalname);
					 psmt1.setString(2,location);
					 psmt1.setString(3,machines);
					 psmt1.setString(4,facility);
					 psmt1.setString(5,doctors);
					 psmt1.setString(6,staffs);
					 psmt1.setString(7,beds);
					
					 psmt1.setString(8,operations);
					 psmt1.setString(9,lattitude);
					 psmt1.setString(10,longitude);
					
					 psmt1.setString(11,phonenumber);
		   		 
			        
		   		      
		                   int i1 = psmt1.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				RequestDispatcher rs = request.getRequestDispatcher("insertlocation.jsp");
				rs.forward(request,response);
				
		
	}

}
