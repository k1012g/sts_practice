package com.example.demo.trySpring;

import lombok.Data;

@Data
public class Employee {
	private int employeeId;
	private String employeeName;
	private int age;
	
	public int getEmployeeId() {
		return this.employeeId;
	}
	
	public String getEmployeeName() {
		return this.employeeName;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public void setEmployeeId(int id) {
		this.employeeId = id;
	}
	
	public void setEmployeeName(String name) {
		this.employeeName = name;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
}