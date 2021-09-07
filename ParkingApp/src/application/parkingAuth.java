package application;


import javafx.scene.control.*;
import javafx.stage.Stage;
import system.ParkingAuthority;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.ArrayList;

import system.System;



public class parkingAuth {
	
	public parkingAuth() {
		
	}
	
	
	
	@FXML
	private Button loginButton;
	@FXML
	private Button registerButton;
	@FXML
	private Label wrongLogin;
	@FXML
	private TextField username;
	@FXML 
	private PasswordField password;
	
	private System system;
	private ArrayList<ParkingAuthority> parkAuth;
	
	
	public void userLogin(ActionEvent event) throws Exception{
		system = new System();
		checkLogin();
	}
	
	

	public void systemLogin(ActionEvent event) throws IOException{
		Main main = new Main();
		main.cs("systemAdmin.fxml");
	}
	public void customerLogin(ActionEvent event) throws IOException{
		Main main = new Main();
		main.cs("main.fxml");
	}
	public void checkLogin() throws Exception{
		Main main = new Main();
		if(username.getText().isEmpty() && password.getText().isEmpty()) {
			wrongLogin.setText("Enter in a username and password to proceed.");
		}
		String userN = username.getText().toString();
		String passW = password.getText().toString();
		boolean found = false;
		parkAuth = system.getParkingAuth();
		for(ParkingAuthority auth : parkAuth) {
			if(auth.getUser().equals(userN) && auth.getPass().equals(passW)) {
				wrongLogin.setText("Success");
				found = true;
				system.newParkSession(auth);
				main.cs("parkingAuth.fxml");
			}
		}
		
	
		if(!found) {
			wrongLogin.setText("Wrong username or password");
		}
	}
	

}
