package system;

import java.util.ArrayList;

public class Customer extends User{
	
	private ArrayList<Booking> bookings;
	 

	public Customer(String firstName, String lastName, String user, String pass, String email, String accountID) {
		super(firstName, lastName, user, pass, email, accountID);
		bookings = new ArrayList<Booking>();

	}


	@Override
	public String getFirstName() {

		return this.firstName;
	}


	@Override
	public String getLastName() {

		return this.lastName;
	}


	@Override
	public String getUser() {

		return this.user;
	}


	@Override
	public String getPass() {

		return this.pass;
	}


	@Override
	public String getEmail() {

		return this.email;
	}


	@Override
	public String getAccountID() {

		return this.accountID;
	}

	@Override
	public void setID(String ID) {
		this.accountID = ID;
	}
}
