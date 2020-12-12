package MainPackage;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;


import Database.DatabaseConnection;


public class User {

	DatabaseConnection db1;
	
	
	public User()
	{
		db1 = new DatabaseConnection();
		db1.dbconnection();
	}
	
	public boolean chkDuplicat(String username)
	{
		boolean flage = false;
		try {
			
			String query = "{? = call validateUsername(?)}";
			CallableStatement cstm = db1.callStatement(query);
			cstm.registerOutParameter(1, java.sql.Types.TINYINT);
			cstm.setString(2, username);
			cstm.execute();
			int outVal = cstm.getInt(1);
			
			if(outVal==1)
				flage = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flage;
	}
	
	public boolean register(String fullname, String address, String role,String gender, int age
			, String email, String mobile, String username, String password) {
		boolean flage = false;
		
		try {
			Details d = new Details();
			int id = d.getID("UserTable");
			
		
			
			String query = "{call registration(?,?,?,?,?,?,?,?,?,?)}";
			
			CallableStatement cstm = db1.callStatement(query);
			cstm.setInt(1, id);
			cstm.setString(2, fullname);
			cstm.setString(3, address);
			cstm.setString(4, role);
			cstm.setString(5, gender);
			cstm.setInt(6, age);
			cstm.setString(7, email);
			cstm.setString(8, mobile);
			cstm.setString(9, username);
			cstm.setString(10, password);
			
			int i = cstm.executeUpdate();
			
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flage;
	}
	
	public boolean verifyKey(String key) {
        boolean flage = false;
//        
//		try {
//			key = DES.encrypt(key);
//			String query = "{? = call verifyKey(?)}";
//			CallableStatement cstm = db1.callStatement(query);
//			cstm.registerOutParameter(1, java.sql.Types.TINYINT);
//			cstm.setString(2, key);
//			cstm.execute();
//			int outVal = cstm.getInt(1);
//			
//			if(outVal==1)
//				flage = true;
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
       return flage;
    }
	
	public boolean insertKey(int id, String key, int distributerId)
	{
		boolean flage = false;
		try
		{
			String query = "{call provideKey(?,?,?)}";
			
			CallableStatement cstm = db1.callStatement(query);
			cstm.setInt(1, id);
			cstm.setString(2, key);
			cstm.setInt(3, distributerId);
			
			int i = cstm.executeUpdate();
			if(i==1)
				flage = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flage;
	}
	
	public boolean validateLogin(String username, String password)
	{
		boolean flage = false;
		try {
			
			
			String query = "{? = call validateLogin(?,?)}";
			CallableStatement cstm = db1.callStatement(query);
			cstm.registerOutParameter(1, java.sql.Types.TINYINT);
			cstm.setString(2, username);
			cstm.setString(3, password);
			cstm.execute();
			int outVal = cstm.getInt(1);
			
			if(outVal==1)
				flage = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flage;
	}
	
	
	public ArrayList<String> getDetails(String username)
	{
		ArrayList<String> details = new ArrayList<String>();
		try {
			
			
			String query = "{call getUserDetailsByUsername(?,?,?,?,?,?,?,?,?,?)}";
			CallableStatement cstm = db1.callStatement(query);
			
			cstm.setString(1, username);
			cstm.registerOutParameter(2, java.sql.Types.INTEGER);
			cstm.registerOutParameter(3, java.sql.Types.VARCHAR);
			cstm.registerOutParameter(4, java.sql.Types.VARCHAR);
			cstm.registerOutParameter(5, java.sql.Types.VARCHAR);
			cstm.registerOutParameter(6, java.sql.Types.VARCHAR);
			cstm.registerOutParameter(7, java.sql.Types.INTEGER);
			cstm.registerOutParameter(8, java.sql.Types.VARCHAR);
			cstm.registerOutParameter(9, java.sql.Types.VARCHAR);
			cstm.registerOutParameter(10, java.sql.Types.VARCHAR);
			
			cstm.execute();
			
			if(cstm.getInt(2)>0)
			{
				details.add(cstm.getInt(2)+""); // id
				details.add(cstm.getString(3)); // Fullname
				details.add(cstm.getString(4)); // Address
				details.add(cstm.getString(5)); // Role
				details.add(cstm.getString(6)); // Gender
				details.add(cstm.getInt(7)+""); // Age
				details.add(cstm.getString(8)); // Email
				details.add(cstm.getString(9)); // Mobile
				String password = cstm.getString(10);
				
				details.add(password); // Password
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
		return details;
	}
	
	
	public int getUserId(String username)
	{
		int id=0;
		try {
			
			String query = "{? = call getUserId(?)}";
			CallableStatement cstm = db1.callStatement(query);
			cstm.registerOutParameter(1, java.sql.Types.INTEGER);
			cstm.setString(2, username);
			cstm.execute();
			id= cstm.getInt(1);	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return id;
	}
	
	public boolean update(String fullname, String address, String gender, int age
			, String email, String mobile, String username, String password) {
		boolean flage = false;
		
		try {
			int id = getUserId(username);
			
						
			String query = "select * from userTable where username='"+username+"' AND id<>"+id;
			
			
			ResultSet rs = db1.getResultSet(query);
			if(rs.next())
			{
				flage = false;
			}
			else
			{
				query = "{call updateUser(?,?,?,?,?,?,?,?,?)}";
				
				CallableStatement cstm = db1.callStatement(query);
				cstm.setInt(1, id);
				cstm.setString(2, fullname);
				cstm.setString(3, address);
				cstm.setString(4, gender);
				cstm.setInt(5, age);
				cstm.setString(6, email);
				cstm.setString(7, mobile);
				cstm.setString(8, username);
				cstm.setString(9, password);
				
				int i = cstm.executeUpdate();
				
				
				if(i==1)
					flage = true;
				
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flage;
	}
	
	
	public ArrayList<String> getUserList()
	{
		ArrayList<String> list = new ArrayList<String>();
		try {
			
			String query = "SELECT ID,FullName,Username FROM UserTable WHERE Role='agent'";
			ResultSet rs = db1.getResultSet(query);
			while(rs.next())
				list.add(rs.getString("Fullname")+" ("+rs.getString("username")+")");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<Integer> getUserID()
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		try {
			
			String query = "SELECT ID FROM UserTable WHERE Role='agent'";
			ResultSet rs = db1.getResultSet(query);
			while(rs.next())
				list.add(rs.getInt("ID"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	
	public String getFullName(int id)
	{
		String name="";
		try
		{
			String query = "SELECT FullName FROM UserTable WHERE ID="+id+"";
			ResultSet rs = db1.getResultSet(query);
			if(rs.next())
			{
				name = rs.getString("FullName");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return name;
	}
	
	public String getUserName(int id)
	{
		String name="";
		try
		{
			String query = "SELECT username FROM UserTable WHERE ID="+id+"";
			ResultSet rs = db1.getResultSet(query);
			if(rs.next())
			{
				name = rs.getString("username");
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return name;
	}
	
	
	
	public ArrayList<Object> getAllDistributerDetails()
	{
		ArrayList<Object> details = new ArrayList<Object>();
		try {
			
			String query = "SELECT * FROM UserTable WHERE Role='distributer'";
			ResultSet rs = db1.getResultSet(query);
			while(rs.next())
			{
				ArrayList<String> temp = new ArrayList<String>();
					
				temp.add(rs.getString("ID"));
				temp.add(rs.getString("FullName"));
				
				details.add(temp);
			}
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return details;
	}
	
	
	
	
	public static void main(String[] args)
	{
		User u = new User();
		u.getDetails("DLkUrbHKpzo=");
		//u.chkDuplicat("mujeeb");
		
		System.out.println("Done");
	}

	public void assignedBook(String username, String password, Date dt1, Date dt2) {
		
		
		
		
		
	}
}
