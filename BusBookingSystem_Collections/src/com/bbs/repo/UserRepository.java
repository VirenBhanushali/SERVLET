package com.bbs.repo;

import java.util.HashMap;

import com.bbs.beans.Admin;
import com.bbs.beans.Available;
import com.bbs.beans.Booking;
import com.bbs.beans.Bus;
import com.bbs.beans.Ticket;
import com.bbs.beans.User;

public class UserRepository {

	public static HashMap<Integer, User> db=new HashMap<>();
	public static HashMap<Integer, Bus> busDb=new HashMap<>();
	public static HashMap<Integer, Ticket> ticketDb=new HashMap<>();
	public static HashMap<Integer, Available> availableDb= new HashMap<>();
	public static HashMap<Integer, Booking> bookingDb= new HashMap<>();
	public static HashMap<Integer, Admin> adminDb= new HashMap<>();
	
	public UserRepository() {
		User user1=new User();
		user1.setUserId(1);
		user1.setUserName("omkar");
		user1.setEmail("omkar@gmail.com");
		user1.setPassword("1234");
		user1.setContact(9090909090l);
		db.put(1,user1);
		
		User user2=new User();
		user2.setUserId(2);
		user2.setUserName("viren");
		user2.setEmail("viren@gmail.com");
		user2.setPassword("1234");
		user2.setContact(9090909090l);
		db.put(2,user2);
		
		User user3=new User();
		user3.setUserId(2);
		user3.setUserName("shri");
		user3.setEmail("shri@gmail.com");
		user3.setPassword("1234");
		user3.setContact(9090909090l);
		db.put(3,user3);
		
		Bus bus1 = new Bus();
		bus1.setBusId(101);
		bus1.setBusName("TMT");
		bus1.setBusType("NON-AC");
		bus1.setSource("Mumbai");
		bus1.setDestination("Pune");
		bus1.setTotalSeats(25);
		bus1.setPrice(500);
		busDb.put(bus1.getBusId(), bus1);
		
		Bus bus2 = new Bus();
		bus2.setBusId(102);
		bus2.setBusName("TMT");
		bus2.setBusType("NON-AC");
		bus2.setSource("Mumbai");
		bus2.setDestination("Pune");
		bus2.setTotalSeats(25);
		bus2.setPrice(500);
		busDb.put(bus2.getBusId(), bus2);

		Available availBus1=new Available();
		availBus1.setAvailId(301);
		availBus1.setAvailSeats(25);
		availBus1.setBusId(101);
		availBus1.setJourneyDate("2019-09-09");
		availableDb.put(availBus1.getBusId(), availBus1 );
		
		Available availBus2=new Available();
		availBus2.setAvailId(302);
		availBus2.setAvailSeats(25);
		availBus2.setBusId(102);
		availBus2.setJourneyDate("2019-09-09");
		availableDb.put(availBus2.getBusId(), availBus1 );
		
		Admin admin =new Admin();
		admin.setAdminId(9010);
		admin.setPassword("1234");
		adminDb.put(admin.getAdminId(), admin);
		

}
}
