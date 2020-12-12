package MainPackage;

import java.io.IOException;
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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class checkdoctoravailability
 */
@WebServlet("/checkdoctoravailability")
public class checkdoctoravailability extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseConnection db1;
	 JsonObject jsonObject;
	 Connection con;
	 String message1="";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checkdoctoravailability() {
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
		
		
		String doctorsandspec=request.getParameter("doctorsandspec");
		String dates=request.getParameter("dates");
		String times=request.getParameter("times");
		String dividedoct[]=doctorsandspec.split(":");

		 String sql1="select * from appointment where doctorname='"+ dividedoct[0]+"'and time='"+times+"'";
  	    	
  	    	PreparedStatement ps11;
  	    	
  	    		
					try {
						ps11 = con.prepareStatement(sql1);
						 ResultSet rs1=ps11.executeQuery();
			  	            if(rs1.next()) { 
			  	            	message1=rs1.getString("clinic");
			  	            	System.out.println("hi executed");
			  	            }
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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