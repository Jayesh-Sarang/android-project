package MainPackage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Database.DatabaseConnection;

/**
 * Servlet implementation class deletemedicle
 */
@WebServlet("/deletemedicle")
public class deletemedicle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deletemedicle() {
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
		 DatabaseConnection db = new DatabaseConnection();
			db.dbconnection();
			String username=request.getParameter("id");
			  String query3="delete from medicleuser  WHERE username = '"+username+"';";
		        System.out.print(query3);
		        db.getUpdate(query3);
		        request.getRequestDispatcher("deletemediclestore.jsp").include(request,
						response);
	
	}

}
