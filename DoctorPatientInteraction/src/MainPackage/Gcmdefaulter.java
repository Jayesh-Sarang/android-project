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

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class Gcmdefaulter
 */
@WebServlet("/Gcmdefaulter")
public class Gcmdefaulter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Put your Google API Server Key here
	private static final String GOOGLE_SERVER_KEY = "AIzaSyA28jwhxU22ttlxQe7vqaBu1xIo95iIGGo";
	static final String MESSAGE_KEY = "price";
	static final String REG_ID_STORE = "E://GCMRegId.txt";
	DatabaseConnection db1=new DatabaseConnection();
	 JsonObject jsonObject;
	 Connection con;
	static List<String> regIdList = new ArrayList<String>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Gcmdefaulter() {
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


		MulticastResult result = null;

		//String share ="APA91bHYUQYByFOwIIl0dcfGVMh9Qio_dEv1UjyKjf-S82XXkI2jQvHZH9Vcuy0qrEJtBi_3hsiTkQiXanuh0-rruu5Z3RQLxuGPaB8X8_RHxLjtQ1BIg364_C7aPbDHYBuxHaCmsFXzAQfiF0hfeqTkALLknxGdqA";//"APA91bHYUQYByFOwIIl0dcfGVMh9Qio_dEv1UjyKjf-S82XXkI2jQvHZH9Vcuy0qrEJtBi_3hsiTkQiXanuh0-rruu5Z3RQLxuGPaB8X8_RHxLjtQ1BIg364_C7aPbDHYBuxHaCmsFXzAQfiF0hfeqTkALLknxGdqA" ;//request.getParameter("shareRegId");
		String share ="APA91bHnp2ejVgSFnxnxfv426_u4pzE1ZOKXR6vl5RczHvEzw1MhMljZcbbGsMY1VPhoFJMAnktuy53gLqynwI9yIqRguXACRK7tdVnSsrm2Qp0Ug2UqgXUUdrrIsKJ_VnMRA-Vr01m2OshzKmWVrR4Nc2d5xqssC4FMdBhIZ_aPRMuIvGcfHGs";
		// GCM RedgId of Android device to send push notification

		

			try {
				String year=request.getParameter("year");
				String rollnum=request.getParameter("rollnum");
				String userMessage ="You are in defaulter";// request.getParameter("msg");
				String branch=request.getParameter("branch");
				String senderid = request.getParameter("sender");
				Sender sender = new Sender(GOOGLE_SERVER_KEY);
				Message message = new Message.Builder().timeToLive(30)
						.delayWhileIdle(true).addData(MESSAGE_KEY, userMessage)
						.build();
			
				String query="insert into defaulter values(?)";

	    	  		String splitt[]=rollnum.split(",");
	    	  		for(int i=0;i<splitt.length;i++)
	    	  		{
	    	  			 PreparedStatement psmt;
	    	  	   	   	
	    	   	   			try {
	    	   					psmt = con.prepareStatement(query);
	    	   					 psmt.setString(1,splitt[i]);
//	    	   		   		        psmt.setString(2, userMessage+" Sender: "+senderid);
//	    	   		   		  psmt.setString(3,branch);
//	    			   		        psmt.setString(4, senderid);
	    	   		   		      
	    	   		   		      
	    	   		                   int im = psmt.executeUpdate();
	    	   				} catch (SQLException e) {
	    	   					// TODO Auto-generated catch block
	    	   					e.printStackTrace();
	    	   				}
	    	   	   			
	    	   	   	  String sql="select * from usertable1 WHERE name='"+splitt[i]+"'";
	    				    	
			    	PreparedStatement ps1;
			    	try {
			    		ps1 = con.prepareStatement(sql);
			    		// ps1.setString(1, "1" );
			    		    
			    	     ResultSet rs=ps1.executeQuery();
			            while(rs.next()) { 
			            	
			            	regIdList.add(rs.getString("regId"));
			            	 
			              
			            }
			        } catch (SQLException e) {
			            e.printStackTrace();
			        }


	    						
	    	  		}
	    	  		
   	          
				//regIdList.addAll(regIdSet);
//				String mine="APA91bHnp2ejVgSFnxnxfv426_u4pzE1ZOKXR6vl5RczHvEzw1MhMljZcbbGsMY1VPhoFJMAnktuy53gLqynwI9yIqRguXACRK7tdVnSsrm2Qp0Ug2UqgXUUdrrIsKJ_VnMRA-Vr01m2OshzKmWVrR4Nc2d5xqssC4FMdBhIZ_aPRMuIvGcfHGs";
//				regIdList.add(mine.trim());
//				String emu="APA91bG6H_ce3YbpACyxEFJbXLY84ov52lNiH7R25VVNJ3Iug0OfRWpRbEEDm5wXeNTKGaTY-ZV4RNhikJ-kxWADvVeyUBoKw5x1zuqDlF30LCAbVBv7WwBD8-HQhIIeWgnnSDAeWGMdVSqCWVEb6HxmDAxTjXp9jg";
//				regIdList.add(emu.trim());
				
				
				
				
				
				
				result = sender.send(message, regIdList, 1);
				request.setAttribute("pushStatus", result.toString());
			} catch (IOException ioe) {
				ioe.printStackTrace();
				request.setAttribute("pushStatus",
						"RegId required: " + ioe.toString());
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("pushStatus", e.toString());
			}
			request.getRequestDispatcher("index.jsp")
					.forward(request, response);
		
	
	}

}
