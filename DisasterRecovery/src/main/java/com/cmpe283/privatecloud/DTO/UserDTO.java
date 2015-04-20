package com.cmpe283.privatecloud.DTO;

import java.util.ArrayList;

public class UserDTO {
	
	private String userName;
	private String password;
	private String email;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private ArrayList<String>listofVMS;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ArrayList<String> getListofVMS() {
		return listofVMS;
	}
	public void setListofVMS(ArrayList<String> listofVMS) {
		this.listofVMS = listofVMS;
	}

}
