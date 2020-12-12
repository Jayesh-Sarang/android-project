package inserting;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import Database.DatabaseConnection;

/**
 * Servlet implementation class insertmachinedetails
 */
@WebServlet("/insertmachinedetails")
public class insertmachinedetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseConnection db1;
	 JsonObject jsonObject;
	 Connection con;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertmachinedetails() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String doctorname=request.getParameter("doctorname");
		String hospitalname=request.getParameter("hospitalname");
		String availability=request.getParameter("availability");
		String education=request.getParameter("education");
		String operation=request.getParameter("operation");
		String about=request.getParameter("about");
		String doctorhandling=request.getParameter("doctorhandling");
		
		String query1="insert into machinedetails(machinename,hospitalname,machinemfg,machineexp,numberofoperation,about,doctorhandling) values(?,?,?,?,?,?,?)";


			
	        PreparedStatement psmt1;
	        InputStream is = null;
				try {
					psmt1 = con.prepareStatement(query1);
					 psmt1.setString(1,doctorname);
					 psmt1.setString(2,hospitalname);
					 psmt1.setString(3,availability);
					 psmt1.setString(4,education);
					 psmt1.setString(5,operation);
					 psmt1.setString(6,about);
					 psmt1.setString(7,doctorhandling);
					
		   		
		   		 
			        
		   		      
		                   int i1 = psmt1.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				RequestDispatcher rs = request.getRequestDispatcher("insertmachinedetails.jsp");
				rs.forward(request,response);
				
		
	
	}

}
