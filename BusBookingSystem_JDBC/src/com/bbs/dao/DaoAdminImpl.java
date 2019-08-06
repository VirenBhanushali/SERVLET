package com.bbs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.bbs.beans.Available;
import com.bbs.beans.Bus;
import com.bbs.beans.Feedback;
import com.bbs.exceptions.CustomException;
import com.bbs.exceptions.DeleteFailedException;
import com.bbs.exceptions.UpdateFailedException;

public class DaoAdminImpl implements DaoAdmin {
	String url = "jdbc:mysql://localhost:3306/busbookingsystem_db?user=root&password=root";
	Connection conn;
	java.sql.Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs = null;
	Bus bus = new Bus();

	public DaoAdminImpl(){
		try {
			//load Driver
			java.sql.Driver div=new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(div);

			//GetConnection
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			throw new CustomException("CantGetConnection");
		}


	}

	@Override
	public Boolean createBus(Bus bus) {
		try {

			//iSSUE SQL Query
			//iNSERT BUS INFO
			String q = "Insert INTO bus_info values(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(q);
			pstmt.setInt(1,bus.getBusId());
			pstmt.setString(2,bus.getBusName());
			pstmt.setString(3,bus.getSource());
			pstmt.setString(4,bus.getDestination());
			pstmt.setString(5,bus.getBusType());
			pstmt.setInt(6,bus.getTotalSeats());
			pstmt.setDouble(7,bus.getPrice());
			int i = pstmt.executeUpdate();
			if(i>0)
			{

				return true;
			}
		}catch(Exception e) {
			throw new CustomException("CreateBusFailed");
		}
		return false;

	}

	@Override
	public Boolean updateBus(Bus bus) {
		try {

			//Issue SQL quERY
			//UPDATE BUS INFO
			String q2 = "update bus_info set source=?,destination=?,price=? where bus_id=?";
			pstmt = conn.prepareStatement(q2);
			pstmt.setInt(4,bus.getBusId());
			pstmt.setString(1,bus.getSource());
			pstmt.setString(2,bus.getDestination());
			pstmt.setDouble(3, bus.getPrice());
			int j = pstmt.executeUpdate();
			if(j > 0)
			{
				return true;
			}
			else
			{

				return false;
			}

		}
		catch(Exception e) {
			throw new UpdateFailedException("FailedTOUpdate");
		}


	}

	@Override
	public Bus searchBus(int busId) {
		try {
			//GET BUSES BASED ON ID
			String q1 = "Select * from bus_info where bus_id=?";
			pstmt = conn.prepareStatement(q1);
			pstmt.setInt(1, busId);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				int busid = rs.getInt("bus_id");
				String busName = rs.getString("busname");
				String source = rs.getString("source");
				String destination = rs.getString("destination");
				String busType = rs.getString("bus_Type");
				int totalSeats = rs.getInt("total_seats");
				double price = rs.getDouble("price");
				bus.setBusId(busid);
				bus.setBusName(busName);
				bus.setSource(source);
				bus.setDestination(destination);
				bus.setBusType(busType);
				bus.setTotalSeats(totalSeats);
				bus.setPrice(price);
				if(bus != null)
				{
					return bus;
				}
				else {
					return null;
				}
			}
		}
		catch(Exception e)
		{
			throw new CustomException("SearchBusException");
		}
		return null;
	}

	@Override
	public Boolean deletebus(int busId, String password) {
		try {
			//dELETE BUS BASED ON ID
			//issue sql query
			String q3="DELETE FROM bus_INFO WHERE bus_id=?";
			pstmt = conn.prepareStatement(q3);
			pstmt.setInt(1,busId);
			int k = pstmt.executeUpdate();
			if(k>0)
			{
				return true;
			}
			else
			{
				return false;
			}


		}
		catch(Exception e)
		{
			throw new DeleteFailedException("DeleteFailed");
		}
	}


	@Override
	public Boolean adminLogin(int adminId, String password) {
		try {
			//ADMIN LOGIN VALIDATION
			//Issue SQL Query
			String q4 = "SELECT * FROM admin_INFO WHERE admin_ID=? AND PASSWORD=?";
			pstmt = conn.prepareStatement(q4);
			pstmt.setInt(1, adminId);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				return true;
			}
		}


		catch(Exception e) {
			 throw new com.bbs.exceptions.LoginException("LoginFAILED");
		}
		return false;

	}
	@Override
	public Boolean addAvailability(Available available) {
		try {
			//add availability
			//iSSUE SQL Query
			String q = "Insert INTO availability values(?,?,?,?)";
			pstmt = conn.prepareStatement(q);
			pstmt.setInt(1, available.getAvailId());
			Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(available.getDate());
			java.sql.Date date = new java.sql.Date(date1.getTime());
			pstmt.setDate(2, date);
			pstmt.setInt(3, available.getAvailSeats());
			pstmt.setInt(4, available.getBusId());
			int i = pstmt.executeUpdate();
			if(i>0)
			{
				return true;
			}
		}catch(Exception e) {
			throw new CustomException("AvailableBusFailed");
		}
		return false;
	}
	
	@Override
	public List<Feedback> showFeedback() {
		//Take ALL suggestion from suggestions Table
		String query = "SELECT * FROM SUGGESTION";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			int i = 0;
			List<Feedback> feedbacks = new ArrayList<Feedback>();
			//Storing Feedback in tables
			while(rs.next())
			{
				Feedback feedback = new Feedback();
				feedback.setSuggestionId(rs.getInt("sugg_id"));
				feedback.setUserId(rs.getInt("user_id"));
				feedback.setFeedback(rs.getString("feedback"));
				feedbacks.add(i, feedback);
				i++;
			}
			return feedbacks;
			
			
		} catch (SQLException e) {
			return null;
		}
	}

	

	@Override
	public HashMap<Integer, Bus> busBetween(String source, String destination) {
		
		try {
			//Issue SQL Query
			//Search Bus BY source and destination
			String q4 = "SELECT * FROM bus_INFO WHERE source=? and destination=?";
			pstmt = conn.prepareStatement(q4);
			pstmt.setString(1, source);
			pstmt.setString(2, destination);
			rs = pstmt.executeQuery();
			HashMap<Integer, Bus> map = new HashMap<>();
			//Storing Buses in Hashmap
            Integer increment=1;
			while(rs.next())
			{
				Bus bus = new Bus();
				int busid = rs.getInt("bus_id");
				String busName = rs.getString("busname");
				source = rs.getString("source");
				destination = rs.getString("destination");
				String busType=rs.getString("bus_Type");
				int totalSeats = rs.getInt("total_seats");
				double price = rs.getDouble("price");
				bus.setBusId(busid);
				bus.setBusName(busName);
				bus.setSource(source);
				bus.setDestination(destination);
				bus.setBusType(busType);
				bus.setTotalSeats(totalSeats);
				bus.setPrice(price);
				map.put(increment, bus);
				increment++;
			}
			return map;
		} catch (Exception e) {
			throw new CustomException("SearchBusException");
		}finally {
			if(conn != null)
			{
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null)
			{
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs != null)
			{
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}



	}

	
}


