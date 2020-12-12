package MainPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseConnection;

/**
 * Servlet implementation class otpvalidationmedicle
 */
@WebServlet("/otpvalidationmedicle")
public class otpvalidationmedicle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public otpvalidationmedicle() {
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


		String uname = request.getParameter("OTP");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String type = request.getParameter("type");
		String mobilenum = request.getParameter("mobile");
		PrintWriter out = response.getWriter();
		DatabaseConnection db = new DatabaseConnection();
		db.dbconnection();
		String query = "select * from medicleuser where OTP = '" + uname + "'";

		ResultSet rs = db.getResultSet(query);
		try {
			if (!rs.next()) {

				out.print("<script type=\"text/javascript\">alert(" + ""
						+ " Wrong OTP);</script>");

				request.getRequestDispatcher("OTPvalidationmedicle.jsp").include(request,
						response);
			} else {
				
				
			
				
				
				
				
				
				
				request.getRequestDispatcher("medicleindex.jsp").include(request,
						response);
				out.print("<script type=\"text/javascript\">alert(you are registered please log in);</script>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	
	
	}

}
