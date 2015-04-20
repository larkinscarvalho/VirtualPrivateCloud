package com.cmpe283.privatecloud.service.implementation;

import java.util.ArrayList;
import java.util.HashMap;

import com.cmpe283.privatecloud.DAO.implementation.VMServiceDAOImplementation;
import com.cmpe283.privatecloud.DAO.interfaces.VMServiceDAO;
import com.cmpe283.privatecloud.DTO.VMDTO;
import com.cmpe283.privatecloud.DTO.VMStatDTO;
import com.cmpe283.privatecloud.factory.DAOFactory;
import com.cmpe283.privatecloud.service.interfaces.VMService;

public class VMServiceImplementation implements VMService{

	private final DAOFactory factory=new DAOFactory();
	public HashMap<String,String>getVmList(ArrayList<String>listOfVm,String userName){
		
		VMServiceDAO vm=factory.getVMServiceDAO();
		
		/*if(listOfVm==null || listOfVm.size()==0){
			return null;
		}*/
		
		return vm.getVmList(listOfVm,userName);
		
	}
	
	public boolean createVirtualMachine(VMDTO vm,String userName,String emailId,String ip){
		
		VMServiceDAO vmservice=factory.getVMServiceDAO();
		
		return vmservice.createVirtualMachine(vm,userName,emailId,ip);
		
		
	}
	
	public ArrayList<VMStatDTO> getStatistics(ArrayList<String>uservmlist,String userName){
		if(!uservmlist.isEmpty()){
			VMServiceDAO vm=factory.getVMServiceDAO();
			return vm.getStatistics(uservmlist,userName);
		}
		
		return null;
	}
	
	public boolean changePower(String userName,String vmName,String power){
		VMServiceDAO vm=factory.getVMServiceDAO();
		return vm.changePower(userName, vmName, power);
		
	}
}
