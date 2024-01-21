package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
//GUI
import javafx.application.Application;

public class Server
{
	// Assign each client connection an ID. Just increment for now.
	private static int nextId = 0;
	
	// The socket that waits for client connections.
	private ServerSocket serversocket;

	// The port number used for client communication.
	private static final int PORT = 8000; // Should be higher than "1024" as many ports below this level are being used already.
			
	// List of active client threads by ID number.
	private Vector<ConnectionThread> clientconnections;
	
	//GUI.
	ServerGUI maingui;
	
	public int getPort()
	{
		return PORT;
	}

	public Server()
	{
		
		// Construct the list of active client threads.
		clientconnections = new Vector<ConnectionThread>();

		// Listen for incoming connection requests.
		listen();

	}

	public void peerconnection(Socket socket)
	{		
		// When a client arrives, create a thread for their communication.
		ConnectionThread connection = new ConnectionThread(nextId, socket, this);

		// Add the thread to the active client threads list.
		clientconnections.add(connection);
		
		// Start the thread.
		connection.start();

		// Place some text in the area to let the server operator know what is going on.
		System.out.println("SERVER: connection received for ID #" + nextId + "\n");
		++nextId;
	}
	
		
	// Called by a ServerThread when a client is terminated.
	public void removeID(int id)
	{
		nextId--;
		
		// Find the object belonging to the client thread being terminated.
		for (int i = 0; i < clientconnections.size(); ++i)
		{
			ConnectionThread cc = clientconnections.get(i);
			long x = cc.getId();
			if(x == id)
			{
				// Remove it from the active threads list, and the thread will terminate itself.
				clientconnections.remove(i);
				
				// Place some text in the area to let the server operator know what is going on.
				System.out.println("SERVER: connection closed for client ID #" + id + "\n");
				break;
			}
		}
	}

	private void listen()
	{
		try 
		{
			// Open the server socket.
			serversocket = new ServerSocket(getPort());
			
			// Server runs until manually shut down.
			while(true)
			{
					// Block until a client comes along.
					Socket socket = serversocket.accept();
					
					// Connection accepted, create a peer-to-peer socket
					// between the server (thread) and client.
					peerconnection(socket);
			}
		}
		
		catch(IOException e) 
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	//This does not work for the Main Server for some reason
	public static int ReturnID() {
		return nextId;
	}
	
	public static void main(String args[])
	{
		// Instantiate the server anonymously. There is no need to keep a reference
		// to the object since it will run in its own thread.
		new Thread(() -> 
		{
			Application.launch(ServerGUI.class, args);
		}).start();
		
		new Server();
	}
}
