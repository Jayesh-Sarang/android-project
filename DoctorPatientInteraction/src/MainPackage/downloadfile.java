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
 * Servlet implementation class downloadfile
 */
@WebServlet("/downloadfile")
public class downloadfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String methodname="";
	DatabaseConnection db1=new DatabaseConnection();
	 JsonObject jsonObject;
	 Connection con;
	 String sendToMObile;
	    ArrayList<studentinfo> studentList=new ArrayList<studentinfo>();
	    studentinfo student=new studentinfo();
	    String year="100831";  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public downloadfile() {
        super();
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
		

		 ArrayList<Countries> country=new ArrayList<Countries>();
		 year=request.getParameter("name");
			
		  country=getAllCountries();
		 
		
		
		 Gson gson = new Gson();
		  JsonElement element = gson.toJsonTree(country, new TypeToken<List<Countries>>() {}.getType());
		 JsonArray jsonArray = element.getAsJsonArray();
		 JsonObject jsonObject=new JsonObject();
		  jsonObject.add("jsonarrayval", jsonArray);
		  response.setContentType("application/json");
		  response.getWriter().print(jsonObject);
	
		
		
		
		
	}
	public  ArrayList<Countries> getAllCountries() {

		
		 ArrayList<Countries> countryList = new ArrayList<Countries>();

	     //connection = con;
	        ArrayList<Book> booklist = new ArrayList<Book>();
	       
	        String sql="select * from details where username='"+year+"'";
	    	PreparedStatement ps1;
	    	try {
	    		ps1 = con.prepareStatement(sql);
	    		// ps1.setString(1, "1" );
	    		    
	    	     ResultSet rs=ps1.executeQuery();
	            while(rs.next()) { 
	            	
	            	
	      	       
		            	
		            	
		             Countries country=new Countries();
		            
		            	 
//		            	  country.setName(rs.getString("name")); 
		            	  country.setSubcategory(rs.getString("username"));
		            	  country.setPrice(rs.getString("filename"));
		            	  country.setDescription(rs.getString("path"));
		            	
		            	  countryList.add(country);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	     
	
	     //connection = con;
	       
	           
	               
	           
	          

	        return countryList;
	
	}	

}


