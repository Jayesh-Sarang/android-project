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
 * Servlet implementation class getprescrptiondetails
 */
@WebServlet("/getprescrptiondetails")
public class getprescrptiondetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String methodname="";
	DatabaseConnection db1=new DatabaseConnection();
	 JsonObject jsonObject;
	 Connection con;
	 String sendToMObile;
	    ArrayList<Book> books=new ArrayList<Book>();
	    String username="";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getprescrptiondetails() {
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
		username=request.getParameter("username");
		
		ArrayList<Book> books=new ArrayList<Book>();
		  books=getAllCountries();
		  Gson gson = new Gson();
		  JsonElement element = gson.toJsonTree(books, new TypeToken<List<Countries>>() {}.getType());
JsonObject jsonObject=new JsonObject();

		  JsonArray jsonArray = element.getAsJsonArray();
		  jsonObject.add("jsonarrayval", jsonArray);
		  response.setContentType("application/json");
		  response.getWriter().print(jsonObject);
	
	}

	public  ArrayList<Book> getAllCountries() {
	     //connection = con;
	        ArrayList<Book> booklist = new ArrayList<Book>();
	        String sql="select * from userprescription where username='"+username+"'";
	    	
	    	PreparedStatement ps1;
	    	try {
	    		ps1 = con.prepareStatement(sql);
	    		// ps1.setString(1, "1" );
	    		    
	    	     ResultSet rs=ps1.executeQuery();
	            while(rs.next()) { 
	            	
	            	
	            	Book book=new Book();
	            book.setBookName(rs.getString("id"));
	            book.setDateof(rs.getString("date"));
	             book.setAuthor("\n"+rs.getString("medicinedetails"));
//	                book.setPublisher(rs.getString("operations"));
	                book.setStock(rs.getString("disease"));
	               
	                booklist.add(book);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return booklist;
	}
}

