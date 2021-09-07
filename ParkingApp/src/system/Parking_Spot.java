package system;

import java.util.UUID;

public class Parking_Spot {
	private String spot;
	private String price;
	private String id;
	
	public Parking_Spot(String spot, String price) {
		this.spot = spot;
		this.price = price;
		id = UUID.randomUUID().toString();
	}
	public String getSpot() {
		return this.spot;
	}
	public String getPrice(){
		return this.price;
	}
	
	public void setSpot(String spot) {
		this.spot = spot;
	}
	public void setPrice(String price) {
		this.price = price;
		
	}
	
	public void setID(String id) {
		this.id = id;
	}
	
	public String getID() {
		return this.id;
	}
	
	
}
