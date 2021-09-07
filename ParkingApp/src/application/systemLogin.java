package application;


import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.ArrayList;

import system.System;
import system.SystemAdministrator;



public class systemLogin {
	
	public systemLogin() {
		
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
	private ArrayList<SystemAdministrator> sysAdmin;
	
	
	public void userLogin(ActionEvent event) throws Exception{
		system = new System();
		checkLogin();
	}
	
	
	public void userRegister(ActionEvent event) throws IOException{
		Main main = new Main();
		main.cs("register.fxml");
	}
	
	public void parkingLogin(ActionEvent event) throws IOException{
		Main main = new Main();
		main.cs("parkingAuthLogin.fxml");
	}
	public void customerLogin(ActionEvent event) throws IOException{
		Main main = new Main();
		main.cs("main.fxml");
	}
	private void checkLogin() throws IOException{
		Main main = new Main();
		
		if(username.getText().isEmpty() && password.getText().isEmpty()) {
			wrongLogin.setText("Enter in a username and password to proceed.");
		}
		
		sysAdmin = system.getSystemAdmin();
		
		boolean found = false;
		
		for(SystemAdministrator admin : sysAdmin) {
			String userN = username.getText().toString();
			String passW = password.getText().toString();
			if(userN.equals(admin.getUser()) && passW.equals(admin.getPass())) {
				found = true;
				wrongLogin.setText("Success");
				main.cs("sysAdmin.fxml");
			}
		}
		
		
	
		
		
		
		if(!found) {
			wrongLogin.setText("Wrong username or password");
		}
	}
	

}
