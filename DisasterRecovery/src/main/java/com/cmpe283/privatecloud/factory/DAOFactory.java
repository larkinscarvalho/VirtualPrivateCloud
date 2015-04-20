package com.cmpe283.privatecloud.factory;

import com.cmpe283.privatecloud.DAO.implementation.UserServiceDAOImplementation;
import com.cmpe283.privatecloud.DAO.implementation.VMServiceDAOImplementation;
import com.cmpe283.privatecloud.DAO.interfaces.UserServiceDAO;
import com.cmpe283.privatecloud.DAO.interfaces.VMServiceDAO;

public class DAOFactory {

	public UserServiceDAO getUserServiceDAO(){
		return new UserServiceDAOImplementation();
		
	}
	
	public VMServiceDAO getVMServiceDAO(){
		return new VMServiceDAOImplementation();
	}
}
