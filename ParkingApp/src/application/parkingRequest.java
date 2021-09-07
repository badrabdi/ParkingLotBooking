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
import system.Booking;

public class parkingRequest implements Initializable{

	public parkingRequest(){
		
	}
	
	
	@FXML private Button cancel;
	@FXML private Button grant;
	@FXML private Button view;
	@FXML private Label error;
	@FXML private ListView<String> listRequest;
	@FXML private Label bLabel;
	
	private String req;
	private System system;
	private ArrayList<Booking> bookings;
	
	public void back(ActionEvent event) throws IOException{
		Main main = new Main();
		main.cs("parkingAuth.fxml");
	}
	public void logout(ActionEvent event) throws IOException{
		Main main = new Main();
		main.cs("main.fxml");
	}
	
	public void viewInfo(ActionEvent event) throws Exception {
		if(nullCheck(req)) {
			String bookID = listRequest.getSelectionModel().getSelectedItem().toString();
			Booking found = null;
			for(int i = 0; i < bookings.size();i++) {
				if(bookings.get(i).getBookingID().equals(bookID)) found = bookings.get(i);
			}
			String res =  "Parking Spot: " + found.getParkingSpot() + " Date: "+ found.getDate() + " Time "+  found.getStartTime()+" CustomerID " + found.getCustomerID();
			bLabel.setText(res);
		}
		else {
			error();
		}
	}
	
	public void cancel(ActionEvent event)throws Exception {
		if(nullCheck(req)) {
			String bookID = listRequest.getSelectionModel().getSelectedItem().toString();
			Booking found = null;
			for(int i = 0; i < bookings.size();i++) {
				if(bookings.get(i).getBookingID().equals(bookID)) found = bookings.get(i);
			}
			bookings.remove(found);
			system.updateBookings(bookings);
			Main main = new Main();
			main.cs("parkingReq.fxml");
		}
			
		
		else {
			error();
		}
	}
		
	
	
	public void grant(ActionEvent event)throws Exception {
		if(nullCheck(req)) {
			String bookID = listRequest.getSelectionModel().getSelectedItem().toString();
			
			for(int i = 0; i < bookings.size();i++) {
				if(bookings.get(i).getBookingID().equals(bookID)) bookings.get(i).setApproved(true);
			}
			
			system.updateBookings(bookings);
			Main main = new Main();
			main.cs("parkingReq.fxml");
		}
		else {
			error();
		}
	}
		
	
	
	public boolean nullCheck(String res) {return listRequest.getSelectionModel().getSelectedItem() != null;}
	public void error() {error.setText("Please select a booking");}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		req = null;
		try {
			system = new System();
			bookings = system.getBookings();
			
		} catch (Exception e) {
	
			e.printStackTrace();
		}
		
		for(Booking booking: bookings) {
			
			if(!booking.getApproved()) listRequest.getItems().add(booking.getBookingID());
			
			

		}
//		listRequest.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
//				req = listRequest.getSelectionModel().getSelectedItem();
//	         
//	
//	});
	}
}
