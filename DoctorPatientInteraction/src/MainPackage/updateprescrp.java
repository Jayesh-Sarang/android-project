package MainPackage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseConnection;

/**
 * Servlet implementation class updateprescrp
 */
@WebServlet("/updateprescrp")
public class updateprescrp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateprescrp() {
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
		String medicine = request.getParameter("medicine");
		String id=request.getParameter("id");
		DatabaseConnection db = new DatabaseConnection();
		db.dbconnection();
		
		String query="";	
		query="update userprescription set medicinedetails='"+medicine+"' where id="+id+"";
//		query = "insert into userprescription(username,doctor,date,medicinedetails,disease) values('" + uname1 + "','"+uname+"','" + date.toString()
//				+ "','" + medicine + "','"+patienttrouble+"')";
		System.out.println(query);
		
		db.getUpdate(query);
		
		
		request.getRequestDispatcher("updateprescrption.jsp").include(request,
				response);
	}

}
