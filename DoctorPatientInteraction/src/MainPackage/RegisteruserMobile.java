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
 * Servlet implementation class RegisteruserMobile
 */
@WebServlet("/RegisteruserMobile")
public class RegisteruserMobile extends HttpServlet {
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
    public RegisteruserMobile() {
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
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String mobilenumber=request.getParameter("mobilenumber");
		
		
		
		 ArrayList<Book> books=new ArrayList<Book>();
		  books=getAllCountries(username,password,email,mobilenumber);
		  Gson gson = new Gson();
		  JsonElement element = gson.toJsonTree(books, new TypeToken<List<Countries>>() {}.getType());
JsonObject jsonObject=new JsonObject();

		  JsonArray jsonArray = element.getAsJsonArray();
		  jsonObject.add("jsonarrayval", jsonArray);
		  response.setContentType("application/json");
		  response.getWriter().print(jsonObject);
	
	
	}

	public  ArrayList<Book> getAllCountries(String username, String password, String email, String mobilenumber) {
	     //connection = con;
		
		
		String query = "insert into user values('" + username + "','" + email
				+ "','" + password + "','user','"+mobilenumber+"','"+"','')";
		int i=db1.getUpdate(query);

		
		
	        ArrayList<Book> booklist = new ArrayList<Book>();
	       
	    	
	    	     Book book=new Book();
	            if(i>0) { 
	            	
	            	
	            	
	            book.setBookName("1");
	           
	               
	               
	            }
	            else
	            {
	            	
	            	 book.setBookName("0");
	            }
	            booklist.add(book);
	         

	        return booklist;
	}
}

