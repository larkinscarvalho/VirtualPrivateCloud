package com.cmpe283.privatecloud.service.implementation;


import com.cmpe283.privatecloud.DAO.interfaces.UserServiceDAO;
import com.cmpe283.privatecloud.DTO.UserDTO;
import com.cmpe283.privatecloud.factory.DAOFactory;
import com.cmpe283.privatecloud.service.interfaces.UserServices;

public class UserServicesImplementation implements UserServices{
	private final DAOFactory factory=new DAOFactory();
	@Override
	public UserDTO validateUser(String userName, String password) {
		// TODO Auto-generated method stub
		
		
		UserServiceDAO validateUser=factory.getUserServiceDAO();
		return validateUser.validateUser(userName, password);
	}

	@Override
	public boolean registerUser(UserDTO user) {
		// TODO Auto-generated method stub
		if(user.getUserName()!=null || user.getPassword()!=null){
		
			UserServiceDAO validateUser=factory.getUserServiceDAO();
			return validateUser.registerUser(user);
		}
		return false;
	}

}
