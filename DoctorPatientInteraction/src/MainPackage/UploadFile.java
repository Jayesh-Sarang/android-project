package MainPackage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import Database.DatabaseConnection;



/**
 * Servlet to handle File upload request from Client
 * 
 * @author Javin Paul
 */
@WebServlet("/UploadFile")
public class UploadFile extends HttpServlet {
	 DatabaseConnection db1=new DatabaseConnection();
	 String filepath="";
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 Connection con;
		 con=db1.dbconnection();
		 filepath=request.getSession().getServletContext().getRealPath("/images/");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();

		// process only if its multipart content
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				List<FileItem> multiparts = new ServletFileUpload(
						new DiskFileItemFactory()).parseRequest(request);

				for (FileItem item : multiparts) {
					if (!item.isFormField()) {

						String name = new File(item.getName()).getName();
						System.out.println(name); // print with extenssion

						long sizeInBytes = item.getSize();
						System.out.println("byte" + sizeInBytes);
						double kilobytes = sizeInBytes / 1024.0;
						String kb = String.valueOf(kilobytes);
						System.out.println("byte :" + kilobytes);

						String fileNameWithOutExt = FilenameUtils
								.removeExtension(name);
						System.out.println(fileNameWithOutExt); // print

						path p = new path();
						String username = (String) session.getAttribute("user");

						File udir = new File(filepath);
//						if (!(udir.exists())) {
//							udir.mkdir();
//						}

						File a = new File(udir + "//" + name);
						String  absolutePath = udir + "//" + name;
						int i = 0;
						while (a.exists()) {
							i++;
							a = new File(udir + "//" + i + name);
							absolutePath = udir + "//" +i+ name;
							name = i+ name;
							
						}
				
						
						System.out.println(absolutePath);
						item.write(a);
						
						String query="insert into details values(?,?,?)";

		    	  		
		    	  		
				           PreparedStatement psmt;
				   	
				   			try {
								psmt = con.prepareStatement(query);
								 psmt.setString(1,username);
					   		        psmt.setString(2, name);
					   		  psmt.setString(3,filepath+"/"+name);
				   		     
					   		      
					   		      
					                   int mi = psmt.executeUpdate();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					
					
						
						
//						MD5 m = new MD5();
//						String hash = m.getHash(absolutePath);
////						absolutePath = absolutePath.replaceAll("\\", "\\");
//						System.out.println(absolutePath);
//						datamanager dm = new datamanager();
//						extnslector sx = new extnslector();
//						String LOgoPath = sx.extnslector(name);
//						dm.UploadLog(name.substring(0, name.lastIndexOf('.')), name, hash, username, kilobytes+"",LOgoPath);
//
//						
						
						
					}
					
				}
				
				

			} catch (Exception ex) {
				System.out.println(ex);
			}

		} else {
			request.setAttribute("message",
					"Sorry this Servlet only handles file upload request");
		}
	}
}