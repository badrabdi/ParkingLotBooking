package application;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import system.Booking;
import system.Customer;
import system.System;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class customerLoggedIn implements Initializable {
	
	
	@FXML private Button logout;
	@FXML private Label greeting;
	@FXML private Label approval;
	@FXML private Label ready;
	@FXML private Label paid;
	@FXML private Label warning;
	private Customer customer;
	private System system;
	private ArrayList<Booking> bookings;
	public void userLogout(ActionEvent event) throws IOException{
		Main main = new Main();
		main.cs("main.fxml");
	}
	public void bookSpot(ActionEvent event)throws IOException {
		bookings = system.getUserBooking(customer);
		if(bookings.size() >= 3) {
			warning.setText("Maximum 3 bookings allowed!");
		}
		else {
			Main main = new Main();
			main.cs("addSpot.fxml");
		}
		
		
	}
	public void checkOut(ActionEvent event)throws IOException{
		bookings = system.getUserBooking(customer);
		
	
		int approvedPurchase = 0;
		
		
		for(Booking booking : bookings) {

			if(booking.getApproved() && !booking.isPaymentStatus()) {
				approvedPurchase++;
			}
			
		}
		if(approvedPurchase > 0) {
			Main main = new Main();
			main.cs("creditCardPurchase.fxml");
		}
		else {
			warning.setText("No Approved Spots.");
		}
		
	}
	
	public void viewSpot(ActionEvent event)throws IOException{
		Main main = new Main();
		main.cs("viewSpots.fxml");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		
		try {
			system = new System();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
		
			customer = system.getUserSession();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		if(customer == null) {
			greeting.setText("NULL");
		}
		else greeting.setText("Hello, " + customer.getFirstName() + " " + customer.getLastName());
		
		bookings = system.getUserBooking(customer);
		
		int cartWaitingApproval = 0;
		int approvedPurchase = 0;
		int fullypaid = 0;
		
		for(Booking booking : bookings) {
			if(booking.getApproved() && booking.isPaymentStatus()) {
				fullypaid++;
			}
			if(booking.getApproved() && !booking.isPaymentStatus()) {
				approvedPurchase++;
			}
			if(!booking.getApproved() && !booking.isPaymentStatus()) {
				cartWaitingApproval++;
			}
		}
		
		approval.setText("Current bookings in cart waiting for approval: "+ cartWaitingApproval + "");
		ready.setText("Current Approved Spots Ready to purchase: " +approvedPurchase +  "");
		paid.setText("Current Paid Booking Spots: " +fullypaid + "");
		
		
		
	}
	
}
