package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import services.NetworkInterface;

public class Client
{
	// Port and host name of server.
	private static final int PORT = 8000;
    public static String HOST = "127.0.0.1";
	
	// Socket variable for peer to peer communication.
	private static Socket socket;

	// Stream variables for peer to peer communication
	// to be opened on top of the socket.
	NetworkInterface networkInterface;
	
	private String name = "";
	private boolean isConnected = true;
	
	public Client(String host) throws Exception
	{
		Client.HOST = host;
		try 
		{
			// Construct the peer to peer socket.
			socket = new Socket(HOST, PORT);

			// Wrap the socket in stream I/O objects.
			networkInterface = new NetworkInterface(socket);
		}
		
		catch(UnknownHostException e)
		{
			System.out.println("Host " + HOST + " at port " + PORT + " is unavailable.");
			throw new UnknownHostException();
		}
		
		catch(IOException e)
		{
			System.out.println("Unable to create I/O streams.");
			throw new IOException();
		}
		
	}
	
	public String sendString (String _msg) throws Exception
	{
		String rtnmsg = "";

		try 
		{
			// The server only receives String objects that are
			// terminated with a newline \n".
			// Send the String, making sure to flush the buffer.
			networkInterface.Write(_msg + "\n");
			networkInterface.Flush();
			
			// Receive the response from the server.
			// The do/while makes this a blocking read. Normally BufferedReader.readLine() is non-blocking.
			// That is, if there is no String to read, it will read "". Doing it this way does not allow
			// that to occur. We must get a response from the server. Time out could be implemented with
			// a counter.
			rtnmsg = "";
			do 
			{
				rtnmsg = networkInterface.Read();
			}
			
			while(rtnmsg.equals(""));
		}
		
		catch(IOException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println(rtnmsg);
		return rtnmsg;
		
	}
	
	public void disconnect()
	{
		isConnected = false;
    	String text = "disconnect";
		try
		{
			// The server only receives String objects that are
			// terminated with a newline "\n"

        	// Send a special message to let the server know 
        	// that this client is shutting down.
			text += "\n";
			networkInterface.Write(text);
			networkInterface.Flush();

			// Close the peer-to-peer socket.
			socket.close();
		}
		
		catch(IOException e1)
		{
			e1.printStackTrace();
			System.exit(1);
		}
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setStatus(boolean status)
	{
		this.isConnected = status;
	}
	
	public boolean getStatus()
	{
		return this.isConnected;
	}
}