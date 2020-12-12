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
 * Servlet implementation class updatepdiseseinfo
 */
@WebServlet("/updatepdiseseinfo")
public class updatepdiseseinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updatepdiseseinfo() {
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
		String disease = request.getParameter("disease");
		String information = request.getParameter("information");
		String medicinedetails = request.getParameter("medicinedetails");
		String medicine = request.getParameter("medicine");
		String id=request.getParameter("id");
		DatabaseConnection db = new DatabaseConnection();
		db.dbconnection();
		
		String query="";	
		query="update mediclediseaseinfo set medicinedetails='"+medicinedetails+"',information='"+information+"',disease='"+disease+"' where id="+id+"";
//		query = "insert into userprescription(username,doctor,date,medicinedetails,disease) values('" + uname1 + "','"+uname+"','" + date.toString()
//				+ "','" + medicine + "','"+patienttrouble+"')";
		System.out.println(query);
		
		db.getUpdate(query);
		
		
		request.getRequestDispatcher("updatediseaseinfo.jsp").include(request,
				response);
	
	}

}
