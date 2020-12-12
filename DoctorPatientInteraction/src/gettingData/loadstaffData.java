package gettingData;

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
import MainPackage.Book;
import MainPackage.Countries;

/**
 * Servlet implementation class loadstaffData
 */
@WebServlet("/loadstaffData")
public class loadstaffData extends HttpServlet {
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
    public loadstaffData() {
        super();
        con=db1.dbconnection();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 ArrayList<Book> books=new ArrayList<Book>();
		 String hospname=request.getParameter("hospitalname");
		  books=getAllCountries(hospname);
		  Gson gson = new Gson();
		  JsonElement element = gson.toJsonTree(books, new TypeToken<List<Countries>>() {}.getType());
JsonObject jsonObject=new JsonObject();

		  JsonArray jsonArray = element.getAsJsonArray();
		  jsonObject.add("jsonarrayval", jsonArray);
		  response.setContentType("application/json");
		  response.getWriter().print(jsonObject);
	
	
	}

	public  ArrayList<Book> getAllCountries(String hospname) {
	     //connection = con;
	        ArrayList<Book> booklist = new ArrayList<Book>();
	        String sql="select * from staffdetails where hospitalname='"+hospname+"'";
	    	
	    	PreparedStatement ps1;
	    	try {
	    		ps1 = con.prepareStatement(sql);
	    		// ps1.setString(1, "1" );
	    		    
	    	     ResultSet rs=ps1.executeQuery();
	            while(rs.next()) { 
	            //	+"_"+rs.getString("hospitalname")+","+rs.getString("location")+","+rs.getString("operations")
	            	
	            	Book book=new Book();
//	            book.setBookName(rs.getString("hospitalname"));
//	             book.setAuthor(rs.getString("lattitude")+"_"+rs.getString("longitude")+"_"+rs.getString("operations"));
//	                book.setPublisher(rs.getString("location")+"_"+rs.getString("machines")+"_"+rs.getString("facility")+"_"+rs.getString("doctors")+"_"+rs.getString("staffs")+"_"+rs.getString("beds")+"_"+rs.getString("operations"));
//	                book.setStock(rs.getString("operations"));
	            	book.setDoctorname(rs.getString("doctorname"));
	            	//book.setSpecialization(rs.getString("specialization"));
	            	book.setAvailability(rs.getString("availability"));
	            	//book.setLocation(rs.getString("location"));
	            	book.setEducation(rs.getString("education"));
	            	book.setOperation(rs.getString("operation"));
	            	book.setAbout(rs.getString("about"));
	            	
	                booklist.add(book);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return booklist;
	}
}

