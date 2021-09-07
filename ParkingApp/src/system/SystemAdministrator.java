package system;

public class SystemAdministrator {
	private String user;
	private String pass;
	
	public SystemAdministrator(String user,String pass) {
		this.user = user;
		this.pass = pass;
	}

	public String getUser() {
		return user;
	}

	public String getPass() {
		return pass;
	}
}
