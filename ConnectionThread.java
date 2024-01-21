package server;


import java.io.IOException;
import java.net.Socket;
import services.NetworkInterface;
import services.Database;
import services.Gmail;

public class ConnectionThread extends Thread 
{
	private boolean go;
	private String name;
	private int id;
	
	// The main server (port listener) will supply the socket.
	// The thread (this class) will provide the I/O streams.
	// BufferedReader is used because it handles String objects,
	// whereas DataInputStream does not (primitive types only).
	
	NetworkInterface networkInterface;
	
	// This is a reference to the "parent" Server object.
	// It will be set at time of construction.
	private Server server;
	
	Database database = new Database("root", "password");
	Gmail gmailService = new Gmail();

	public ConnectionThread (int id, Socket socket, Server server) 
	{
		this.server = server;
		this.id = id;
		this.name = Integer.toString(id);
		go = true;
		
		// Create the stream I/O objects on top of the socket.
		try
		{
			networkInterface = new NetworkInterface(socket);
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public String toString ()
	{
		return name;
	}
	
	public String getname ()
	{
		return name;
	}

	public void run ()
	{
		// Server thread runs until the client terminates the connection.
		while(go) 
		{
			try 
			{
				// Always receives a String object with a newline (\n)
				// on the end due to how BufferedReader readLine() works.
				// The client adds it to the user's string but the BufferedReader
				// readLine() call strips it off.
				String txt = networkInterface.Read();
				System.out.println("SERVER received: " + txt);
				// If it is not the termination message, send it back adding the
				// required (by readLine) "\n".
				
				// If the disconnect string is received then 
				// close the socket, remove this thread object from the
				// server's active client thread list, and terminate the thread.
				if(txt.equals("disconnect")) 
				{
					server.removeID(id);
					networkInterface.Close();
					go = false;
				}
				if(txt.substring(0, 8).equals("register"))
				{
					try
					{
						String credentialsTemp = txt.substring(9, txt.length());
						String[] credentials = credentialsTemp.split(" ");
						boolean result = database.createUser(credentials[0], credentials[1] , credentials[2]);
						if(result == true)
						{
							networkInterface.Write("finished" + "\n");
						}
						else
						{
							networkInterface.Write("error" + "\n");
						}
					}
					catch(Exception e)
					{
						networkInterface.Write("error" + "\n");
					}
				}
				else if(txt.substring(0, 8).equals("recovery"))
				{
					String recoveryUsername = txt.substring(9, txt.length());
					String[] information = new String[2];
					try
					{
						information = database.recoveryCheck(recoveryUsername);
						gmailService.sendEmail(information[0], information[1]);
						networkInterface.Write("finished" + "\n");
					}
					catch(Exception e)
					{
						networkInterface.Write("error" + "\n");
					}
				}
				else if(txt.substring(0, 5).equals("reset"))
				{
					String statusUsername = txt.substring(6, txt.length());
					try
					{
						String result4 = database.resetStatus(statusUsername);
						
						if(result4.equals("good"))
						{
							networkInterface.Write("good" + "\n");
						}
						else
						{
							networkInterface.Write("error" + "\n");
						}
					}
					catch(Exception e)
					{
						networkInterface.Write("error" + "\n");
					}
				}
				else if(txt.substring(0, 6).equals("status"))
				{
					String statusUsername = txt.substring(7, txt.length());
					try
					{
						String result3 = database.getStatus(statusUsername);
						
						if(result3.equals("good"))
						{
							networkInterface.Write("good" + "\n");
						}
						else if(result3.equals("locked"))
						{
							networkInterface.Write("locked" + "\n");
						}
						else
						{
							networkInterface.Write("nonexisting" + "\n");
						}
					}
					catch(Exception e)
					{
						networkInterface.Write("error" + "\n");
					}
				}
				else if(txt.substring(0, 5).equals("login"))
				{
					String credentialsTemp = txt.substring(6, txt.length());
					String[] credentials = credentialsTemp.split(" ");
					String username = credentials[0];
					String password = credentials[1];
					try
					{
						String result = database.login(username, password);
						
						if(result.equals("good"))
						{
							networkInterface.Write("finished" + "\n");
						}
						else if(result.equals("passworderror"))
						{
							String result2 = database.increaseLockCount(username);
							
							if(result2.equals("lockaccount"))
							{
								networkInterface.Write("lockaccount" + "\n");
							}
							else if(result2.equals("good"))
							{
								networkInterface.Write("passworderror" + "\n");
							}
							else
							{
								networkInterface.Write("error");
							}
						}
						else
						{
							networkInterface.Write("usernameerror" + "\n");
						}
					}
					catch(Exception e)
					{
						networkInterface.Write("error" + "\n");
					}
				}
				else if(txt.substring(0, 6).equals("logout"))
				{
					String username = txt.substring(7, txt.length());
					String result = database.logout(username);
					if(result.equals("good"))
					{
						networkInterface.Write("finished" + "\n");
					}
					else
					{
						networkInterface.Write("error" + "\n");
					}
				}
				else 
				{
					networkInterface.Write("unrecognizederror" + "\n");
				}
			}
			catch(IOException e) 
			{
				e.printStackTrace();
				go = false;
			}
			
		}
	}
}