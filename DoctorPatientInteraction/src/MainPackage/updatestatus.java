package MainPackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseConnection;

/**
 * Servlet implementation class updatestatus
 */
@WebServlet("/updatestatus")
public class updatestatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	DatabaseConnection db1=new DatabaseConnection();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updatestatus() {
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
		
		String query="update appointment set  status='"+status+"' where id="+regId;

		
        PreparedStatement psmt;
	
			try {
				psmt = con.prepareStatement(query);
				
	   		     
	   		      
	                   int i = psmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	
	}

}
