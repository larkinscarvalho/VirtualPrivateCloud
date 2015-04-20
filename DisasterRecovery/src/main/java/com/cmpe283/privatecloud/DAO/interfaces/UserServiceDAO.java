package com.cmpe283.privatecloud.DAO.interfaces;

import com.cmpe283.privatecloud.DTO.UserDTO;

public interface UserServiceDAO {
	public UserDTO validateUser(String userName,String password);
	public boolean registerUser(UserDTO user);
}
