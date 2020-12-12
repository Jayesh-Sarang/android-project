package MainPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Database.DatabaseConnection;

/**
 * Servlet implementation class userprescription
 */
@WebServlet("/userprescription")
public class userprescription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userprescription() {
        super();
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

		 HttpSession session=request.getSession();
		
		String username=(String)session.getAttribute("user"); 
		String uname =username;// request.getParameter("username");
		String uname1 = request.getParameter("username");
		String patienttrouble = request.getParameter("patienttrouble");
		String medicine = request.getParameter("medicine");
		String apt = request.getParameter("apt");
		String servicecharges = request.getParameter("servicecharges");
		PrintWriter out = response.getWriter();
		DatabaseConnection db = new DatabaseConnection();
		db.dbconnection();
//		String query = "select * from user where username = '" + uname + "'";
//
//		ResultSet rs = db.getResultSet(query);
		
			
		  Date date = new Date();
	        
	       // display time and date using toString()
	       System.out.println(date.toString());
				
			
				
		String query="";	
				
				query = "insert into userprescription(username,doctor,date,medicinedetails,disease) values('" + uname1 + "','"+uname+"','" + date.toString()
						+ "','" + medicine + "','"+patienttrouble+"')";
				System.out.println(query);
				
				db.getUpdate(query);

				
				
			       
				
				request.getRequestDispatcher("Userprescription.jsp").include(request,
						response);
				out.print("<script type=\"text/javascript\">alert(you are registered please log in);</script>");
			
				
		

	
	
		
		
		
		
	}

}
