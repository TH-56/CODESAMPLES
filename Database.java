package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database
{

	// Objects to be used for database access.
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rset = null;

    // This is the connector to the database, default port is 3306.
    private String url = "jdbc:mysql://127.0.0.1:3306/database";

    public Database(String username, String password)
    {
    	try
    	{
			// Makes the connection to the database.
			conn = DriverManager.getConnection(url, username, password);
	        
			// These will be used to send queries to the database.
	        stmt = conn.createStatement();
	        rset = stmt.executeQuery("SELECT VERSION()");
	
	        if(rset.next()) 
	        {
	            System.out.println("MySQL version: " + rset.getString(1) + "\n=====================\n");
	        }
		} 
		catch(SQLException ex) 
    	{
			// Handle any errors.
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}  	
    }
    
    public boolean createUser(String username, String password, String emailaddress) throws Exception
    {
    	// INSERT INTO users VALUE(username, password, emailaddress, lockcount, loggedin);
    	String insertNewUser = "INSERT INTO users VALUE('" + username + "', '" + password + "', '" + emailaddress + "', 0, 0);";
    	
    	try
    	{
    		// Works, but freezes program.
        	stmt.executeUpdate(insertNewUser);
        	return true;
    	}
    	
		catch(SQLException ex)
    	{
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return false;
		}
    }

    public String[] recoveryCheck(String username)
    {
    	String findUser = "SELECT * FROM users WHERE username ='" + username + "';";
    	
    	try
    	{
    		rset = stmt.executeQuery(findUser);
    		rset.next();
    		String emailAddress = rset.getString("emailaddress");
    		String password = rset.getString("password");
    		String[] information = new String[2];
    		information[0] = emailAddress;
    		information[1] = password;
    		return information;
    	}
    	
		catch(SQLException ex)
    	{
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return null;
		}
    }
    
    public String login(String inputtedUsername, String inputtedPassword)
    {
    	String findUser = "SELECT * FROM users WHERE username ='" + inputtedUsername + "';";
    	String updateStatus = "UPDATE users SET loggedin = 1 WHERE username = '" + inputtedUsername + "';";
    	
    	try
    	{
    		rset = stmt.executeQuery(findUser);
    		rset.next();
    		String password = rset.getString("password");
    		if(password.equals(inputtedPassword))
    		{
    			stmt.executeUpdate(updateStatus);
    			return "good";
    		}
    		else
    		{
    			return "passworderror";
    		}
    	}
    	
		catch(SQLException ex)
    	{
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return "usernameerror";
		}
    }
    
    public String logout(String username)
    {
    	String updateStatus = "UPDATE users SET loggedin = 0 WHERE username = '" + username + "';";
    	
    	try
    	{
    		stmt.executeUpdate(updateStatus);
    		return "good";
    	}
		catch(SQLException ex)
    	{
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return "error";
		}
    }
    
    public String increaseLockCount(String username)
    {
    	String findLockCount = "SELECT * FROM users WHERE username ='" + username + "';";
    	
    	try
		{
			rset = stmt.executeQuery(findLockCount);
			rset.next();
			int lockCount = rset.getInt("lockcount");
			lockCount++;
	    	String updateStatus = "UPDATE users SET lockcount = " + lockCount + " WHERE username = '" + username + "';";
    		stmt.executeUpdate(updateStatus);
    		
			if(lockCount >= 3)
			{
				return "lockaccount";
			}
			else
			{
				return "good";
			}
		}
    	catch (SQLException ex)
		{
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return "error";
		}
    }
    
    public String getStatus(String username)
    {
    	String findLockCount = "SELECT * FROM users WHERE username ='" + username + "';";
    	
    	try
		{
			rset = stmt.executeQuery(findLockCount);
			rset.next();
			int lockCount = rset.getInt("lockcount");
			
			if(lockCount >= 3)
			{
				return "locked";
			}
			else
			{
				return "good";
			}
		}
    	catch (SQLException ex)
		{
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return "error";
		}
    }
    
    public String GetLoggedStatus(String username)
    {
    	String findLogCount = "SELECT * FROM users WHERE username ='" + username + "';";
    	
    	try
		{
			rset = stmt.executeQuery(findLogCount);
			rset.next();
			int login = rset.getInt("loggedin");
			
			if(login == 1)
			{
				return "logged in";
			}
			else
			{
				return "Not logged in";
			}
		}
    	
    	catch (SQLException ex)
		{
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return "error";
		}
    }
    
    public String ReturnUsername(int a)
    {
    	int s = 0;
    	String name = "a";
    	try {
        	stmt = conn.createStatement();
        	rset = stmt.executeQuery("SELECT username FROM users");
        	while (s < a) {
        		rset.next();
        		s++;
        	}
        	name = rset.getString("username");
        	return name;
        	}
    	
    	catch (SQLException ex)
		{
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return "NULL";
		} 
    }
    
    public int ReturnUsersNum()
    {
    	int Users = 0;
    	try {
    	stmt = conn.createStatement();
    	rset = stmt.executeQuery("SELECT * FROM users");
    	while (rset.next()) {
    		Users++;
    	}
    	return Users;
    	} 
    	catch (SQLException ex)
		{
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return 0;
		} 
    }
    
    
    
    public String resetStatus(String username)
    {
    	String updateStatus = "UPDATE users SET lockcount = 0 WHERE username = '" + username + "';";
    	
    	try
		{
    		stmt.executeUpdate(updateStatus);
    		return "good";
		}
    	catch (SQLException ex)
		{
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return "error";
		}
    }
}