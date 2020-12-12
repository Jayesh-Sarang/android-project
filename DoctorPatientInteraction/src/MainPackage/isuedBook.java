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
 * Servlet implementation class isuedBook
 */
@WebServlet("/isuedBook")
public class isuedBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String methodname="";
	DatabaseConnection db1=new DatabaseConnection();
	 JsonObject jsonObject;
	 Connection con;
	 String sendToMObile;
	    ArrayList<studentinfo> studentList=new ArrayList<studentinfo>();
	    studentinfo student=new studentinfo();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public isuedBook() {
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
String rollnumber=request.getParameter("rollnumber");
		
ArrayList<studentinfo> studentList=new ArrayList<studentinfo>();
studentList=getAllCountries(rollnumber);
		  Gson gson = new Gson();
		  JsonElement element = gson.toJsonTree(studentList, new TypeToken<List<Countries>>() {}.getType());
JsonObject jsonObject=new JsonObject();

		  JsonArray jsonArray = element.getAsJsonArray();
		  jsonObject.add("jsonarrayval", jsonArray);
		  response.setContentType("application/json");
		  response.getWriter().print(jsonObject);
	
	}
	public  ArrayList<studentinfo> getAllCountries(String rollnumber) {
	     //connection = con;
		ArrayList<studentinfo> studentList=new ArrayList<studentinfo>();
	        String sql="select * from issuebook where rollnumber='"+rollnumber+"'";
	    	
	    	PreparedStatement ps1;
	    	try {
	    		ps1 = con.prepareStatement(sql);
	    		// ps1.setString(1, "1" );
	    		    
	    	     ResultSet rs=ps1.executeQuery();
	            while(rs.next()) { 
	            	
	            	
	            	  studentinfo student=new studentinfo();
	            	  
	            	  String sqls="select * from book where id='"+rs.getString("id")+"'";
	            	  student.setBookid(rs.getString("id"));
	      	    	PreparedStatement ps11;
	      	    	
	      	    		ps11 = con.prepareStatement(sqls);
	      	    		// ps1.setString(1, "1" );
	      	    		    
	      	    	     ResultSet rs1=ps11.executeQuery();
	      	            while(rs1.next()) { 
	      	            	
	      	            	student.setBookname(rs1.getString("bookname"));
	      	            	
	      	            }
	            	  
	            	
	      	          student.setStartdate(rs.getString("startdate"));
	      	        student.setEnddate(rs.getString("enddate"));
	            	  
	            	  
	               
	                studentList.add(student);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return studentList;
	}
}
