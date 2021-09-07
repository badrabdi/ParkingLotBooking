package system;

public class ParkingAuthority extends User {
	
	

	public ParkingAuthority(String firstName, String lastName ,String user, String pass, String email, String accountID) {
		super(firstName, lastName, user, pass, email, accountID);
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public String getFirstName() {

		return firstName;
	}


	@Override
	public String getLastName() {

		return lastName;
	}


	@Override
	public String getUser() {

		return user;
	}


	@Override
	public String getPass() {

		return pass;
	}


	@Override
	public String getEmail() {

		return email;
	}


	@Override
	public String getAccountID() {

		return accountID;
	}
	@Override
	public void setID(String ID) {
		this.accountID = ID;
	}

}
