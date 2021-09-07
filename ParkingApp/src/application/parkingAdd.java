package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import system.System;
import system.Parking_Spot;
public class parkingAdd {
	
	public parkingAdd() {
		
	}
	
	
	@FXML private TextField parkingNumber;
	@FXML private Button addSpot;
	@FXML private Label error;
	
	
	private System system;
	private ArrayList<Parking_Spot> spots;
	
	
	public void add(ActionEvent event)throws Exception{
		
		system = new System();
		spots = system.getParkingSpots();
		
		String verify = parkingNumber.getText().toString();
		
		char [] c = verify.toCharArray();
		boolean check = true;
		for(char ca : c) {
			if(!Character.isDigit(ca)) {
				check =  false;
				break;
			}
		}
		
		
		if(check) {
			boolean found = false;
			for(Parking_Spot spot : spots) {
				if(spot.getSpot().equals(verify)) found = true;
			}
			
			if(found) {
				error.setText("Spot already exist");
				return;
			}
			Parking_Spot newSpot = new Parking_Spot(verify,"5");
			spots.add(newSpot);
			system.updateParking(spots);
			
			Main main = new Main();
			
			main.cs("parkingAuth.fxml");
		}
		else {
			error.setText("Enter a valid number");
		}
	}
	
	public void back(ActionEvent event)throws IOException{
		Main main = new Main();
		main.cs("parkingAuth.fxml");
	}
	
	public void logout(ActionEvent event)throws IOException{
		Main main = new Main();
		main.cs("main.fxml");
	}
	
	
	

}
