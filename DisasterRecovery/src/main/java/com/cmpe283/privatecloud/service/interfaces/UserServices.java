package com.cmpe283.privatecloud.service.interfaces;

import com.cmpe283.privatecloud.DTO.UserDTO;

public interface UserServices {

	public UserDTO validateUser(String userName,String password);
	public boolean registerUser(UserDTO user);
}
