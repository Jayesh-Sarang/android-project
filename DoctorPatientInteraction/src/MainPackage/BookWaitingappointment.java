package MainPackage;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseConnection;

import com.google.gson.JsonObject;

/**
 * Servlet implementation class BookWaitingappointment
 */
@WebServlet("/BookWaitingappointment")
public class BookWaitingappointment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseConnection db1;
	 JsonObject jsonObject;
	 Connection con;
	 String message1="";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookWaitingappointment() {
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

		String usernames=request.getParameter("username");
		String doctor=request.getParameter("doctor");
		String days=request.getParameter("days");
		String dates=request.getParameter("dates");
		String doctorsandspec=request.getParameter("doctorsandspec");
		
		String appointmentcancelled="";
		String appointmentwaiting="";
		
		
		 String sql1m="select * from appointment where doctor='"+ doctor+"' and date='"+dates+"' and time='"+days+"' and username='"+usernames+"'";
	    	

	    	PreparedStatement ps112m;
	    	
	    	String phoneofpatient="";
						try {
							ps112m = con.prepareStatement(sql1m);
							 ResultSet rs1m=ps112m.executeQuery();
				  	            if(rs1m.next()) { 
				  	            	
				  	            	
				  	            	phoneofpatient=rs1m.getString("phonenumber");  
				  	            	
				  	            	
				  	            	String query121="update appointment set status ='Approved',statusbyuser ='Approved'"+"where doctor='"+ doctor+"' and date='"+dates+"' and time='"+days+"' and username='"+usernames+"'";
				  		  	  		 PreparedStatement psmt1q1;
				  			  	  		  	  				
				  		  	  				
				  		  	  		psmt1q1 = con.prepareStatement(query121);
				  			  	            	
				  		  	  		psmt1q1.executeUpdate();   
				  		  	  		
				  		  	  		
				  		  	  	String query121e="delete from appointment where doctor='"+ doctor+"' and  username='"+usernames+"'";
			  		  	  		 PreparedStatement psmt1q1e;
			  			  	  		  	  				
			  		  	  				
			  		  	  		psmt1q1e = con.prepareStatement(query121e);
			  			  	            	
			  		  	  		int checkupdate=psmt1q1e.executeUpdate();   
				  		  	  		
				  		  	  		
				  		  	  	appointmentcancelled="Yours appointment date='"+dates+"' and time='"+days+" has been Approved";
								 
				  		  	  	SendSms.SendSms.Way2SMS(phoneofpatient, appointmentcancelled);
				  		  	  	
				  		  	if(checkupdate>0){
				  		  String	appointmentdeleted="Yours other appointment  has been deleted";
							 
			  		  	  	SendSms.SendSms.Way2SMS(phoneofpatient, appointmentdeleted);
				  		  	}
//				  		  	  	String query1211="delete  from appointment where doctor='"+ doctor+"' and date='"+dates+"' and time='"+days+"' and username='"+usernames+"'";
//			  		  	  		 PreparedStatement psmt1q11;
//			  			  	  		  	  				
//			  		  	  				
//			  		  	  		psmt1q11 = con.prepareStatement(query1211);
//			  			  	            	
//			  		  	  		psmt1q11.executeUpdate();   
				  		  	
				  		  	  		
				  		  	  		
				  		  	  		
				  		  	  		
				  	            	
				  	            	
				  	            	
				  	            }
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
		
						
						 String sql1m1="select * from appointment where doctor='"+ doctor+"' and date='"+dates+"' and time='"+days+"' and status!='Approved'";
					    	

					    	PreparedStatement ps112m1;
					    	
					    	String phoneofpatient1="";
										try {
											ps112m1 = con.prepareStatement(sql1m1);
											 ResultSet rs1m1=ps112m1.executeQuery();
								  	            while(rs1m1.next()) { 
								  	            	
								  	            String users=rs1m1.getString("username");	
								  	            
								  	            	String query121="update appointment set status ='NOT',statusbyuser ='NOT'"+"where doctor='"+ doctor+"' and date='"+dates+"' and time='"+days+"' and username='"+users+"'";
								  		  	  		 PreparedStatement psmt1q1;
								  			  	  		  	  				
								  		  	  				
								  		  	  		psmt1q1 = con.prepareStatement(query121);
								  			  	            	
								  		  	  		psmt1q1.executeUpdate();      
								  		  	  		
								  	
//								  		  	  	String query1211="delete  from appointment where doctor='"+ doctor+"' and date='"+dates+"' and time='"+days+"' and username='"+usernames+"'";
//							  		  	  		 PreparedStatement psmt1q11;
//							  			  	  		  	  				
//							  		  	  				
//							  		  	  		psmt1q11 = con.prepareStatement(query1211);
//							  			  	            	
//							  		  	  		psmt1q11.executeUpdate();   
								  		  	
								  		  	  		
								  		  	  		
								  		  	  		
								  		  	  		
								  	            	
								  	            	
								  	            	
								  	            }
										} catch (SQLException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
													
						
						
						
						
//						 SendSms.SendSms.Way2SMS(phoneofpatient, "Your OTP is "+ OTP);
	}
	
}
