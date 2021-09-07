package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import system.System;
import system.Parking_Spot;

public class parkingGUI implements Initializable{
	
	public parkingGUI() {
		
	}
	
	@FXML private Button request;
	@FXML private Button remove;
	@FXML private Button addSpot;
	@FXML private ListView<String> listParking;
	@FXML private Label error;
	
	private System system;
	private ArrayList<Parking_Spot> spots;
	
	private String parkingNum;
	
	public void add(ActionEvent event) throws IOException{
		
			Main main = new Main();
			main.cs("parkingAddSpot.fxml");
		
	

	
	}
	
	public void logout(ActionEvent event) throws IOException{
		
		Main main = new Main();
		main.cs("main.fxml");
	



}

	public void removeSpot(ActionEvent event) throws IOException {
		if(listParking.getSelectionModel().getSelectedItem() != null) {
			String sp = listParking.getSelectionModel().getSelectedItem().toString();
			Parking_Spot toRemove = null;
			for(Parking_Spot spot : spots) {
				if(spot.getSpot() == sp) {
					toRemove = spot;
					break;
				}
			}
			spots.remove(toRemove);
			system.updateParking(spots);
			Main main = new Main();
			main.cs("parkingAuth.fxml");
		}
		else {
			error.setText("Select a spot");
		}
	}
	
	
	
	public void request(ActionEvent event) throws IOException{
		Main main = new Main();
		main.cs("parkingReq.fxml");
	}
	

	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			system = new System();
			spots = system.getParkingSpots();
			for(Parking_Spot spot : spots) {
				listParking.getItems().add(spot.getSpot());
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	
//		listParking.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
//				parkingNum = listParking.getSelectionModel().getSelectedItem();
//	         
//	
//	});
		
		
	
	
		
		
		
		
		
	}
	
	

}
