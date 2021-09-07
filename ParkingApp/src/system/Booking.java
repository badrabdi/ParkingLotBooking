package system;

import java.util.UUID;

public class Booking {
	
	private String parkingSpot;
	private String bookingID;
	private boolean paymentStatus;
	private boolean approved;
	private String customerID;
	private String startTime;
	private String duration;
	private String plate;
	private String date;
	
	public Booking(String parkingSpot, String bookingID, boolean paymentStatus, String customerID, String startTime, String duration,String plate,String date) {
		this.parkingSpot = parkingSpot;
		this.bookingID = UUID.randomUUID().toString();
		this.paymentStatus = paymentStatus;
		this.customerID = customerID;
		this.startTime = startTime;
		this.duration = duration;
		this.approved = false;
		this.date = date;
	}
	
	public String getParkingSpot() {
		return parkingSpot;
	}



	public String getBookingID() {
		return bookingID;
	}



	public boolean isPaymentStatus() {
		return paymentStatus;
	}



	public String getCustomerID() {
		return customerID;
	}



	public String getStartTime() {
		return startTime;
	}



	public String getDuration() {
		return duration;
	}



	public String getPlate() {
		return plate;
	}


	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}


	
	public void setBookingID(String ID) {
		this.bookingID = ID;
	}
	
	public void setPaymentStatus(boolean set) {
		this.paymentStatus = set;
	}
	
	public void setApproved(boolean set) {
		this.approved = set;
	}
	public boolean getApproved() {
		return this.approved;
	}
	
	
	public String viewBooking() {
		
		//TODO 
		return bookingID;
		
	}
}
