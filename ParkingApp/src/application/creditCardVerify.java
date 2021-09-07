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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import system.Booking;
import system.Customer;
import system.Parking_Spot;
import system.System;

public class creditCardVerify implements Initializable {

	
	public creditCardVerify() {
		
	}
	
	
	
	@FXML private TextField fName;
	@FXML private TextField lName;
	@FXML private TextField creditNum;
	@FXML private TextField expDate;
	@FXML private TextField cvv;
	@FXML private Label warning;
	@FXML private Button confirm;
	@FXML private ListView<String> list;
	@FXML private Label total;
	
	private System system;
	private ArrayList<Booking> bookings;
	private Customer customer;
	
	
	public void confirmPurchase(ActionEvent event)throws Exception{
		String fullName = fName.getText() +" "+lName.getText();
		String card = creditNum.getText();
		String exp = expDate.getText();
		String cv = cvv.getText();
		
		boolean res = nameValidate(fullName) && creditCardValidate(card) && cvvValidate(cv) && expDateValidate(exp);
		if(res && list.getSelectionModel().getSelectedItems() != null) {
		       ArrayList<String> selectedItems = new ArrayList<String>();
		         selectedItems.addAll(list.getSelectionModel().getSelectedItems());
		         for(String str : selectedItems) {
		        	 
		        	 for(int i = 0; i < bookings.size();i++) {
		        		 String sub = "Parking ID: " + bookings.get(i).getBookingID();
		        		 if(sub.equals(str )) {
		        			 bookings.get(i).setPaymentStatus(true);

		        		 }
		        	 }
		         }
		         
			system.updateBookings(bookings);
			Main main = new Main();
			main.cs("customerLog.fxml");
		}
		else {
			warning.setText("Please enter valid data!");
		}
	}
	
	
	public boolean nameValidate(String name) {
		char[] chars = name.toCharArray();
		for(char c : chars) {
			if(!Character.isLetter(c) && c != ' ') return false;
		}
		return true;
		
	}
	public boolean creditCardValidate(String card) {
		if(card.length() != 16) {
			return false;
		}
		char[] chars = card.toCharArray();
		for(char c : chars) {
			if(!Character.isDigit(c)) return false;
		}
		
		return true;
	}
	
	public boolean cvvValidate(String cvv) {
		if(cvv.length() != 3) return false;
		char[] chars = cvv.toCharArray();
		for(char c : chars) {
			if(!Character.isDigit(c)) return false;
		}
		
		return true;
		
	}
	
	public boolean expDateValidate(String date) {
		if(date.length() != 5) {
			return false;
		}
		char[] c = date.toCharArray();
		if(!(Character.isDigit(c[0]) && Character.isDigit(c[1]))) {
			return false;
		}
		
		if(!(Character.isDigit(c[3]) && Character.isDigit(c[4]))) {
			return false;
		}
		if(c[2] != '/') return false;
		return true;
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
			String res = "Parking ID: " + booking.getBookingID() ;
			list.getItems().add(res);
		}
		list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		list.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
	         ArrayList<String> selectedItems = new ArrayList<String>();
	         selectedItems.addAll(list.getSelectionModel().getSelectedItems());
	         ArrayList<Parking_Spot> parkSpot  = system.getParkingSpots();

	         ArrayList<Booking> books = new ArrayList<Booking>();
	         for(String str : selectedItems) {
	        	 
	        	 for(Booking book : bookings) {
	        		 String sub = "Parking ID: " + book.getBookingID();
	        		 if(sub.equals(str )) {
	        			 books.add(book);
	        			 
	        			
	        		 }
	        	 }
	         }
	         
	         int totalPrice = 0;
	         for(Booking str : books) {
	        	 String pNum = str.getParkingSpot();
	        	 for(Parking_Spot spot : parkSpot) {
	        		 if(pNum.equals(spot.getSpot())) {
	        			 int multiplier = 1;
	        			 int cmp = Integer.parseInt(str.getDuration());
	        			 multiplier = cmp / 15;
	        			 totalPrice += Integer.parseInt(spot.getPrice()) *multiplier;
	        		 }
	        	 }
	         }
	         
	         total.setText("TOTAL: $"+ totalPrice);	      
	         
	         
	});
		
	}
	
	
}
