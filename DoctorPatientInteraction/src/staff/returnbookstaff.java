package staff;

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
import MainPackage.Book;
import MainPackage.Countries;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class returnbookstaff
 */
@WebServlet("/returnbookstaff")
public class returnbookstaff extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String methodname="";
	DatabaseConnection db1=new DatabaseConnection();
	 JsonObject jsonObject;
	 Connection con;
	 String sendToMObile;
	    ArrayList<Book> books=new ArrayList<Book>();
	    String bookid="1";
	    String rollnumber="1";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public returnbookstaff() {
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

		 ArrayList<Book> books=new ArrayList<Book>();
		 bookid=request.getParameter("id");
		 rollnumber=request.getParameter("rollnumber");
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
	        String sql="select * from book where id='"+bookid+"'";
	    	int numberafterbook=-1;
	    	PreparedStatement ps1;
	    	try {
	    		ps1 = con.prepareStatement(sql);
	    		// ps1.setString(1, "1" );
	    		    
	    	     ResultSet rs=ps1.executeQuery();
	            while(rs.next()) { 
	            	
	            	
	            	Book book=new Book();
	           
	               // book.setStock(rs.getString("stock"));
	                numberafterbook=Integer.parseInt(rs.getString("stock"))+1;
	                
	                if(numberafterbook<=0)
	                {
	                	book.setMessage("Book is not in stock");
	                }
	                else
	                {
	                	
	                	  String query2="delete from issuebook where rollnumber='"+rollnumber+"' and id='"+bookid+"'";

  		        		
  		        		
  		                PreparedStatement psmt11;
  		        	
  		        	
  		        				psmt11 = con.prepareStatement(query2);
  		        				
  		        	   		     
  		        	   		     
  		        	   		      
  		        	                   int i2 = psmt11.executeUpdate();
	                	
	                String query1="update book set stock='"+numberafterbook+"' where id='"+bookid+"'";

	        		
	        		
	                PreparedStatement psmt;
	        	
	        			try {
	        				psmt = con.prepareStatement(query1);
	        				
	        	   		     
	        	   		     if(i2>0){
	        	   		      
	        	                   int i = psmt.executeUpdate();
	        	                   if(i>0)
	        	                   {
	        	                	   book.setMessage("Successfully Returned");
	        	                   }
	        	                   else
	        	                   {
	        	                	   book.setMessage("Failure occured try again");
	        	                   }
	        	                 
	        	   		     }
	        	   		     else
	        	   		     {
	        	   		    	book.setMessage("Failure occured try again");
	        	   		     }
	        	                   
	        			} catch (SQLException e) {
	        				// TODO Auto-generated catch block
	        				e.printStackTrace();
	        			}
	                }
	                booklist.add(book);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	     
	return booklist;
	}
}
