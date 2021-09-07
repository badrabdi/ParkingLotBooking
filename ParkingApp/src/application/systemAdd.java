package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import system.System;
import system.ParkingAuthority;


public class systemAdd {

	public systemAdd() {
		
	}
	
	
	@FXML private TextField fName;
	@FXML private TextField lName;
	@FXML private TextField email;
	@FXML private TextField user;
	@FXML private PasswordField pass1;
	@FXML private PasswordField pass2;
	@FXML private Button create;
	@FXML private Label error;
	
	
	private System system;
	private ArrayList<ParkingAuthority> pAuth;
	
	public void logout(ActionEvent event)throws IOException{
		Main main = new Main();
		main.cs("main.fxml");
	}
	
	public void back(ActionEvent event) throws IOException{
		Main main = new Main();
		main.cs("sysAdmin.fxml");
	}
	
	
	public void create(ActionEvent event) throws Exception{
		if(fName.getText().length() == 0) {
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
		if(pass1.getText().length() == 0) {
			error.setText("Enter your Password");
			return;
		}
		if(pass2.getText().length() == 0) {
			error.setText("Confirm your Password");
			return;
		}
		boolean nameCheck = verifyName(fName.getText(),lName.getText());
		boolean emailCheck = verifyEmail(email.getText());
		boolean passCheck = verifyPass(pass1.getText(),pass2.getText());
		
		
		if(!nameCheck) {
			error.setText("Enter only letters in your name!");
			return;
		}
		if(!emailCheck) {
			error.setText("Enter a valid email address");
			return;
		}
		if(!passCheck) {
			error.setText("Your passwords don't match!");
			return;
		}
		
		system = new System();
		pAuth = system.getParkingAuth();
		String userN = user.getText().toString();
		String emailN = email.getText().toString();
		boolean foundUser = false;
		boolean foundEmail = false;
		
		for(ParkingAuthority auth : pAuth) {
			if(auth.getUser().equals(userN)) foundUser = true;
			if(auth.getEmail().equals(emailN))foundEmail = true;
		}
		
		if(foundUser && foundEmail) {
			error.setText("User and Email already exist");
			return;
		}
		else if(foundUser) {
			error.setText("User already exist");
		}
		else if(foundEmail) {
			error.setText("Email already exist");
		}
		else {
			Main main = new Main();
			ParkingAuthority newAuth = new ParkingAuthority(fName.getText().toString(),
					lName.getText().toString(),
					userN,
					pass1.getText().toString(),
					emailN,
					"");
			pAuth.add(newAuth);
			system.updateAuth(pAuth);
			main.cs("sysAdmin.fxml");
		}
		
		
		
	}
	
	public boolean verifyPass(String pass1,String pass2) {
		return pass1.equals(pass2);
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
