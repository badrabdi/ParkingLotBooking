package system;

import java.util.UUID;

public abstract class User {

	
	protected String firstName;
	protected String lastName;
	protected String user;
	protected String pass;
	protected String email;
	
	
	protected String accountID;
	
	

	
	public User(String firstName,String lastName,String user, String pass, String email, String accountID) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.user = user;
		this.pass = pass;
		this.email = email;
		this.accountID = UUID.randomUUID().toString();
	}



	public abstract String getFirstName();



	public abstract String getLastName();



	public abstract String getUser();



	public abstract String getPass();



	public abstract String getEmail();



	public abstract String getAccountID();
	public abstract void setID(String ID);
	
}
