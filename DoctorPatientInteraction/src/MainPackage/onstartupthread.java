package MainPackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseConnection;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class onstartupthread
 */
@WebServlet("/onstartupthread")
public class onstartupthread extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 Timer sessTimer;
	 String methodname="";
		DatabaseConnection db1=new DatabaseConnection();
		 JsonObject jsonObject;
		 Connection con;
		 String sendToMObile;
		    ArrayList<Book> books=new ArrayList<Book>();
		    String bookid="1";
		    String rollnumber="1";
		    static List<String> regIdList = new ArrayList<String>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public onstartupthread() {
        super();
        con=db1.dbconnection();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long startTime = System.currentTimeMillis();
		long elapsedTime = 0L;
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-mm-dd");
		String inputString1 = "2015-01-24";
		String inputString2 = "2015-02-30";

		final String GOOGLE_SERVER_KEY = "AIzaSyA28jwhxU22ttlxQe7vqaBu1xIo95iIGGo";
		Sender sender = new Sender(GOOGLE_SERVER_KEY);
		 final String MESSAGE_KEY = "price";
		 MulticastResult result = null;
		
			
			try {
			   
			   
		        String sql="select * from issuebook";
		    	int numberafterbook=-1;
		    	PreparedStatement ps1;
		    

		    	ps1 = con.prepareStatement(sql);
		    		// ps1.setString(1, "1" );
		    		    
		    	     ResultSet rs=ps1.executeQuery();
		            while(rs.next()) { 
		            	
		            			inputString2=rs.getString("enddate");
		            			String id =rs.getString("id");
		            			String rollnumber=rs.getString("rollnumber");
		            			String bookname="";
		            			  String sqls="select * from usertable1 where rollnumber='"+rollnumber+"'";
		          		    	
		          		    	PreparedStatement ps11;
		          		    
		          		    		ps11 = con.prepareStatement(sqls);
		          		    		// ps1.setString(1, "1" );
		          		    		    
		          		    	     ResultSet rs1=ps11.executeQuery();
		          		    	     {
		          		    	    	  while(rs1.next()) { 
		          		    	    		  
		          		    	    		  
		          		    	    		 String sqle="select * from book where id='"+id+"'";
		          		   		    	
		          		   		    	PreparedStatement ps1e;
		          		   		    

		          		   		    	ps1e = con.prepareStatement(sqle);
		          		   		    		// ps1.setString(1, "1" );
		          		   		    		    
		          		   		    	     ResultSet rse=ps1e.executeQuery();
		          		   		            while(rse.next()) { 
		          		   		           bookname=rse.getString("bookname");
		          		   		            	
		          		   		            	
		          		   		            }
		          		    	    		  
		          		    	    		  
		          		    	    		regIdList.add(rs1.getString("regId"));
		          		    	    		Message message = new Message.Builder().timeToLive(30)
		          		    	  				.delayWhileIdle(true).addData(MESSAGE_KEY,"Book "+bookname+" Returning date is "+ inputString2)
		          		    	  				.build();
		          		    	    	  
		          		    	    		result = sender.send(message, regIdList, 1);
		          		    	    		System.out.println(result);
		          		    	    		
		          		    	    		String query="insert into message(rollnumber,message) values(?,?)";

		          		    	  		
		          		    	  		
		          		    	           PreparedStatement psmt;
		          		    	   	
		          		    	   			try {
		          		    					psmt = con.prepareStatement(query);
		          		    					 psmt.setString(1,rollnumber);
		          		    		   		        psmt.setString(2,"Book "+bookname+" Returning date is "+ inputString2);
		          		    		   		      
		          		    		   		      
		          		    		                   int i = psmt.executeUpdate();
		          		    				} catch (SQLException e) {
		          		    					// TODO Auto-generated catch block
		          		    					e.printStackTrace();
		          		    				}
		          		    	    		
		          		    	    	  }
		          		    	     }
		            			
		            			
		            }
			}  catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		    //perform db poll/check
		   System.out.println("ji");
		}
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
	}

}
