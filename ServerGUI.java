package server;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import services.Database;

public class ServerGUI extends Application {
	
	Server server;

	private Database database = new Database("root", "password");;

	@FXML
	Button loggedInButton = new Button();
	
	@FXML
	Button lockedOutButton = new Button();
	
	@FXML
	Button registeredButton = new Button();
	
	@FXML
	Button connectedButton = new Button();
	
	@FXML
	TextArea textlog = new TextArea();
	
	
	public static void main(String[] args)
	{
		launch(args);
	}


	@Override
	public void start(Stage primaryStage)
	{
		try 
		{
			Parent mainScene = FXMLLoader.load(getClass().getResource("serverGUI.fxml"));
			Scene scene = new Scene(mainScene);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Server Application");
			database = new Database("root", "password");
		}

		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void connected() // TODO.
	{
		int i = Server.ReturnID();
		textlog.setText("Total number of connected users: " + String.valueOf(i));
	}
	
	public void loggedIn() 
	{
		int i = database.ReturnUsersNum();
		String s = "";
		String logname = "";
		String full = "";
		int counter = 0;
		for(int j = 1; j < i + 1; j++)
		{
			s = database.ReturnUsername(j);
		
			// Actually telling if its logged in or not:
			logname = database.GetLoggedStatus(s);
			if(logname.equals("Not logged in")){}
			else 
			{
				counter++;
				full = full + "\"" + database.ReturnUsername(j) + "\" is " + logname + ".\n";
			}
		}
		
		textlog.setText(full + "\n Total number of logged-in users: " + counter);
	}
	
	public void lockedOut() 
	{
		int i = database.ReturnUsersNum();
		String s = "";
		String logname = "";
		String full = "";
		int counter = 0;
		for (int j = 1; j < i + 1; j++)
		{
			s = database.ReturnUsername(j);
			
			// Actually telling if its logged in or not:
			logname = database.getStatus(s);
			if(logname.equals("locked"))
			{
				full = full + "\"" + database.ReturnUsername(j) + "\" is " + logname + " out.\n";
				counter++;
			} 
			else {}
		}
		
		textlog.setText(full + "\n Total number of locked out users: " + counter);
	}
	
	public void registered() 
	{
		String full = "";
		int i = database.ReturnUsersNum();
		int counter = 0;
		for(int j = 1; j < i + 1; j++)
		{
			counter++;
			full = full + "\"" + database.ReturnUsername(j) + "\" is registered.\n";
		}
		
		textlog.setText(full + "\n Total number of registered users: " + counter);
	}
}

