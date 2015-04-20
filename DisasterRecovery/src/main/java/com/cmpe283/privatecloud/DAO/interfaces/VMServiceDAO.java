package com.cmpe283.privatecloud.DAO.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

import com.cmpe283.privatecloud.DTO.VMDTO;
import com.cmpe283.privatecloud.DTO.VMStatDTO;

public interface VMServiceDAO {

	
	public HashMap<String,String>getVmList(ArrayList<String>listOfVm,String userName);
	public boolean createVirtualMachine(VMDTO vm,String userName,String emailID,String ip);
	public ArrayList<VMStatDTO> getStatistics(ArrayList<String>uservmlist,String userName);
	public boolean changePower(String userName,String vmName,String power);
	
}
