package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import system.Customer;
import system.System;


public class register {
	public register() {}
	@FXML private TextField fName;
	@FXML private TextField lName;
	@FXML private TextField email;
	@FXML private TextField user; 
	@FXML private PasswordField pass;
	@FXML private Button create;
	@FXML private Label error;
	
	
	public void create(ActionEvent event) throws Exception{
		if(fName.getText().toString().length() == 0) {
			error.setText("Enter your First Name");
			return;
		}
		if(lName.getText().length() == 0) {
			error.setText("Enter your Last Name");
			return;
		}
		if(user.getText().length() == 0) {
			error.setText("Enter your user");
		}
		if(email.getText().length() == 0) {
			error.setText("Enter your Email");
			return;
		}
		if(pass.getText().length() == 0) {
			error.setText("Enter your Password");
			return;
		}
	
		boolean nameCheck = verifyName(fName.getText(),lName.getText());
		boolean emailCheck = verifyEmail(email.getText());

		
		
		if(!nameCheck) {
			error.setText("Enter only letters in your name!");
			return;
		}
		if(!emailCheck) {
			error.setText("Enter a valid email address");
			return;
		}
	
		
		System system = new System();
		ArrayList<Customer> customers = system.getCustomers();
		boolean userexist = false;
		boolean emailexist = false;
		String tEmail = email.getText().toString();
		String tUser = user.getText().toString();
		for(Customer customer : customers) {
			if(customer.getUser().equals(tUser)) {
				userexist = true;
			}
			if(customer.getEmail().equals(tEmail)) {
				emailexist = true;
			}
			
		}
		
		if(emailexist && userexist) {
			error.setText("Username & Email already exist");
			return;
		}
		else if(userexist) {
			error.setText("Username already exist");
			return;
		}
		else if(emailexist) {
			error.setText("Email already exist");
			return;
		}
		else {
			Customer newCustomer = new Customer(
					fName.getText().toString(),
					lName.getText().toString(),
					user.getText().toString(),
					pass.getText().toString(),
					email.getText().toString(),
					""
					);
			customers.add(newCustomer);
			system.updateCustomers(customers);
			system.newUserSession(newCustomer);
		}
		
		
		Main main = new Main();
		main.cs("customerLog"
				+ ".fxml");
		
		
	}
	

	
	public boolean verifyEmail(String em) {
		if(em.length() == 0) return false;
		int at = em.indexOf("@");
		if(at < 0) {
			return false;
		}
		String en = em.substring(at,em.length());
		int dot = en.indexOf(".");
		if(dot < 0) return false; 
		return true;
	}
	
	public boolean verifyName(String fname,String lname) {
		if(fname.length() == 0 || lname.length() == 0) {
			return false;
		}
		String name = fname + ""+lname;
		String res = name.replaceAll(" ","");
		
		char []arr = res.toCharArray();
		
		for(char c : arr) {
			if(Character.isDigit(c))return false;
		}
		return true;
	}
}
