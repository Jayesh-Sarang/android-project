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
 * Servlet implementation class deletetiminginfo
 */
@WebServlet("/deletetiminginfo")
public class deletetiminginfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deletetiminginfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		 HttpSession session=request.getSession();
		 DatabaseConnection db = new DatabaseConnection();
			db.dbconnection();
			String id=request.getParameter("id");
		
		String query3="delete from doctoravail  WHERE id = "+id+"";
	        System.out.print(query3);
	        db.getUpdate(query3);
	        

			request.getRequestDispatcher("updatetiminginfo.jsp").include(request,
					response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session=request.getSession();
		 DatabaseConnection db = new DatabaseConnection();
			db.dbconnection();
			String id=request.getParameter("id");
		
		String query3="delete from doctoravail  WHERE id = "+id+"";
	        System.out.print(query3);
	        db.getUpdate(query3);
	        

			request.getRequestDispatcher("updatetiminginfo.jsp").include(request,
					response);
	}

}
