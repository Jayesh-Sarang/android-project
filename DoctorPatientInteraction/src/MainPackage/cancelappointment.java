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
 * Servlet implementation class cancelappointment
 */
@WebServlet("/cancelappointment")
public class cancelappointment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseConnection db1;
	 JsonObject jsonObject;
	 Connection con;
	 String message1="";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cancelappointment() {
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
		
		
		 String sql1m="select * from appointment where doctor='"+ doctor+"' and date='"+dates+"' and time='"+days+"' ";
	    	

	    	PreparedStatement ps112m;
	    	
	    	String phoneofpatient="";
						try {
							ps112m = con.prepareStatement(sql1m);
							 ResultSet rs1m=ps112m.executeQuery();
				  	            if(rs1m.next()) { 
				  	            	
				  	            	
				  	            	phoneofpatient=rs1m.getString("phonenumber");   	
				  	            	
				  	            }
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
		
						
//						 SendSms.SendSms.Way2SMS(phoneofpatient, "Your OTP is "+ OTP);
		
		 String sql1="select * from dataappointment where doctor='"+ doctor+"' and date='"+dates+"' and time='"+days+"' ";
	    	
boolean checking=true;
	    	PreparedStatement ps112;
	    	
	    	
					try {
						ps112 = con.prepareStatement(sql1);
						 ResultSet rs1=ps112.executeQuery();
			  	            while(rs1.next()) { 
			  	            	checking=false;
			  	            	String usernames1=rs1.getString("username");
			  	      		String emails1=rs1.getString("emailid");
			  	      		String phoneString1=rs1.getString("phonenumber");
			  	      		String doctor1=rs1.getString("doctor");
			  	      		String specialization1=rs1.getString("specialization");
			  	      		
			  	      	String date1=rs1.getString("date");
		  	      		String time1=rs1.getString("time");
		  	      		
		  	      	   
		  	  		
		  	  		String query1="insert into appointment(username,emailid,phonenumber,doctor,specialization,date,time,status) values(?,?,?,?,?,?,?,?)";

		  	  	appointmentwaiting="your appoiuntment for "+specialization1+" and "+doctor1+" on "+date1+" "+time1+" waiting for your approval";
		  	  		
		  	  SendSms.SendSms.Way2SMS(phoneString1, appointmentwaiting);
		  	  	//	String messqge1="Appointment received for "+usernames+" on "+dates+" at "+times+" for "+dividedoct[1];
//		  	  		String mfg=messqge1.replaceAll("[\\t\\n\\r]"," ");
		  	  		//message=mfg;//"Appointment received for "+usernames+" on "+dates+" at "+times+" for "+dividedoct[1];
		  	  	        PreparedStatement psmt1;
		  	  	        InputStream is = null;
		  	  				try {
		  	  					psmt1 = con.prepareStatement(query1);
		  	  					 psmt1.setString(1,usernames1);
		  	  					 psmt1.setString(2,emails1);
		  	  					 psmt1.setString(3,phoneString1);
		  	  					 psmt1.setString(4,doctor1);
		  	  					 psmt1.setString(5,specialization1);
		  	  					 psmt1.setString(6,date1);
		  	  					 psmt1.setString(7,time1);
		  	  					 psmt1.setString(8,"waitingcheck");
		  	  					 
		  	  					
		  	  		   		
		  	  		   		 
		  	  			        
		  	  		   		      
		  	  		                   int i1 = psmt1.executeUpdate();
		  	  				} catch (SQLException e) {
		  	  					// TODO Auto-generated catch block
		  	  					e.printStackTrace();
		  	  				}
		  	  				
			  	            	
			  	            
		  	  				
		  	  			String query12="delete  from dataappointment where doctor='"+ doctor+"' and date='"+dates+"' and time='"+days+"' and username='"+usernames1+"'";
		  	  		 PreparedStatement psmt1q;
			  	  		  	  				
		  	  				
		  	  		psmt1q = con.prepareStatement(query12);
			  	            	
		  	  		psmt1q.executeUpdate();  
		  	  		
		  	  		
		  	  	String query121="delete  from appointment where doctor='"+ doctor+"' and date='"+dates+"' and time='"+days+"' and username='"+usernames+"'";
	  	  		 PreparedStatement psmt1q1;
		  	  		  	  				
	  	  				
	  	  		psmt1q1 = con.prepareStatement(query121);
		  	            	
	  	  		psmt1q1.executeUpdate();  
		  	  		
	  	  	appointmentcancelled="Yours appointment date='"+dates+"' and time='"+days+" has been cancelled";
			  	            	
			  	            }
			  	            if(checking)
			  	            {
			  	            	
			  	      		
			  			  	  	String query121="delete  from appointment where doctor='"+ doctor+"' and date='"+dates+"' and time='"+days+"' and username='"+usernames+"'";
			  		  	  		 PreparedStatement psmt1q1;
			  			  	  		  	  				
			  		  	  				
			  		  	  		psmt1q1 = con.prepareStatement(query121);
			  			  	            	
			  		  	  		psmt1q1.executeUpdate();   
			  		  	  		
			  		  		appointmentcancelled="Yours appointment date='"+dates+"' and time='"+days+" has been cancelled";
			  	            	
			  	            }
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					appointmentcancelled="Yours appointment date='"+dates+"' and time='"+days+" has been cancelled";
					 SendSms.SendSms.Way2SMS(phoneofpatient, appointmentcancelled);
					
	}

}
