package com.cmpe283.privatecloud.DAO.implementation;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.servlet.jsp.jstl.core.Config;

import com.cmpe283.privatecloud.DAO.interfaces.VMServiceDAO;
import com.cmpe283.privatecloud.DTO.ConfigDTO;
import com.cmpe283.privatecloud.DTO.UserDTO;
import com.cmpe283.privatecloud.DTO.VMDTO;
import com.cmpe283.privatecloud.DTO.VMStatDTO;
import com.cmpe283.privatecloud.util.GetMongoConnection;
import com.cmpe283.privatecloud.util.GetVMConnections;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.vmware.vim25.VirtualMachineRuntimeInfo;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ManagedEntity;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.VirtualMachine;

public class VMServiceDAOImplementation implements VMServiceDAO{
	
	
	public HashMap<String,String>getVmList(ArrayList<String>listOfVm,String userName){
		
		
		HashMap<String,String> getVmPowerList=new HashMap<String,String>();
		ServiceInstance si;
		if(GetVMConnections.serviceInstanceClass!=null){
			si=GetVMConnections.serviceInstanceClass.getServiceInstance();
			//GetVMConnections.serviceInstanceClass.setVms(si);
		}else{
			si=getServiceInstance();
			GetVMConnections getVmConnection=GetVMConnections.getInstance();
			getVmConnection.setServiceInstance(si);
			getVmConnection.setVms(si);
			
		}
		
		ArrayList<VirtualMachine>vmList=GetVMConnections.getVmList();
		
		ArrayList<String>latestVmList=getVmList(userName);
		
		
		for( String vm :latestVmList){
			
			for(VirtualMachine virtualMachine:vmList){
				System.out.println(virtualMachine.getName());
				if(!virtualMachine.getConfig().template){
				if(virtualMachine.getName().equals(vm)){
					getVmPowerList.put(vm, virtualMachine.getRuntime().getPowerState().toString());
					
				}
				}
				
				
			}
			}
		
		
		return getVmPowerList;
		
		
	}
	
	
	private  ArrayList<String> getVmList(String userName){
		
		DB db;
		ArrayList<String>listOfVms=new ArrayList<String>();
		try {
			db = GetMongoConnection.getConnection();
		
		DBCollection collection = db.getCollection("userDatabase");
		BasicDBObject doc = new BasicDBObject();
		
		doc.put("userName", userName);
		DBCursor cursor = collection.find(doc);
		
		if(cursor==null){
			return null;
		}
		
		
		while(cursor.hasNext()){
			
			DBObject dobj = cursor.next();
			listOfVms=(ArrayList<String>)dobj.get("vms");
			
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listOfVms;
		
	}
	
	
	
	private  ServiceInstance getServiceInstance(){
		try{
			URL url=new URL("https://130.65.132.110/sdk");
			ServiceInstance si = new ServiceInstance(url, "administrator", "12!@qwQW", true);
			
			if(si!=null){
				return si;
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return null;
		
	}
	
	
	public boolean createVirtualMachine(VMDTO vm,String userName,String emailId,String ip){
		ServiceInstance si;
		if(GetVMConnections.serviceInstanceClass!=null){
			si=GetVMConnections.serviceInstanceClass.getServiceInstance();
			
		}else{
			si=getServiceInstance();
			GetVMConnections getVmConnection=GetVMConnections.getInstance();
			getVmConnection.setServiceInstance(si);
			getVmConnection.setVms(si);
			
		}
		
		if(validateVmName(vm.getVmName())){
			return false;
		}
		
		
		String vmName=vm.getVmName();
		ConfigDTO config=vm.getConfig();
		CloneVM clone=new CloneVM(vm,userName,emailId,ip);
		clone.start();
		return true;
			
	}
	
	
	private boolean  validateVmName(String name){
		
		ArrayList<VirtualMachine>vmlist=GetVMConnections.getVmList();
		
		HashSet vmNames=new HashSet();
		
		for(VirtualMachine vm:vmlist){
			
			vmNames.add(vm.getName());
			
		}
		
		return vmNames.contains(name);
		
	}
	
	
	public ArrayList<VMStatDTO> getStatistics(ArrayList<String>uservmlist,String userName){
		ServiceInstance si;
		if(GetVMConnections.serviceInstanceClass!=null){
			si=GetVMConnections.serviceInstanceClass.getServiceInstance();
			//GetVMConnections.serviceInstanceClass.setVms(si);
		}else{
			si=getServiceInstance();
			GetVMConnections getVmConnection=GetVMConnections.getInstance();
			getVmConnection.setServiceInstance(si);
			getVmConnection.setVms(si);
			
		}
		
		ArrayList<VirtualMachine>vmList=GetVMConnections.getVmList();
		ArrayList<VMStatDTO>vmstatDTO=new ArrayList<VMStatDTO>();
		ArrayList<String>latestVmList=getVmList(userName);
           for( String vm :latestVmList){
			
			for(VirtualMachine virtualMachine:vmList){
				
				if(virtualMachine.getName().equals(vm)){
					 VirtualMachineRuntimeInfo vmri = (VirtualMachineRuntimeInfo) virtualMachine.getRuntime();
					VMStatDTO vmInfo=new VMStatDTO();
					vmInfo.setName(virtualMachine.getName());
					vmInfo.setGuestName(virtualMachine.getConfig().getGuestFullName());
					vmInfo.setGuestIP(virtualMachine.getSummary().guest.ipAddress);
					vmInfo.setAvailableMemory(String.valueOf(virtualMachine.getConfig().hardware.getMemoryMB()));
					vmInfo.setPrivateMemeory(String.valueOf(virtualMachine.getSummary().quickStats.getPrivateMemory()));
					vmInfo.setComputerUsage(String.valueOf(virtualMachine.getSummary().quickStats.overallCpuUsage));
					vmInfo.setUptime(String.valueOf(virtualMachine.getSummary().quickStats.uptimeSeconds));
					vmInfo.setMaxCPU(vmri.maxCpuUsage.toString());
					
					vmInfo.setMaxMemory(vmri.maxMemoryUsage.toString());
					vmstatDTO.add(vmInfo);
					
				}
				
				
			}
			}
		
		
		return vmstatDTO;
		
		
	}
	
	public boolean changePower(String userName,String vmName,String power){
		HashMap<String,String>vmstate=new HashMap<String,String>();
		
		try{
		ServiceInstance si;
		si=GetVMConnections.serviceInstanceClass.getServiceInstance();
		ArrayList<VirtualMachine>vmList=GetVMConnections.getVmList();
		
		for(VirtualMachine vm:vmList){
			
			if(vm.getName().equals(vmName)){
				
				if(vm.getRuntime().getPowerState().toString().equalsIgnoreCase("poweredOff")){
				
					vm.powerOnVM_Task(null);
					return true;
				}else{
					vm.powerOffVM_Task();
					return true;
				}
				
				
			}
		}
	}catch(Exception e){
		e.printStackTrace();
		return false;
	}
		
		
		return false;
	}
	
	

}
