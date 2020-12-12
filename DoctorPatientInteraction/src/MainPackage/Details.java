package MainPackage;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Database.DatabaseConnection;



public class Details {

	DatabaseConnection db;
	
	public Details() {
		db = new DatabaseConnection();
		db.dbconnection();
	}
	
	public int getID(String tableName)
	{
		int id=0;
		try {
			String query = "SELECT MAX(ID) AS ID FROM "+tableName+";";
			ResultSet rs = db.getResultSet(query);
			
			if(rs.next())
				if(rs.getString("ID")!=null)	
				{
					id = rs.getInt("ID");
				}
				else
					id = 0;
			
			id = id + 1;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public static String getUploadFileName(String fileName,int id)
	{
		int i = fileName.lastIndexOf("\\");
		String name = fileName.substring(0, (i+1));
		name = name +id+"_"+fileName.substring(i+1);
		return name;
	}
	
	
}
