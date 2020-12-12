package authentication;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseConnection;



/**
 * Servlet implementation class validate
 */
@WebServlet("/validate")
public class validate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public validate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter out = response.getWriter(); 
		String strStatus = "Username is available";
	        try{
	        	DatabaseConnection db = new DatabaseConnection();
	        	db.dbconnection();
	            System.out.println("Performing check");
	            String username = request.getParameter("userName");
	            String strQuery = "SELECT * FROM user WHERE username='" + username +"'";
	            ResultSet rs = db.getResultSet(strQuery);
	            boolean userExists = rs.next();
	            if (userExists){
	                strStatus = "Username already exists"; // Return Exist Msg
	            }
	        }catch(Exception e){
	            strStatus = "Some error occured. Try again"; // Return Err Msg
	            e.printStackTrace();
	        }
	        out.println(strStatus);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
