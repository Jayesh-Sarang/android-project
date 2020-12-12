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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import Database.DatabaseConnection;

/**
 * Servlet implementation class GetWholeHospitalDetails
 */
@WebServlet("/GetWholeHospitalDetails")
public class GetWholeHospitalDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String methodname="";
	DatabaseConnection db1=new DatabaseConnection();
	 JsonObject jsonObject;
	 Connection con;
	 String sendToMObile;
	    ArrayList<Book> books=new ArrayList<Book>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetWholeHospitalDetails() {
        super();
        con=db1.dbconnection();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		 ArrayList<hospitalinformation> books=new ArrayList<hospitalinformation>();
		  books=getAllCountries();
		  Gson gson = new Gson();
		  JsonElement element = gson.toJsonTree(books, new TypeToken<List<Countries>>() {}.getType());
JsonObject jsonObject=new JsonObject();

		  JsonArray jsonArray = element.getAsJsonArray();
		  jsonObject.add("jsonarrayval", jsonArray);
		  response.setContentType("application/json");
		  response.getWriter().print(jsonObject);
	
	
	}

	public  ArrayList<hospitalinformation> getAllCountries() {
	     //connection = con;
	        ArrayList<hospitalinformation> booklist = new ArrayList<hospitalinformation>();
	        String sql="select * from hospitallist";
	    	
	    	PreparedStatement ps1;
	    	try {
	    		ps1 = con.prepareStatement(sql);
	    		// ps1.setString(1, "1" );
	    		    
	    	     ResultSet rs=ps1.executeQuery();
	            while(rs.next()) { 
	            //	+"_"+rs.getString("hospitalname")+","+rs.getString("location")+","+rs.getString("operations")
	            	
	            	hospitalinformation hli=new hospitalinformation();
	            	hli.setHospitalname(rs.getString("hospitalname"));
	            	hli.setLattitude(rs.getString("lattitude"));
	            	hli.setLongitude(rs.getString("longitude"));
	            	hli.setHospitalname(rs.getString("hospitalname"));
	            	hli.setMachines(rs.getString("machines"));
	            	hli.setBeds(rs.getString("beds"));
	            	hli.setOperations(rs.getString("operations"));
	            	hli.setDoctors(rs.getString("doctors"));
	            	hli.setStaffs(rs.getString("staffs"));
	            	hli.setLocation(rs.getString("location"));
	            	//hli.se(rs.getString(""));
	            	hli.setFacility(rs.getString("facility"));
	               
	                booklist.add(hli);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return booklist;
	}
}

