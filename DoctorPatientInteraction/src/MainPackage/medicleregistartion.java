package MainPackage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import Database.DatabaseConnection;

/**
 * Servlet implementation class medicleregistartion
 */
@WebServlet("/medicleregistartion")

@MultipartConfig(fileSizeThreshold=1024*1024*10,    // 10 MB 
maxFileSize=1024*1024*50,          // 50 MB
maxRequestSize=1024*1024*100)  
public class medicleregistartion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 String uploadFilePath;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public medicleregistartion() {
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
		String	 applicationPath = request.getSession().getServletContext().getRealPath("/");
		HttpSession session = request.getSession();
		  String augmentfilenamepath="";
	        // constructs path of the directory to save uploaded file
	         uploadFilePath = applicationPath + File.separator ;
		String uname = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String type = request.getParameter("type");
		String mobilenum = request.getParameter("mobile");
		PrintWriter out = response.getWriter();
		DatabaseConnection db = new DatabaseConnection();
		db.dbconnection();
		 String fileName = null;
		  for (Part part : request.getParts()) {
	        	
	            fileName ="medcerti"+uname+".jpg";// getFileName(part);
	            augmentfilenamepath=uploadFilePath + File.separator + fileName;
	            
	            part.write(uploadFilePath + File.separator + fileName);
		  }
		
		String query = "select * from medicleuser where username = '" + uname + "'";

		ResultSet rs = db.getResultSet(query);
		try {
			if (rs.next()) {

				out.print("<script type=\"text/javascript\">alert(" + uname
						+ " already in use);</script>");

				request.getRequestDispatcher("default.jsp").include(request,
						response);
			} else {
				
				
			
				
				
				
				query = "insert into medicleuser values('" + uname + "','" + email
						+ "','" + password + "','Doctor','"+mobilenum+"','"+"','"+fileName+"')";
				db.getUpdate(query);

				
				Random r=new Random();
			       String otp=new String();
			       for(int i=0;i<6;i++){
			    	   otp+=r.nextInt(10);
			       }
		            System.out.println(otp);
		            String OTP=otp;
			        SendSms.SendSms.Way2SMS(mobilenum, "Your OTP is "+ OTP);
			        
			        String query3="UPDATE medicleuser SET OTP = '"+OTP+"' WHERE username = '"+uname+"';";
			        System.out.print(query3);
			        db.getUpdate(query3);
				
				
				request.getRequestDispatcher("OTPvalidationmedicle.jsp").include(request,
						response);
				out.print("<script type=\"text/javascript\">alert(you are registered please log in);</script>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	 private String getFileName(Part part) {
	        String contentDisp = part.getHeader("content-disposition");
	        System.out.println("content-disposition header= "+contentDisp);
	        String[] tokens = contentDisp.split(";");
	        for (String token : tokens) {
	            if (token.trim().startsWith("filename")) {
	                return token.substring(token.indexOf("=") + 2, token.length()-1);
	            }
	        }
	        return "";
	    }
		}

