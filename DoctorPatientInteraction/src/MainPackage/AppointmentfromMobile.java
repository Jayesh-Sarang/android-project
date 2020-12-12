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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import Database.DatabaseConnection;

/**
 * Servlet implementation class AppointmentfromMobile
 */
@WebServlet("/AppointmentfromMobile")
public class AppointmentfromMobile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseConnection db1;
	 JsonObject jsonObject;
	 Connection con;
	 String message1=""; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppointmentfromMobile() {
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
		// TODO Auto-generated method stub
		String usernames=request.getParameter("username");
		String emails=request.getParameter("emails");
		String phoneString=request.getParameter("phoneString");
		String doctorsandspec=request.getParameter("hospitalname");
		String dates=request.getParameter("dategot");
		String times=request.getParameter("timegot");
	//	String dividedoct[]=doctorsandspec.split(":");
		
		
		String selectphonenumber="";
		try {
			 selectphonenumber=getphonenum(usernames);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
String query1="insert into multipleappointment(username,emailid,phonenumber,doctor,specialization,date,time,status) values(?,?,?,?,?,?,?,?)";
//String messqge1="Appointment received for "+usernames+" on "+dates+" at "+times+" for "+dividedoct[1];
	//	String mfg=messqge1.replaceAll("[\\t\\n\\r]"," ");
//		message=mfg;//"Appointment received for "+usernames+" on "+dates+" at "+times+" for "+dividedoct[1];
	        PreparedStatement psmt1;
	        InputStream is = null;
				try {
					psmt1 = con.prepareStatement(query1);
					 psmt1.setString(1,usernames);
					 psmt1.setString(2,emails);
					 psmt1.setString(3,selectphonenumber);
					 psmt1.setString(4,"");
					 psmt1.setString(5,"");
					 psmt1.setString(6,dates);
					 psmt1.setString(7,times);
					 psmt1.setString(8,"pending");
					 
					
		   		
		   		 
			        
		   		      
		                   int i1 = psmt1.executeUpdate();
		                   
		                   
		                   ArrayList<Book> booklist = new ArrayList<Book>();
		        	       
		       	    	
		  	    	     Book book=new Book();
		  	            if(i1>0) { 
		  	            	
		  	            	
		  	            	
		  	            book.setBookName("1");
		  	           
		  	               
		  	               
		  	            }
		  	            else
		  	            {
		  	            	
		  	            	 book.setBookName("0");
		  	            }
		  	            booklist.add(book);
		  	         

		  	          
		  			  Gson gson = new Gson();
		  			  JsonElement element = gson.toJsonTree(booklist, new TypeToken<List<Countries>>() {}.getType());
		  	JsonObject jsonObject=new JsonObject();

		  			  JsonArray jsonArray = element.getAsJsonArray();
		  			  jsonObject.add("jsonarrayval", jsonArray);
		  			  response.setContentType("application/json");
		  			  response.getWriter().print(jsonObject);
		                   
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
		
	}

	private String getphonenum(String usernames) throws SQLException {
		String phonenum="";
		String qry="select * from user where username='"+usernames+"'";
		ResultSet rs=db1.getResultSet(qry);
		while(rs.next())
		{
			phonenum=rs.getString("mobilenumber");	
		}
		return phonenum;
	}

}
