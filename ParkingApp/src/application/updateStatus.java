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
import system.ParkingAuthority;

public class updateStatus implements Initializable{

	
	public updateStatus() {
		
	}
	

	
	@FXML private ListView<String> listView;
	@FXML private Label warning;
	
	private System system;
	private ArrayList<Booking> bookings;
	
	public void back(ActionEvent event)throws IOException {
		Main main = new Main();
		main.cs("sysAdmin.fxml");
	}
	public void logout(ActionEvent event)throws IOException {
		Main main = new Main();
		main.cs("maim.fxml");
	}
	public void update(ActionEvent event)throws Exception {
		if(listView.getSelectionModel().getSelectedItem() != null) {
			String id = listView.getSelectionModel().getSelectedItem().toString();
			Booking found = null;
			for(Booking booking: bookings) {
				if(booking.getBookingID().equals(id)) {
					found = booking;
				}
			}
			bookings.remove(found);
			system.updateBookings(bookings);
			Main main = new Main();
			main.cs("sysAdmin.fxml");
		}
		else {
			warning.setText("Select Account!");
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			system = new System();
			bookings = system.getBookings();
			
			for(Booking booking : bookings) {
				if(!booking.isPaymentStatus() && booking.getApproved()) {
				listView.getItems().add(booking.getBookingID());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
