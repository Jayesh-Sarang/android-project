package MainPackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseConnection;

import com.google.gson.JsonObject;



/**
 * Servlet implementation class issuebook
 */
@WebServlet("/issuebook")
public class issuebook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseConnection db1=new DatabaseConnection();
	 JsonObject jsonObject;
	 Connection con;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public issuebook() {
        super();
        con=db1.dbconnection();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String rollnumber="a";
		String bookid="a";
		String gcmid="a";
		String quantity="1";
		String book="";
		String bookId="";
		Calendar calendar = Calendar.getInstance();
		 java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
		 java.sql.Date endDate = new java.sql.Date(calendar.getTime().getTime());
		 java.sql.Date midnight = new java.sql.Date( startDate.getTime() + (86400000*7) ); 
		String query="insert into issuebook(rollnumber,bookid,startdate,enddate) values(?,?,?,?)";

		
		
           PreparedStatement psmt;
   	
   			try {
				psmt = con.prepareStatement(query);
				 psmt.setString(1,rollnumber);
	   		        psmt.setString(2,bookid);
	   		        psmt.setDate(3,startDate);
	   		     psmt.setDate(4,midnight);
	   		      
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
		
		
		String rollnumber="a";
		String bookid="a";
		String gcmid="a";
		String quantity="1";
		String book="";
		String bookId="";
		Calendar calendar = Calendar.getInstance();
		 java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
		 java.sql.Date endDate = new java.sql.Date(calendar.getTime().getTime());
		
		
		String query="insert into ticket(rollnumber,bookid,startdate,enddate) values(?,?,?,?)";

           PreparedStatement psmt;
   	
   			try {
				psmt = con.prepareStatement(query);
				 psmt.setString(1,rollnumber);
	   		        psmt.setString(2,bookid);
	   		     psmt.setDate(3,startDate);
	   		     psmt.setDate(4,endDate);
	   		      
	                   int i = psmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
   			
   		       
		
	}

}
