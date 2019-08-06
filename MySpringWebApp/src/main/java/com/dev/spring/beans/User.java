package com.dev.spring.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users_info_jpa")
public class User {
	@Id @Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	private String firstName;
	private String lastName;
	private String password;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
				+ password + "]";
	}
	
	

}
