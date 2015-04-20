package com.cmpe283.privatecloud.factory;

import com.cmpe283.privatecloud.service.implementation.UserServicesImplementation;
import com.cmpe283.privatecloud.service.implementation.VMServiceImplementation;
import com.cmpe283.privatecloud.service.interfaces.UserServices;
import com.cmpe283.privatecloud.service.interfaces.VMService;

public class ServiceFactory {

	
	public UserServices getUserService(){
		
		return new UserServicesImplementation();
	}
	
	public VMService getVMService(){
		return new VMServiceImplementation();
	}
	
	
}
