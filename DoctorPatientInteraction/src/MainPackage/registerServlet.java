package MainPackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseConnection;

import com.google.gson.JsonObject;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseConnection db1=new DatabaseConnection();
	 JsonObject jsonObject;
	 Connection con;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerServlet() {
        super();
        con=db1.dbconnection();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String regId = request.getParameter("regId");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String rollnumber = request.getParameter("name");
		String dept = request.getParameter("dept");
		String query="insert into usertable1(name,email,regId,rollnumber,department) values(?,?,?,?,?)";

		
		
        PreparedStatement psmt;
	
			try {
				psmt = con.prepareStatement(query);
				 psmt.setString(1,name);
	   		        psmt.setString(2,email);
	   		        psmt.setString(3,regId);
	   		     psmt.setString(4,rollnumber);
	   		  psmt.setString(5,dept);
	   		     
	   		      
	                   int i = psmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String regId = request.getParameter("regId");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("year");
		String dept = request.getParameter("dept");
		String rollnumber = request.getParameter("name");
		String phonenumber = request.getParameter("phonenumber");
		String query="insert into usertable1(name,email,regId,year,department) values(?,?,?,?,?)";

		
		
        PreparedStatement psmt;
	
			try {
				psmt = con.prepareStatement(query);
				 psmt.setString(1,name);
	   		        psmt.setString(2,email);
	   		        psmt.setString(3,regId);
	   		   
	   		  psmt.setString(4,"");
	   		     psmt.setString(5,dept);
	   		      
	                   int i = psmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			String query1="insert into user(username,emailid,password,type,mobilenumber) values(?,?,?,?,?)";

	        PreparedStatement psmt1;
		
				try {
					psmt1 = con.prepareStatement(query1);
					 psmt1.setString(1,name);
		   		        psmt1.setString(2,email);
		   		        psmt1.setString(3,password);
		   		     psmt1.setString(4,"user");
		   		  psmt1.setString(5,phonenumber);
		   		      
		                   int i = psmt1.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
			
	
	}

}
