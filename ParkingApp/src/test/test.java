package test;







import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import system.System;
import system.Booking;
import system.Customer;
import system.ParkingAuthority;
import system.Parking_Spot;
import system.SystemAdministrator;


public class test {

	ArrayList<Customer> customers; 
	ArrayList<ParkingAuthority> parkingAuth;
	ArrayList<SystemAdministrator> systemAdmin;
	ArrayList<Parking_Spot> parking;
	
	ArrayList<Booking> bookings;
	
	System system;
	@Before
    public void setUp() throws Exception {
		system = new System();
		customers = new ArrayList<Customer>();
		parkingAuth = new ArrayList<ParkingAuthority>();
		parking = new ArrayList<Parking_Spot>();
		systemAdmin = new ArrayList<SystemAdministrator>();
		bookings = new ArrayList<Booking>();
		
		
		Customer c1 = new Customer("Test1","Last1","user1","pass","e1@.com","");
		Customer c2 = new Customer("Test2","Last2","user2","pass","e2@.com","");
		Customer c3 = new Customer("Test3","Last3","user3","pass","e3@.com","");
		Customer c4 = new Customer("Test4","Last4","user4","pass","e4@.com","");
		Customer c5 = new Customer("Test5","Last5","user5","pass","e5@.com","");
		customers.add(c1);
		customers.add(c2);
		customers.add(c3);
		customers.add(c4);
		customers.add(c5);
		
		ParkingAuthority p1 = new ParkingAuthority("P1","Last1","p1","pass","p1@.com","");
		ParkingAuthority p2 = new ParkingAuthority("P2","Last2","p2","pass","p2@.com","");
		ParkingAuthority p3 = new ParkingAuthority("P3","Last3","p3","pass","p3@.com","");
		ParkingAuthority p4 = new ParkingAuthority("P4","Last4","p4","pass","p4@.com","");
		parkingAuth.add(p1);
		parkingAuth.add(p2);
		parkingAuth.add(p3);
		parkingAuth.add(p4);

		SystemAdministrator admin = new SystemAdministrator("admin","admin");
		systemAdmin.add(admin);
		
		for(int i = 1; i < 7000;i++) {
			Parking_Spot spot = new Parking_Spot(i+"","5");
			parking.add(spot);
		}
		
		Booking b1 = new Booking("1","",false,c1.getAccountID(),"1:00","15","a1","2021-06-15");
		Booking b2 = new Booking("2","",false,c1.getAccountID(),"2:00","15","a2","2021-06-15");
		Booking b3 = new Booking("3","",false,c1.getAccountID(),"3:00","15","a3","2021-06-15");
		Booking b4 = new Booking("4","",false,c1.getAccountID(),"4:00","15","a4","2021-06-15");
		Booking b5 = new Booking("5","",false,c1.getAccountID(),"5:00","15","a5","2021-06-15");
		bookings.add(b1);
		bookings.add(b2);
		bookings.add(b3);
		bookings.add(b4);
		bookings.add(b5);
		
		system.updateParking(parking);
		
		system.updateBookings(bookings);
		system.updateCustomers(customers);
		system.updateAuth(parkingAuth);
		
		
    }
	
//	@Test
//	public void test_getUserBooking() throws Exception {
//		
//		ArrayList<Booking> books = system.getUserBooking(system.getCustomers().get(0));
//		Booking b1 = new Booking("1","",false,system.getCustomers().get(0).getAccountID(),"1:00","15","a1","2021-06-15");
//		Booking b2 = books.get(0);
//		Assert.assertEquals(1,books.size());
//		Assert.assertEquals(b1.getParkingSpot(),b2.getParkingSpot());
//		Assert.assertEquals(b1.getBookingID(),b2.getBookingID());
//		Assert.assertEquals(b1.isPaymentStatus(),b2.isPaymentStatus());
//		Assert.assertEquals(b1.getCustomerID(),b2.getCustomerID());
//		Assert.assertEquals(b1.getStartTime(),b2.getStartTime());
//		Assert.assertEquals(b1.getDuration(),b2.getDuration());
//		Assert.assertEquals(b1.getPlate(),b2.getPlate());
//		Assert.assertEquals(b1.getDate(),b2.getDate());
//	}
//	
	@Test
	public void test_updateBookings() throws Exception {
		Customer c1 = new Customer("Test1","Last1","user1","pass","e1@.com","");
		Booking b1 = new Booking("1","",false,c1.getAccountID(),"1:00","15","a1","2021-06-15");
		b1.setDate("2021-09-30");
		ArrayList<Booking> bk = new ArrayList<Booking>();
		bk.add(b1);
		system.updateBookings(bk);
		Assert.assertEquals(bk,system.getBookings());
	
	}
	
	
	@Test 
	public void test_getCustomers() {
		Assert.assertEquals(customers.size(),system.getCustomers().size());
	}
	
	@Test
	public void test_getParkingAuth() {
		Assert.assertEquals(parkingAuth.size(),system.getParkingAuth().size());
	}
	
	@Test
	public void test_getSystemAdmin() {
		Assert.assertEquals(systemAdmin.size(),system.getSystemAdmin().size());
	}
	
	@Test
	public void test_getBooking() {
		Assert.assertEquals(bookings,system.getBookings());
	}
	
	@Test 
	public void test_getParking() {
		Assert.assertEquals(parking.size(),system.getParkingSpots().size());
	}
	
	@Test
	public void test_loadParking() throws Exception {
		system.loadParkingSpots();
		Assert.assertEquals(system.getParkingSpots().size(),parking.size());
	}
	
	@Test
	public void test_updateParking() throws Exception {
		Parking_Spot spot = new Parking_Spot("43222","5");
		ArrayList<Parking_Spot> pSpot = new ArrayList<Parking_Spot>();
		pSpot.add(spot);
		system.updateParking(pSpot);
		system.loadParkingSpots();
		Assert.assertEquals(system.getParkingSpots().size(),pSpot.size());
	}
	
	@Test
	public void test_loadBooking() {
		Assert.assertEquals(bookings,system.getBookings());
	}
	
	@Test 
	public void test_newUserSession() throws Exception {
		Customer c1 = system.getCustomers().get(0);
		system.newUserSession(c1);
		Customer ret = system.getUserSession();
		Assert.assertEquals(c1.getAccountID(),ret.getAccountID());
		
		
	}
	 
	@Test
	public void test_getUserSession() throws Exception {
		Customer c1 = system.getCustomers().get(0);
		system.newUserSession(c1);
		Customer ret = system.getUserSession();
		Assert.assertEquals(c1.getAccountID(),ret.getAccountID());
	}
	
	@Test
	public void test_newAdminSession() throws Exception {
		SystemAdministrator sa = new SystemAdministrator("admin","admin");
		system.newAdminSession(sa);
		SystemAdministrator ret = system.getAdminSession();
		Assert.assertEquals(sa.getUser(),ret.getUser());
	}
	
	@Test
	public void test_getAdminSession() throws Exception {
		SystemAdministrator sa = new SystemAdministrator("admin","admin");
		system.newAdminSession(sa);
		SystemAdministrator ret = system.getAdminSession();
		Assert.assertEquals(sa.getUser(),ret.getUser());
	}
	
	@Test
	public void test_newParkSession() throws Exception {
		system.loadAuth();
		
		ParkingAuthority p1 = system.getParkingAuth().get(0);
		system.newParkSession(p1);
		ParkingAuthority ret = system.getAuthSession();
		Assert.assertEquals(p1.getAccountID(),ret.getAccountID());
	}
	
	@Test
	public void test_getAuthSession() throws Exception {
		system.loadAuth();
		
		ParkingAuthority p1 = system.getParkingAuth().get(0);
		system.newParkSession(p1);
		ParkingAuthority ret = system.getAuthSession();
		Assert.assertEquals(p1.getAccountID(),ret.getAccountID());
	}
	@Test
	public void test_updateCustomers() throws Exception {
		Customer c1 = new Customer("Test1","Last1","user1","pass","e1@.com","");
		ArrayList<Customer> c = new ArrayList<Customer>();
		c.add(c1);
		system.updateCustomers(c);
		system.loadCustomer();
		Assert.assertEquals(system.getCustomers().size(),c.size());
		
	}
	
	@Test
	public void test_loadCustomers() {
		Assert.assertEquals(customers.size(),system.getCustomers().size());
	}
	
	@Test 
	public void test_updateAuth() throws Exception {
		ParkingAuthority c1 = new ParkingAuthority("Test1","Last1","user1","pass","e1@.com","");
		ArrayList<ParkingAuthority> c = new ArrayList<ParkingAuthority>();
		c.add(c1);
		system.updateAuth(c);
		system.loadAuth();
		Assert.assertEquals(system.getParkingAuth().size(),c.size());
	}
	
	@Test 
	public void test_loadAuth() {
		Assert.assertEquals(parkingAuth.size(),system.getParkingAuth().size());
	}
	
	@Test
	public void test_loadAdmin() {
		Assert.assertEquals(systemAdmin.size(),system.getSystemAdmin().size());
	}
	
	@Test 
	public void test_updateAdmin() {
		SystemAdministrator admin = new SystemAdministrator("admin","admin");
		ArrayList<SystemAdministrator> ad = new ArrayList<SystemAdministrator>();
		ad.add(admin);
		Assert.assertEquals(ad.size(),system.getSystemAdmin().size());
	}
}
