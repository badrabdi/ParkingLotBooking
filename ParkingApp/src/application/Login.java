package application;


import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.ArrayList;

import system.Customer;
import system.System;



public class Login {
	
	public Login() {
		
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
	
	
	public void userLogin(ActionEvent event) throws Exception{
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
	public void systemLogin(ActionEvent event) throws IOException{
		Main main = new Main();
		main.cs("systemAdmin.fxml");
	}
	private void checkLogin() throws Exception{
		Main main = new Main();
		System system = new System();
		system.loadCustomer();
		ArrayList<Customer> customers = system.getCustomers();
		String user_attempt = username.getText().toString();
		String pass_attempt = password.getText().toString();
		
		
		boolean accountFound = false;

		
		if(username.getText().isEmpty() && password.getText().isEmpty()) {
			wrongLogin.setText("Enter in a username and password to proceed.");
		}
		for(Customer customer : customers) {
			if(customer.getUser().equals(user_attempt) && customer.getPass().equals(pass_attempt)) {
				accountFound = true;
				system.updateCustomers(system.getCustomers());
				system.newUserSession(customer);
				break;
			}
		}
		
		
		if(accountFound) {
			wrongLogin.setText("Success");
			main.cs("customerLog.fxml");
		}
		else {
			wrongLogin.setText("Wrong username or password");
		}
	}
	

}
