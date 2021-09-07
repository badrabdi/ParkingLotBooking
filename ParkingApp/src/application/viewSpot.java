package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import system.System;
import system.Booking;
import system.Customer;


public class viewSpot implements Initializable{
public viewSpot() {
		
	}
	
	@FXML private ListView<String> listView;
	@FXML private Label warning;
	private System system;
	private Customer customer;
	private ArrayList<Booking> bookings;

	
	
	
	public void back(ActionEvent event) throws IOException {
		Main main = new Main();
		main.cs("customerLog.fxml");
	}
	public void cancel(ActionEvent event) throws Exception{
		
		if( listView.getSelectionModel().getSelectedItem() != null) {
			String cmp = listView.getSelectionModel().getSelectedItem().toString();
			Booking found = null;
			for(Booking booking : bookings) {
				String res = "Parking Spot: " + booking.getParkingSpot() + " Date: " + booking.getDate() + " Start time: "+ booking.getStartTime() 
				+ " Duration: "  +booking.getDuration();
				if(cmp.equals(res)) {
					found = booking;
					
				}
			}
			bookings.remove(found);
			system.updateBookings(bookings);
			Main main = new Main();
			main.cs("viewSpots.fxml");
		}
		else {
			warning.setText("Select booking to cancel!");
		}
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		try {
			system = new System();
			customer = system.getUserSession();
			bookings = system.getUserBooking(customer);
		} catch (Exception e) {
	
			e.printStackTrace();
		}
		
		for(Booking booking : bookings) {
			String res = "Parking Spot: " + booking.getParkingSpot() + " Date: " + booking.getDate() + " Start time: "+ booking.getStartTime() 
			+ " Duration: "  +booking.getDuration();
			listView.getItems().add(res);
		}
	}
}
