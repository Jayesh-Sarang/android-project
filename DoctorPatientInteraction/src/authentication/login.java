package authentication;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Database.DatabaseConnection;



/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("username");
		String password = request.getParameter("password");

		System.out.print(uname + password);

		PrintWriter out = response.getWriter();
		

		boolean flag = true;

		// //////////////////===========UserName
		// Analysis====================//////////////////////////
		Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(uname);

		System.out.println(uname);
		int count = 0;
		while (m.find()) {
			flag = false;
			count = count + 1;
			System.out.println("position " + m.start() + ": "
					+ uname.charAt(m.start()));
		}
		System.out.println("There are " + count + " special characters");

		// //////////////////===========PassWord
		// Analysis====================//////////////////////////
		int pcount = 0;
		m = p.matcher(password);

		System.out.println(password);
		count = 0;
		while (m.find()) {
			flag = false;
			pcount = pcount + 1;
			System.out.println("position " + m.start() + ": "
					+ uname.charAt(m.start()));
		}
		System.out.println("There are " + pcount + " special characters");

		if (flag) {
			try {
				DatabaseConnection db = new DatabaseConnection();
				db.dbconnection();
				String query = "select * from user where username = '" + uname
						+ "' and password = '" + password + "'";
				System.out.println(query);
				ResultSet rs = (ResultSet) db.getResultSet(query);

				if (rs.next()) {
					HttpSession session = request.getSession();
					if (rs.getString("type").equals("Doctor")) {
						session.setAttribute("type", "auditor");
						session.setAttribute("user", uname);
						session.setAttribute("emailid", rs.getString("emailid"));
						request.getRequestDispatcher("Doctorindex.jsp").include(
								request, response);
						
					}
					else
					{
						session.setAttribute("user", uname);
						session.setAttribute("emailid", rs.getString("emailid"));
						request.getRequestDispatcher("Userfile.jsp").include(
								request, response);
						
					}

				
//					session.setAttribute("user", rs.getString("type"));
					
				
				}

				else {
					request.getRequestDispatcher("default.jsp").include(request,
							response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Wrong Input');");
			out.println("</script>");
		}

	}

}
