package com.example.demo.login.domain.model;

import java.util.Date;
import lombok.Data;

@Data
public class User {
	private String userId;
	private String password;
	private String userName;
	private Date birthday;
	private int age;
	private boolean marriage;
	private String role;
	
	public String getUserId() {
		return this.userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Date getBirthday() {
		return this.birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public boolean getMarriage() {
		return this.marriage;
	}
	
	public void setMarriage(boolean marriage) {
		this.marriage = marriage;
	}
	
	public String getRole() {
		return this.role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
}