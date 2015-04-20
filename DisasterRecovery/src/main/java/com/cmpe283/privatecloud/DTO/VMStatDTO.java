package com.cmpe283.privatecloud.DTO;

public class VMStatDTO {
	
	private String name;
	private String guestName;
	private String availableMemory;
	private String privateMemeory;
	private String computerUsage;
	private String maxCPU;
	private String maxMemory;
	private String uptime;
	private String guestIP;
	
	public String getMaxMemory() {
		return maxMemory;
	}
	public void setMaxMemory(String maxMemory) {
		this.maxMemory = maxMemory;
	}
	
	public String getGuestIP() {
		return guestIP;
	}
	public void setGuestIP(String guestIP) {
		this.guestIP = guestIP;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getAvailableMemory() {
		return availableMemory;
	}
	public void setAvailableMemory(String availableMemory) {
		this.availableMemory = availableMemory;
	}
	public String getPrivateMemeory() {
		return privateMemeory;
	}
	public void setPrivateMemeory(String privateMemeory) {
		this.privateMemeory = privateMemeory;
	}
	public String getComputerUsage() {
		return computerUsage;
	}
	public void setComputerUsage(String computerUsage) {
		this.computerUsage = computerUsage;
	}
	public String getMaxCPU() {
		return maxCPU;
	}
	public void setMaxCPU(String maxCPU) {
		this.maxCPU = maxCPU;
	}
	public String getUptime() {
		return uptime;
	}
	public void setUptime(String uptime) {
		this.uptime = uptime;
	}

}
