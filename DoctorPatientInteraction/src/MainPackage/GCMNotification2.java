package MainPackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseConnection;
import SendSms.SendSms;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;
import com.google.gson.JsonObject;

@WebServlet("/GCMNotification2")
public class GCMNotification2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Put your Google API Server Key here
	private static final String GOOGLE_SERVER_KEY = "AIzaSyA28jwhxU22ttlxQe7vqaBu1xIo95iIGGo";
	static final String MESSAGE_KEY = "price";
	static final String REG_ID_STORE = "E://GCMRegId.txt";
	DatabaseConnection db1=new DatabaseConnection();
	 JsonObject jsonObject;
	 Connection con;
	static List<String> regIdList = new ArrayList<String>();
	Message message;
	public GCMNotification2() {
		super();
		 con=db1.dbconnection();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		MulticastResult result = null;

		//String share ="APA91bHYUQYByFOwIIl0dcfGVMh9Qio_dEv1UjyKjf-S82XXkI2jQvHZH9Vcuy0qrEJtBi_3hsiTkQiXanuh0-rruu5Z3RQLxuGPaB8X8_RHxLjtQ1BIg364_C7aPbDHYBuxHaCmsFXzAQfiF0hfeqTkALLknxGdqA";//"APA91bHYUQYByFOwIIl0dcfGVMh9Qio_dEv1UjyKjf-S82XXkI2jQvHZH9Vcuy0qrEJtBi_3hsiTkQiXanuh0-rruu5Z3RQLxuGPaB8X8_RHxLjtQ1BIg364_C7aPbDHYBuxHaCmsFXzAQfiF0hfeqTkALLknxGdqA" ;//request.getParameter("shareRegId");
		String share ="APA91bHnp2ejVgSFnxnxfv426_u4pzE1ZOKXR6vl5RczHvEzw1MhMljZcbbGsMY1VPhoFJMAnktuy53gLqynwI9yIqRguXACRK7tdVnSsrm2Qp0Ug2UqgXUUdrrIsKJ_VnMRA-Vr01m2OshzKmWVrR4Nc2d5xqssC4FMdBhIZ_aPRMuIvGcfHGs";
		// GCM RedgId of Android device to send push notification

		

			try {
				String time=request.getParameter("time");
				String userMessage ="";// request.getParameter("msg");
				String branch=request.getParameter("branch");
				String senderid = request.getParameter("sender");
				Sender sender = new Sender(GOOGLE_SERVER_KEY);
//				Message message = new Message.Builder().timeToLive(30)
//						.delayWhileIdle(true).addData(MESSAGE_KEY, userMessage)
//						.build();
			
				
				
				//regIdList.addAll(regIdSet);
//				String mine="APA91bHnp2ejVgSFnxnxfv426_u4pzE1ZOKXR6vl5RczHvEzw1MhMljZcbbGsMY1VPhoFJMAnktuy53gLqynwI9yIqRguXACRK7tdVnSsrm2Qp0Ug2UqgXUUdrrIsKJ_VnMRA-Vr01m2OshzKmWVrR4Nc2d5xqssC4FMdBhIZ_aPRMuIvGcfHGs";
//				regIdList.add(mine.trim());
//				String emu="APA91bG6H_ce3YbpACyxEFJbXLY84ov52lNiH7R25VVNJ3Iug0OfRWpRbEEDm5wXeNTKGaTY-ZV4RNhikJ-kxWADvVeyUBoKw5x1zuqDlF30LCAbVBv7WwBD8-HQhIIeWgnnSDAeWGMdVSqCWVEb6HxmDAxTjXp9jg";
//				regIdList.add(emu.trim());
				
				
			
				
				String sql="select * from userprescription WHERE medicinedetails like '%"+time+"%'";
			    	
			    	PreparedStatement ps1;
			    	try {
			    		ps1 = con.prepareStatement(sql);
			    		// ps1.setString(1, "1" );
			    		    
			    	     ResultSet rs=ps1.executeQuery();
			            while(rs.next()) { 
			            	String name=rs.getString("username");
			            	String medicine=rs.getString("medicinedetails");
			            	  String sql1="select * from user WHERE username='"+name+"'";
						    	
						    	PreparedStatement ps11;
						    	
						    		ps11 = con.prepareStatement(sql1);
						    		// ps1.setString(1, "1" );
						    		    
						    	     ResultSet rs11=ps11.executeQuery();
						            while(rs11.next()) { 
						            	 String phonenum=rs11.getString("mobilenumber");
//						            	 message = new Message.Builder().timeToLive(30)
//												.delayWhileIdle(true).addData(MESSAGE_KEY, medicine)
//												.build();
			            	
						            	
					       	      	       SendSms.Way2SMS(phonenum, String.valueOf(medicine));
					       		            	
			            	//regIdList.add(rs11.getString("regId"));
			            	 
						            }
						    	
			            }
			        } catch (SQLException e) {
			            e.printStackTrace();
			        }


				
				
				
				//result = sender.send(message, regIdList, 1);
			//	request.setAttribute("pushStatus", result.toString());
			}  catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("pushStatus", e.toString());
			}
			request.getRequestDispatcher("index.jsp")
					.forward(request, response);
		
	}

	
}
