package MainPackage;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseConnection;
import SendSms.SendSms;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class AllslotAppintment
 */
@WebServlet("/AllslotAppintment")
public class AllslotAppintment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseConnection db1;
	 JsonObject jsonObject;
	 Connection con;
	 String message1="";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllslotAppintment() {
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


		
		
		
		
		
		String message="";
		
		String usernames=request.getParameter("usernames");
		String emails=request.getParameter("emails");
		String phoneString=request.getParameter("phoneString");
		String doctorsandspec=request.getParameter("doctorsandspec");
		String dates=request.getParameter("dates");
		
		String times=request.getParameter("times");
		String dividedoct[]=doctorsandspec.split(":");
		String specialization=request.getParameter("specialization");
		
		
		String splittimeanddate[]=dates.split(",");
		
		

		
  	    	
					
		  	            	message1="";
		  	            	
		
		  	 String appointmentwaiting="";           	
		  	            	
		  	            	
  	    		// ps1.setString(1, "1" );
  	    	for(int i=0;i<splittimeanddate.length;i++){
  	    		
  	    		
  	    		
  	    		
  	  		String dividedoct1[]=splittimeanddate[i].split("-");
  	    		
  	  		
  	  
  	  		
  	  		
  	  		
  	  		if(dividedoct1.length==3){
  	  			
  	  		appointmentwaiting="appointment on "+dividedoct1[0]+" "+dividedoct1[1]+" is "+dividedoct1[2];
//  	   	  	 String sql1="select * from appointment where doctor='"+ dividedoct[0]+"' and date='"+dates+"' and time='"+dividedoct1[1]+"' and status='Pending'";
//  	 	    	
//
//   	    	PreparedStatement ps112;
//   	    	
//   	    		try {
// 					ps112 = con.prepareStatement(sql1);
// 					 ResultSet rs1=ps112.executeQuery();
// 		  	            if(rs1.next()) { 	
 		  	            	
 		  	            
  	  		 SendSms.Way2SMS(phoneString, appointmentwaiting);	
  	  			
  	  			
  			String query1="insert into dataappointment(username,emailid,phonenumber,doctor,specialization,date,time,status) values(?,?,?,?,?,?,?,?)";

  			
  			
  			
  			
  			
  			


  			String messqge1="";
  			String mfg=messqge1.replaceAll("[\\t\\n\\r]"," ");
  			message=mfg;//"Appointment received for "+usernames+" on "+dates+" at "+times+" for "+dividedoct[1];
  		        PreparedStatement psmt1;
  		        InputStream is = null;
  				
  						try {
							psmt1 = con.prepareStatement(query1);
							psmt1.setString(1,usernames);
	  						 psmt1.setString(2,emails);
	  						 psmt1.setString(3,phoneString);
	  						 psmt1.setString(4,dividedoct[0]);
	  						 psmt1.setString(5,specialization);
	  						 psmt1.setString(6,dividedoct1[0]);
	  						 psmt1.setString(7,dividedoct1[1]);
	  						 psmt1.setString(8,dividedoct1[2]);
	  						 
	  						
	  			   		
	  			   		 
	  				        
	  			   		      
	  			                   int i1 = psmt1.executeUpdate();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
  						 
// 		  	            }
 		  	            
//  					}
  	  		
  					
 		  	            }         	
  	  		
  	  		
  	  		else
  	  		{
  	  			
String query1="insert into appointment(username,emailid,phonenumber,doctor,specialization,date,time,status) values(?,?,?,?,?,?,?,?)";

  			
  			
  			
  			
  			
  			


  			String messqge1="";
  			String mfg=messqge1.replaceAll("[\\t\\n\\r]"," ");
  			message=mfg;//"Appointment received for "+usernames+" on "+dates+" at "+times+" for "+dividedoct[1];
  		        PreparedStatement psmt1;
  		        InputStream is = null;
  					try {
  						psmt1 = con.prepareStatement(query1);
  						 psmt1.setString(1,usernames);
  						 psmt1.setString(2,emails);
  						 psmt1.setString(3,phoneString);
  						 psmt1.setString(4,dividedoct[0]);
  						 psmt1.setString(5,specialization);
  						 psmt1.setString(6,dividedoct1[0]);
  						 psmt1.setString(7,dividedoct1[1]);
  						 psmt1.setString(8,"Pending");
  						 
  						
  			   		
  			   		 
  				        
  			   		      
  			                   int i1 = psmt1.executeUpdate();
  					} catch (SQLException e) {
  						// TODO Auto-generated catch block
  						e.printStackTrace();
  					}
  					
  	  			
  	  		}
  	    		
  	    	}	    
  	    	    
		

				
					       	    	ArrayList<Countries> country=new ArrayList<Countries>();
					       	     country=getAllCountries(message1);
					       	    	
					       	     
					    		 Gson gson = new Gson();
					    		  JsonElement element = gson.toJsonTree(country, new TypeToken<List<Countries>>() {}.getType());
					    		 JsonArray jsonArray = element.getAsJsonArray();
					    		 JsonObject jsonObject=new JsonObject();
					    		  jsonObject.add("jsonarrayval", jsonArray);
					    		  response.setContentType("application/json");
					    		  response.getWriter().print(jsonObject);
					                   
//		request.setAttribute("doctorname", dividedoct[0]);
//		request.setAttribute("message", message);
//				request.getRequestDispatcher("GCMNotification")
//				.forward(request, response);
  	    		
	
	}
	public  ArrayList<Countries> getAllCountries(String message12) {
		
		 ArrayList<Countries> countryList = new ArrayList<Countries>();

	     //connection = con;
	        ArrayList<Book> booklist = new ArrayList<Book>();
	        
	            	
	      	       
		            	
		            	
		             Countries country=new Countries();
		            
		            	 
		            	  country.setName(message12); 
		            	 
		            	  countryList.add(country);
	     
	
	     //connection = con;
	       
	           
	               
	           
	          

	        return countryList;
	}	

}
