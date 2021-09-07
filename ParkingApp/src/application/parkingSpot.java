package application;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import system.System;
import system.Booking;
import system.Parking_Spot;



public class parkingSpot implements Initializable{
	
	public parkingSpot() {
		
	}
	
	
	
	
	
	@FXML
	private DatePicker datePick;
	

	@FXML
	private MenuButton duration;
	
	@FXML
	private ListView<String> listParking;
	int count = 0;
	
	@FXML
	private Label warning;
	@FXML
	private ListView<String> timePick;
	
	private String hour;
	private String min;
	private String time;
	private String durationTime;
	private String date;
	private String parkingNum;
	private LocalDate dateCurr;
	
	private EventHandler<ActionEvent> hourEvent;
	private EventHandler<ActionEvent> minEvent;
	private EventHandler<ActionEvent> set;
	private EventHandler<ActionEvent> dur;
	private EventHandler<ActionEvent> selectSpot;
	private System system = null;

	public void setHour(ActionEvent event) throws IOException{
		//System.out.println(hourMenu.getItems())();
	
		
	}

	
	public void backMenu(ActionEvent event) throws IOException{
		Main main = new Main();
		main.cs("customerLog.fxml");
	}
	
	public void selectDate(ActionEvent event)throws IOException {
		
		dateCurr = datePick.getValue();
		
		setDate();
	 
	}
	
	public void setDate(){
		
	
		
		hour = null;
		min = null;
		time = null;
		durationTime = null;
		
	
	 	
	 	duration.setText("Duration");
	 	
	 	setup();
	 
	}
	public void setProceed(ActionEvent event) throws Exception {
		Main main = new Main();
		
		
		
//		if(hour != null && min != null && time != null && durationTime != null && parkingNum != null && dateCurr != null ) {
//			main.cs("customerLog.fxml");
//		}else {
//			warning.setText("Please fill in all fields!");
//		}
//		
		
		if(durationTime != null  && datePick.getValue().toString() != ""  && listParking.getSelectionModel().getSelectedItem() != null
				&& timePick.getSelectionModel().getSelectedItem() != null ) {
			
			String timeTo = timePick.getSelectionModel().getSelectedItem();
			String dura = durationTime;
			String parkSpot  = listParking.getSelectionModel().getSelectedItem();
			ArrayList<Booking> bookings = system.getBookings();
			 ArrayList<String> times = new ArrayList<String>();
			 ArrayList<String> times1 = new ArrayList<String>();
			 ArrayList<String> times2 = new ArrayList<String>();
	         String opt [] = { "00","15","30","45"};
	         for(int i = 0 ; i < 24; i++) {
	        	 for(int x = 0; x < 4; x++) {
	        		 times.add(i+":"+opt[x]);
	        	 }
	         }
	         for(int i = 0; i < times.size(); i ++) {
	        	 if(times.get(i).equals(timeTo)) {
	        		 int count = 0;
        			 if(dura == "15") count = 1;
        			 if(dura == "30") count = 2;
        			 if(dura == "45") count = 3;
        			 if(dura == "60") count = 4;
        			 for(int j = i; j < count; j++) {
        				 times1.add(times.get(j));
        			 }
	        	 }
	         }
	         for(Booking booking : bookings) {
	        	 if(booking.getParkingSpot().equals(listParking.getSelectionModel().getSelectedItem().toString())) {
	        		 if(datePick.getValue().toString().equals(booking.getDate())) {
	        			 String durat = booking.getDuration();
	        			 int delete = 0;
	        			 if(durat == "15") delete = 1;
	        			 if(durat == "30") delete = 2;
	        			 if(durat == "45") delete = 3;
	        			 if(durat == "60") delete = 4;
	        			// String cTime = timePick.getSelectionModel().getSelectedItem().toString();
	        			 for(int i = 0; i < times.size();i++) {
	        				 if(times.get(i).equals(booking.getStartTime())) {
	        					 for(int j = i; j < delete;j++) {
	        						 times.remove(j);
	        					 }
	        				 }
	        			 }
	        		 }
	        	 }
	         }
	         
	         
	         for(int i = 0; i < times.size(); i ++) {
	        	 if(times.get(i).equals(timeTo)) {
	        		 int count = 0;
        			 if(dura == "15") count = 1;
        			 if(dura == "30") count = 2;
        			 if(dura == "45") count = 3;
        			 if(dura == "60") count = 4;
        			 for(int j = i; j < count; j++) {
        				 times2.add(times.get(j));
        			 }
	        	 }
	         }
	         
	         
	         
	         
	         
	         if(times1.equals(times2)) {
	        	 Booking newBooking = new Booking(parkSpot,"",false,
	        			 system.getUserSession().getAccountID(),
	        			 timeTo,
	        			 dura,
	        			 "",
	        			 datePick.getValue().toString()
	        			 );
	        	 bookings.add(newBooking);
	        	 system.updateBookings(bookings);
	        	 main.cs("customerLog.fxml");
	         }
	         else {
	        	 warning.setText("Unavailable duration. Select another option");
	         }
			
			
			
			
			
			
			
			
	
		}else {
			warning.setText("Please fill in all fields!");
		}
		
		
		
		
		
	}
	
	public void setSpot(ActionEvent event) throws IOException{
		setup();
	}
	
	public void setup() {
			
		//listParking = new ListView<String>();
		
		datePick.setDayCellFactory(picker -> new DateCell() {
	        public void updateItem(LocalDate date, boolean empty) {
	            super.updateItem(date, empty);
	            LocalDate today = LocalDate.now();

	            setDisable(empty || date.compareTo(today) < 0 );
	        }
	    });
		
		
		
		
		duration.getItems().clear();
		dur = setDur();
		MenuItem ff= new MenuItem("15");
		ff.setOnAction(dur);
		MenuItem tt = new MenuItem("30");
		tt.setOnAction(dur);
		
		MenuItem foo= new MenuItem("45");
		foo.setOnAction(dur);
		duration.getItems().addAll(ff,tt,foo);
		
		
		
		
		
	}
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	//	datePick = new DatePicker();
		
		datePick.setDayCellFactory(picker -> new DateCell() {
	        public void updateItem(LocalDate date, boolean empty) {
	            super.updateItem(date, empty);
	            LocalDate today = LocalDate.now();

	            setDisable(empty || date.compareTo(today) < 0 );
	        }
	    });
		
		
		
		ArrayList<Parking_Spot> parkingSpots  = null;
		try {
			system = new System();
			parkingSpots = system.getParkingSpots();
		} catch (Exception e) {
	
			e.printStackTrace();
		}
		
		parkingNum = null;
		dateCurr = null;
		selectSpot = spotSetup();
		for(Parking_Spot spot : parkingSpots) {
			
		//	placement.setOnAction(selectSpot);
			listParking.getItems().add(spot.getSpot());
			
			

			//((ButtonBase) listParking.getItems()).setOnAction(selectSpot);
		}
		warning.setText(parkingSpots.size()+"");
		listParking.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
	         String selectedItem = listParking.getSelectionModel().getSelectedItem();
	         ArrayList<Booking> bookings = system.getBookings();
	         ArrayList<String> times = new ArrayList<String>();
	         String opt [] = { "00","15","30","45"};
	         for(int i = 0 ; i < 24; i++) {
	        	 for(int x = 0; x < 4; x++) {
	        		 times.add(i+":"+opt[x]);
	        	 }
	         }
	         try {
	        	    for(Booking booking : bookings) {
	   	        	 if(booking.getParkingSpot().equals(selectedItem)) {
	   	        		 if(datePick.getValue().toString().equals(booking.getDate())) {
	   	        			 String durat = booking.getDuration();
	   	        			 int delete = 0;
	   	        			 if(durat == "15") delete = 1;
	   	        			 if(durat == "30") delete = 2;
	   	        			 if(durat == "45") delete = 3;
	   	        			 if(durat == "60") delete = 4;
	   	        			// String cTime = timePick.getSelectionModel().getSelectedItem().toString();
	   	        			 for(int i = 0; i < times.size();i++) {
	   	        				 if(times.get(i).equals(booking.getStartTime())) {
	   	        					 for(int j = i; j < delete;j++) {
	   	        						 times.remove(j);
	   	        					 }
	   	        				 }
	   	        			 }
	   	        		 }
	   	        	 }
	   	         }
	   	            	 
	         } catch(Exception e) {
	        	 
	         }
	     
	         timePick.getItems().addAll(times);
	         parkingNum = selectedItem;
	         setup();
	     
	         setDate();
	});
		
		
	
		time = null;
		hour = null;
		min = null;
		setup();
		
		
		
		
		
		
	}
	
	public EventHandler<ActionEvent> spotSetup(){
		return new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				@SuppressWarnings("unchecked")
				ListView<String> src = (ListView<String>) event.getSource();
				parkingNum = src.getSelectionModel().getSelectedItem();
				
			
				setup();
			}
		};
	}
	
	
	

	public EventHandler<ActionEvent> setDur(){
		return new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				MenuItem src = (MenuItem) event.getSource();
				durationTime = src.getText();
				duration.setText(durationTime);
			}
		};
	}
	
	



}
