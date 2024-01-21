package services;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class NetworkInterface
{
	private BufferedReader datain;
	private DataOutputStream dataout;
	
	public NetworkInterface(Socket socket) throws IOException
	{
		datain = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		dataout = new DataOutputStream(socket.getOutputStream());
	}
	
	public String Read() throws IOException
	{
		return this.datain.readLine();
	}
	
	public void Write(String input) throws IOException
	{
		this.dataout.writeBytes(input);
	}
	
	public void Close() throws IOException
	{
		this.datain.close();
	}
	
	public void Flush() throws IOException
	{
		this.dataout.flush();
	}
}