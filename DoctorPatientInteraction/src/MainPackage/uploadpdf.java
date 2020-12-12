package MainPackage;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;


import com.google.gson.JsonObject;

import Database.DatabaseConnection;


/**
 * Servlet implementation class sockettest
 */
@WebServlet("/uploadpdf")
@MultipartConfig
public class uploadpdf extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  private static ServerSocket serverSocket;
	  private static ServerSocket serverSocket1;
	    private static Socket clientSocket;
	    private static Socket clientSocket1;
	    private static InputStreamReader inputStreamReader;
	    private static BufferedReader bufferedReader;
	    private static String message;
	    DatabaseConnection db1=new DatabaseConnection();
		 JsonObject jsonObject;
		 Connection con;
		// TODO Auto-generated method stub
	    static int filesize=4506600;
        static int bytesRead;
        static int current=0;
        int i=0;
        int j=0;
        String filepath="G:\\code\\Java_Projects_Code\\uploadftp4\\WebContent\\images\\";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uploadpdf() {
        super();
        con=db1.dbconnection();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
filepath=request.getSession().getServletContext().getRealPath("/images/");
		 String img;
         img="00"+i;
         if(i>9&&i<=99)
         {
         	  img="0"+i;
         }
         else if(i>99)
         {
         	 img=String.valueOf(i);
         }
         if(i<180){
         System.out.println(img);
         }
		i++;
		String username=request.getParameter("name");
		String msg=request.getParameter("msg");
		String filename[]=msg.split("\\/");
		
			String query="insert into details values(?,?,?)";

    	  		
    	  		
	           PreparedStatement psmt;
	   	
	   			try {
					psmt = con.prepareStatement(query);
					 psmt.setString(1,username);
		   		        psmt.setString(2, filename[0]);
		   		  psmt.setString(3,filepath+"/"+filename[0]);
	   		     
		   		      
		   		      
		                   int i = psmt.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
		
		
		
		  InputStream in = request.getPart("userfile").getInputStream();
	        OutputStream out = new FileOutputStream(filepath+"/j"+filename[0]);
	        int c;
	         while ((c = in.read()) != -1) {
	            out.write(c);
	         }
	         in.close();
	         out.close();
	        CompressJPEGFile.createcompression(filepath+"/j"+filename[0],filepath+"/"+filename[0],username);
	         
	        
	        
	    
//	       if(msg.equals("changed")){
////	        sendmail("F:\\images\\Capture"+img+".jpg");
//	       }
//   	 serverSocket = new ServerSocket(4482);
//   	

//      
//           try {
//           	
//           	
//           	
//           	
//           	
//                System.out.println("Server started. Listening to the port 4444");
//               clientSocket = serverSocket.accept();   //accept the client connection
////               inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
////               bufferedReader = new BufferedReader(inputStreamReader); //get the client message
////               message = bufferedReader.readLine();
//
//               byte [] mybytearray  = new byte [filesize];
//               InputStream is = clientSocket.getInputStream();
//               String img;
//               img="00"+i;
//               if(i>9&&i<=99)
//               {
//               	  img="0"+i;
//               }
//               else if(i>99)
//               {
//               	 img=String.valueOf(i);
//               }
//               if(i<180){
//               System.out.println(img);
//               }
//              
//              
//               
////               FileOutputStream fos = new FileOutputStream("F:\\images\\Capture"+img+".jpg");
//             
//               
//               
//               
//              
//               
//               i++;
//             
////               BufferedOutputStream bos = new BufferedOutputStream(fos);
////               bytesRead = is.read(mybytearray,0,mybytearray.length);
////               current = bytesRead;
////System.out.println(bytesRead);
////               do {
////                  bytesRead =
////                     is.read(mybytearray, current, (mybytearray.length-current));
////                  if(bytesRead >= 0) current += bytesRead;
////               } while(bytesRead > -1);
//
//               
//       		OutputStream os = new FileOutputStream("F:\\images\\Capture"+img+".jpg");
//
//   			float quality = 0.2f;
//
//   			// create a BufferedImage as the result of decoding the supplied InputStream
//   			BufferedImage image = ImageIO.read(is);
//
//   			
//   			// get all image writers for JPG format
//   			Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
//
//   			if (!writers.hasNext())
//   				throw new IllegalStateException("No writers found");
//
//   			ImageWriter writer = (ImageWriter) writers.next();
//   			ImageOutputStream ios = ImageIO.createImageOutputStream(os);
//   			writer.setOutput(ios);
//
//   			ImageWriteParam param = writer.getDefaultWriteParam();
//
//   			// compress to a given quality
//   			param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
//   			param.setCompressionQuality(quality);
//
//   			// appends a complete image stream containing a single image and
//   		    //associated stream and image metadata and thumbnails to the output
//   			writer.write(null, new IIOImage(image, null, null), param);
//
//   			// close all streams
//   			is.close();
//   			os.close();
//   			ios.close();
//   			writer.dispose();
//               
//               
//               
////               bos.write(mybytearray, 0 , current);
////               bos.flush();
////bos.close();
//               System.out.println("end-start");    
//               
//               System.out.println(message);
//             
//               clientSocket.close();
////             compressfile("F:\\images\\Capture"+img+".jpg",img);
////               SimpleSendEmail smp = new SimpleSendEmail("inbotics.testing0@gmail.com", "inbotics.testing0@gmail.com", "Mobile Survillience", "Survillience camera moved", "inboticstesting","F:\\images\\Capture"+img+".jpg");
//              if(msg.equals("changed")){
//           		sendmail();
//              }
//               // receive file
//              
//           }
//           
//           catch(Exception e)
//           {
//               System.out.println(e);
//               e.printStackTrace();
//           }

         
       }

   
	
	private static void sendmail(String string) {
//		SimpleSendEmail smp = new SimpleSendEmail("inbotics.testing0@gmail.com", "inbotics.testing0@gmail.com", "Mobile Survillience", "Survillience camera moved", "inboticstesting",string);
	}
	  private static void rescale(BufferedImage bi) throws IOException {
	        int originalWidth = bi.getWidth();
	        int originalHeight = bi.getHeight();
	        int type = bi.getType() == 0? BufferedImage.TYPE_INT_ARGB : bi.getType();

	        //rescale 50%
	        BufferedImage resizedImage = new BufferedImage(originalWidth/2, originalHeight/2, type);
	        Graphics2D g = resizedImage.createGraphics();
	        g.drawImage(bi, 0, 0, originalWidth/2, originalHeight/2, null);
	        g.dispose();
	        g.setComposite(AlphaComposite.Src);
	        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	        g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
	        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
	        ImageIO.write(resizedImage, "jpg", new File("Lenna50.jpg"));
	    }

	    private static void compress(int compression, BufferedImage bi, String path)
	            throws FileNotFoundException, IOException {
	        Iterator<ImageWriter> i = ImageIO.getImageWritersByFormatName("jpeg");
	        ImageWriter jpegWriter = i.next();

	        // Set the compression quality
	        ImageWriteParam param = jpegWriter.getDefaultWriteParam();
	        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
	        param.setCompressionQuality(0.1f * compression);

	        // Write the image to a file
	        FileImageOutputStream out = new FileImageOutputStream(new File(path));
	        jpegWriter.setOutput(out);
	        jpegWriter.write(null, new IIOImage(bi, null, null), param);
	        jpegWriter.dispose();
	        out.close();
	    }

	
}
