package com.cmpe283.privatecloud.util;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;

import com.vmware.vim25.InvalidProperty;
import com.vmware.vim25.RuntimeFault;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ManagedEntity;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.VirtualMachine;

public class GetVMConnections {
	
	
public static GetVMConnections serviceInstanceClass;
	
	private static ServiceInstance serviceInstance;
	private  static  ArrayList<VirtualMachine>virtualMachines=new ArrayList<VirtualMachine>();
	
	private GetVMConnections(){
		
	}
	
	private  ServiceInstance getServiceInstanceOnce(){
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
	
	
	public static GetVMConnections getInstance(){
		serviceInstanceClass=new GetVMConnections();
		return serviceInstanceClass;
	}

	
	public void setServiceInstance(ServiceInstance si){
		serviceInstance=si;
	}
	
	public ServiceInstance getServiceInstance(){
		return serviceInstance;
	}
	
	public void setVms(ServiceInstance si){
		
		if(si==null){
			si=getServiceInstanceOnce();
			serviceInstance=si;
			
		}
		Folder rootFolder = si.getRootFolder();
		ManagedEntity[] mes;
		try {
			mes = new InventoryNavigator(rootFolder).searchManagedEntities("VirtualMachine");
			for(int i=0;i<mes.length;i++){
				
				
				VirtualMachine vm=(VirtualMachine)mes[i];
				if(null!=vm && !vm.getConfig().template){
				virtualMachines.add(vm);
				}
				}
		} catch (InvalidProperty e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RuntimeFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public static ArrayList<VirtualMachine> getVmList(){
		return virtualMachines;
	}

}
