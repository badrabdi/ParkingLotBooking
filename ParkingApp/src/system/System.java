package system;
import java.io.FileWriter;
import java.util.ArrayList;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;


public class System {
	
	private ArrayList<Customer> customers; 
	private ArrayList<ParkingAuthority> parkingAuth;
	private ArrayList<SystemAdministrator> systemAdmin;
	private ArrayList<Parking_Spot> parking;
	
	private ArrayList<Booking> bookings;
	
	
	
	String bookingDbPath = "src/resources/bookingsdb.csv";
	String customerDbPath ="src/resources/customerdb.csv";
	String parkingAdminDbPath = "src/resources/parkingAdmindb.csv";
	String systemAdminDbPath ="src/resources/systemAdmin.csv";
	String userSessPath = "src/resources/userSession.csv";
	String parkingSpotPath = "src/resources/parkingSpot.csv";
	public System() throws Exception {
		this.loadCustomer();
		this.loadAuth();
		this.loadAdmin();
		this.loadBookings();
		this.loadParkingSpots();
		this.loadAdmin();
		
//		ArrayList<SystemAdministrator> admin = new ArrayList<SystemAdministrator>();
//		SystemAdministrator sysA = new SystemAdministrator("admin","admin");
//		admin.add(sysA);
//		this.updateAdmin(admin);
//		ArrayList<Parking_Spot> park = new ArrayList<Parking_Spot>();
//		for(int i = 0; i < 7000;i++) {
//			Parking_Spot newSpot = new Parking_Spot(i+"","5");
//			park.add(newSpot);
//		}
//		
//		 this.updateParking(park);
	}
	

	
	public ArrayList<Booking> getUserBooking(Customer user){
		ArrayList<Booking> book = new ArrayList<Booking>();
		for(Booking booking : bookings) {
			if(booking.getCustomerID().equals(user.getAccountID())) {
				book.add(booking);
			}
		}
		return book;
		
	}
	

	public void updateBookings(ArrayList<Booking> booking1)throws Exception{	
//		
//		for(Booking book : booking1) {
//			boolean found = false;
//			for(int i = 0; i < bookings.size();i++) {
//				if(bookings.get(i).getBookingID().equals(book.getBookingID())) {
//					bookings.set(i, book);
//					found = true;
//				}
//			}
//			if(!found) {
//				bookings.add(book);
//			}
//			
//			
//		}
		bookings = booking1;
		try {
			CsvWriter csvOutput = new CsvWriter(new FileWriter(bookingDbPath, false), ',');
			csvOutput.write("Parking Spot Number");
			csvOutput.write("Booking ID");
			csvOutput.write("Payment Status");
			csvOutput.write("Approved Status");
			csvOutput.write("Customer ID");
			csvOutput.write("Start Time");
			csvOutput.write("Duration");
			csvOutput.write("Plate Number");
			csvOutput.write("Date");
			csvOutput.endRecord();
			
			for(Booking booking : bookings) {
				csvOutput.write(booking.getParkingSpot());
				csvOutput.write(booking.getBookingID());
				csvOutput.write(booking.isPaymentStatus() == true ? "paid" :"not-paid");
				csvOutput.write(booking.getApproved() == true ? "approved" : "not-approved");
				csvOutput.write(booking.getCustomerID());
				csvOutput.write(booking.getStartTime());
				csvOutput.write(booking.getDuration());
				csvOutput.write(booking.getPlate());
				csvOutput.write(booking.getDate());
				csvOutput.endRecord();
				}
			csvOutput.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public ArrayList<ParkingAuthority> getParkingAuth() {
		return parkingAuth;
	}

	public ArrayList<SystemAdministrator> getSystemAdmin() {
		return systemAdmin;
	}

	public ArrayList<Booking> getBookings() {
		return bookings;
	}
	public ArrayList<Parking_Spot> getParkingSpots(){
		return parking;
	}
	
	public void loadParkingSpots() throws Exception{
		parking = new ArrayList<Parking_Spot>();
		try {
			CsvReader reader = new CsvReader(parkingSpotPath); 
			reader.readHeaders();
			
			while(reader.readRecord()){ 
				
		
				String parkingSpot = reader.get("Parking Spot Number");
				String bookingID = reader.get("Parking ID");
				String price  = reader.get("Parking Fee");
	
				Parking_Spot park = new Parking_Spot(parkingSpot,price);
				park.setID(bookingID);
				parking.add(park);
			
				
			}
		}catch(Exception e) {
			
			e.printStackTrace();
		
		}
	}
	public void updateParking(ArrayList<Parking_Spot> park) {
		try {
			CsvWriter csvOutput = new CsvWriter(new FileWriter(parkingSpotPath, false), ',');
			csvOutput.write("Parking Spot Number");
			csvOutput.write("Parking ID");
			csvOutput.write("Parking Fee");
			csvOutput.endRecord();
			
			for(Parking_Spot p : park) {
				csvOutput.write(p.getSpot());
				csvOutput.write(p.getID());
				csvOutput.write(p.getPrice());
				csvOutput.endRecord();
				}
			csvOutput.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadBookings() throws Exception{
		
		bookings = new ArrayList<Booking>();

		try {
			CsvReader reader = new CsvReader(bookingDbPath); 
			reader.readHeaders();
			
			while(reader.readRecord()){ 
				
				//name,id,email,password
				String parkingSpot = reader.get("Parking Spot Number");
				String bookingID = reader.get("Booking ID");
				String pS  = reader.get("Payment Status");
				String aS = reader.get("Approved Status");
				boolean paymentStatus = pS.equals("paid");
				boolean approvedStatus = aS.equals("approved");
				String customer = reader.get("Customer ID");
				String startTime = reader.get("Start Time");
				String duration = reader.get("Duration");
				String plate = reader.get("Plate Number");
				String date = reader.get("Date");
				Booking book = new Booking(parkingSpot,bookingID,paymentStatus,customer,startTime,duration,plate,date);
				book.setBookingID(bookingID);
				book.setApproved(approvedStatus);
				bookings.add(book);
			
				
			}
		}catch(Exception e) {
			
			e.printStackTrace();
		
		}
	}
	
	
	public void newUserSession(Customer user) throws Exception{
		try {
			CsvWriter csvOutput = new CsvWriter(new FileWriter(userSessPath, false), ',');
			csvOutput.write("AccountID");
			csvOutput.endRecord();
			csvOutput.write(user.getAccountID());
			csvOutput.endRecord();
			csvOutput.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Customer getUserSession() throws Exception{
		try {
			CsvReader reader = new CsvReader(userSessPath); 
			reader.readHeaders();
			String accID = "";
			reader.readRecord();
			accID = reader.get("AccountID");
			
		
		
			if(customers.size() == 0 ) loadCustomer();
			
			for(Customer customer: customers) {
				if(customer.getAccountID().equals(accID)) {
					return customer;
				}
			}
	
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void newAdminSession(SystemAdministrator user) throws Exception{
		try {
			CsvWriter csvOutput = new CsvWriter(new FileWriter(userSessPath, false), ',');
			csvOutput.write("AccountID");
			csvOutput.endRecord();
			csvOutput.write(user.getUser());
			csvOutput.endRecord();
			csvOutput.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public SystemAdministrator getAdminSession() throws Exception{
		try {
			CsvReader reader = new CsvReader(userSessPath); 
			reader.readHeaders();
			reader.readRecord();
			String accID = reader.get("AccountID");
		
			
			for(SystemAdministrator admin: systemAdmin) {
				if(admin.getUser().equals(accID)) {
					return admin;
				}
			}
	
			reader.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void newParkSession(ParkingAuthority user) throws Exception{
		try {
			CsvWriter csvOutput = new CsvWriter(new FileWriter(userSessPath, false), ',');
			csvOutput.write("AccountID");
			csvOutput.endRecord();
			csvOutput.write(user.getAccountID());
			csvOutput.endRecord();
			csvOutput.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ParkingAuthority getAuthSession() throws Exception{
		try {
			CsvReader reader = new CsvReader(userSessPath); 
			reader.readHeaders();
			reader.readRecord();
			String accID = reader.get("AccountID");
			
			
			for(ParkingAuthority auth: parkingAuth) {
				if(auth.getAccountID().equals(accID)) {
					return auth;
				}
			}
	
			reader.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public void updateCustomers(ArrayList<Customer> custs)throws Exception{	
		try {
			CsvWriter csvOutput = new CsvWriter(new FileWriter(customerDbPath, false), ',');
			csvOutput.write("First Name");
			csvOutput.write("Last Name");
			csvOutput.write("User");
			csvOutput.write("Password");
			csvOutput.write("Email");
			csvOutput.write("Account ID");
			csvOutput.endRecord();
			
			for(Customer user: custs) {
				csvOutput.write(user.getFirstName());
				csvOutput.write(user.getLastName());
				csvOutput.write(user.getUser());
				csvOutput.write(user.getPass());
				csvOutput.write(user.getEmail());
				csvOutput.write(user.getAccountID());
				csvOutput.endRecord();
				}
			csvOutput.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadCustomer() throws Exception{
		customers = new ArrayList<Customer>();
		try {
			CsvReader reader = new CsvReader(customerDbPath); 
			reader.readHeaders();
			
			while(reader.readRecord()){ 
				

				Customer user;
				String fName = reader.get("First Name");
				String lName = reader.get("Last Name");
				String use = reader.get("User");
				String pass = reader.get("Password");
				String email = reader.get("Email");
				String accID = reader.get("Account ID");
				user = new Customer(fName,lName,use,pass,email,accID);
				user.setID(accID);
				customers.add(user);
				
			}
		}catch(Exception e) {
			
			e.printStackTrace();
		
		}
	}
	
	
	public void updateAuth(ArrayList<ParkingAuthority> auth)throws Exception{	
		try {
			CsvWriter csvOutput = new CsvWriter(new FileWriter(parkingAdminDbPath, false), ',');
			csvOutput.write("First Name");
			csvOutput.write("Last Name");
			csvOutput.write("User");
			csvOutput.write("Password");
			csvOutput.write("Email");
			csvOutput.write("Account ID");
			csvOutput.endRecord();
			
			for(ParkingAuthority user: auth) {
				csvOutput.write(user.getFirstName());
				csvOutput.write(user.getLastName());
				csvOutput.write(user.getUser());
				csvOutput.write(user.getPass());
				csvOutput.write(user.getEmail());
				csvOutput.write(user.getAccountID());
				csvOutput.endRecord();
				}
			csvOutput.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadAuth() throws Exception{
		parkingAuth = new ArrayList<ParkingAuthority>();
		try {
			CsvReader reader = new CsvReader(parkingAdminDbPath); 
			reader.readHeaders();
			
			while(reader.readRecord()){ 
				
				//name,id,email,password
				User user;
				String fName = reader.get("First Name");
				String lName = reader.get("Last Name");
				String use = reader.get("User");
				String pass = reader.get("Password");
				String email = reader.get("Email");
				String accID = reader.get("Account ID");
				user = new ParkingAuthority(fName,lName,use,pass,email,accID);
				user.setID(accID);
				parkingAuth.add((ParkingAuthority) user);
				
			}
		}catch(Exception e) {
			
			e.printStackTrace();
		
		}
	}

	
	public void loadAdmin() throws Exception{
		systemAdmin = new ArrayList<SystemAdministrator>();
		try {
			CsvReader reader = new CsvReader(systemAdminDbPath); 
			reader.readHeaders();
			
			while(reader.readRecord()){ 
				
		
	
				String use = reader.get("User");
				String pass = reader.get("Password");
			
				SystemAdministrator admin = new SystemAdministrator(use,pass);
				systemAdmin.add(admin);
			}
		}catch(Exception e) {
			
			e.printStackTrace();
		
		}
	}
	
	public void updateAdmin(ArrayList<SystemAdministrator> admin)throws Exception{	
		try {
			CsvWriter csvOutput = new CsvWriter(new FileWriter(systemAdminDbPath, false), ',');
			csvOutput.write("User");
			csvOutput.write("Password");
			csvOutput.endRecord();
			
			for(SystemAdministrator user: admin) {
				csvOutput.write(user.getUser());
				csvOutput.write(user.getPass());
				csvOutput.endRecord();
				}
			csvOutput.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	

}
