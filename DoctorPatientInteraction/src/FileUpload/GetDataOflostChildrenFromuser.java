package FileUpload;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Database.DatabaseConnection;



import com.google.gson.JsonObject;




/**
 * Servlet implementation class GetDataOflostChildrenFromuser
 */
@WebServlet("/GetDataOflostChildrenFromuser")
@MultipartConfig
public class GetDataOflostChildrenFromuser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 //public static String pathofdataset="D://lostprojectfromuser//";
     DatabaseConnection db1=new DatabaseConnection();
	 JsonObject jsonObject;
	 Connection con;
  	 String uploadFilePath;
  	  String filename="";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDataOflostChildrenFromuser() {
        super();
        con=db1.dbconnection();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String	 applicationPath = request.getSession().getServletContext().getRealPath("dataset1");
//		HttpSession session = request.getSession();
//		String username=(String)session.getAttribute("user");
	        // constructs path of the directory to save uploaded file
//	    String     uploadFilePath1 = applicationPath + File.separator ;
//	     System.out.println("upload "+uploadFilePath);
//		
//	    // pathofdataset="E://2016 2017 project backup//115 Android app for finding lost children//Java//FindingLostChildren//WebContent//dataset1//";
//		
//		String imageDataString=request.getParameter("TreeImages");
//		String lati=request.getParameter("lati");
//		String longi=request.getParameter("longi");
//		String username="a";//request.getParameter("username");
//		String address=request.getParameter("address");
//		String mobilenum=request.getParameter("mobilenum");
//	
//		int id =1;// d.getID("userresponse");
//		
//		System.out.println(lati+"_"+longi);
//		
//		 BufferedImage newImg;
//		  newImg = ImageUtils.decodeToImage(imageDataString);
//		  
//		   filename="j"+id+"-"+username+".jpg";
//		  
//	       // ImageIO.write(newImg, "jpg", new File(pathofdataset+filename));
//	        
//	        ImageIO.write(newImg, "jpg", new File(uploadFilePath1+filename));
	        
	        
	        
		String username=request.getParameter("usern");
		
		
		ServletContext servletContext = getServletContext();
        String contextPath = servletContext.getRealPath("/userimage");
       // Sys
		
	        System.out.println(contextPath);
	        InputStream in = request.getPart("bill").getInputStream();
	        
	       String filename= getFileName(request.getPart("bill"));
	       String usernameobt[]=filename.split("@");
	       System.out.println(usernameobt[0]);
	      //  InputStream in = request.getInputStream();
	        
	       
	       String qry="insert into imagesofuser(username,imagename) values('"+usernameobt[0]+"','"+filename+"')";
	       db1.getUpdate(qry);
	       
	        OutputStream out = new FileOutputStream(contextPath+"\\"+filename+".jpg");			
		    			
	        byte[] buffer = new byte[4096];

	        long count = 0L;
	        int n = 0;

	        while (-1 != (n = in.read(buffer))) {
	        	out.write(buffer, 0, n);
	            count += n;
	        }

	        out.flush();
	        out.close();
				
	}
	
	 private String getFileName(Part part) {
	        String contentDisp = part.getHeader("content-disposition");
	        System.out.println("content-disposition header= "+contentDisp);
	        String[] tokens = contentDisp.split(";");
	        for (String token : tokens) {
	            if (token.trim().startsWith("filename")) {
	            	String filenme=token.substring(token.indexOf("=") + 2, token.length()-1);
	            	String filesplit[]=filenme.trim().split("/");
	            	//String op=
	                return filesplit[6];
	            }
	        }
	        return "";
	    }
}
