package MainPackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseConnection;
import mesage91.SendSms;


/**
 * Servlet implementation class changestatus
 */
@WebServlet("/changestatus")
public class changestatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	DatabaseConnection db1=new DatabaseConnection();
	String message1="";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changestatus() {
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
		

		String regId = request.getParameter("id");
		String status = request.getParameter("status");
		String stat = request.getParameter("stat");
			
		if("Approved".equalsIgnoreCase(stat)){
		
		
	String query1="select phonenumber from multipleappointment where id="+regId;
		
	
	
	PreparedStatement ps11;
  	
		
	try {
		ps11 = con.prepareStatement(query1);
		 ResultSet rs1=ps11.executeQuery();
	            if(rs1.next()) { 
	            	
	            	 String phonenum=rs1.getString("phonenumber");
	            	 SendSms.sendmessageto91(phonenum,String.valueOf("Your appointment has been approved by doctor "),"OTHOSP");
     	      	     //  SendSms.Way2SMS(phonenum, String.valueOf("Your appointment has been approved by doctor "));
     		            	
	            }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	
		}
	
	
		String query="update multipleappointment set  status='"+status+"',statusbyuser='"+stat+"' where id="+regId;

		System.out.println(query);
        PreparedStatement psmt;
	
			try {
				psmt = con.prepareStatement(query);
				
	   		     
	   		      
	                   int i = psmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
			request.getRequestDispatcher("ViewMyappointment.jsp").include(request,
					response);
		
		
	}

}
