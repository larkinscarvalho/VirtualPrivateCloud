package com.cmpe283.privatecloud.DAO.implementation;

import java.util.ArrayList;

import com.cmpe283.privatecloud.DTO.UserDTO;
import com.cmpe283.privatecloud.DTO.VMDTO;
import com.cmpe283.privatecloud.util.GetMongoConnection;
import com.cmpe283.privatecloud.util.GetVMConnections;
import com.cmpe283.privatecloud.util.MailUser;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.vmware.vim25.VirtualMachineCloneSpec;
import com.vmware.vim25.VirtualMachineConfigSpec;
import com.vmware.vim25.VirtualMachineRelocateSpec;
import com.vmware.vim25.mo.ComputeResource;
import com.vmware.vim25.mo.Datastore;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.HostSystem;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ManagedEntity;
import com.vmware.vim25.mo.ResourcePool;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.Task;
import com.vmware.vim25.mo.VirtualMachine;

public class CloneVM extends Thread {

	VMDTO vmdetails; 
	String userName;
	String emailId;
	String hostIp;
	
	public CloneVM(VMDTO vmdetails,String userName,String emailId,String hostIp){
		this.vmdetails=vmdetails;
		this.userName=userName;
		this.emailId=emailId;
		this.hostIp=hostIp;
		Thread t=new Thread();
		t.start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
		ServiceInstance si=GetVMConnections.getInstance().getServiceInstance();
		Folder rootFolder = si.getRootFolder();
		String vmtemplateName=vmdetails.getConfig().getOS().equals("Windows")? "WinTemplate" : "UbuntuTemplate";
		ManagedEntity mesHost = new InventoryNavigator(rootFolder).searchManagedEntity("HostSystem", hostIp);
		HostSystem hosts=(HostSystem)mesHost;

		//String resourcePool = null;
    	ResourcePool targetPool = null;
    	//ComputeResource hostResource = (ComputeResource) hosts.getParent();
    	//ManagedEntity[] rp = (ManagedEntity[]) new InventoryNavigator(rootFolder).searchManagedEntities("ResourcePool");
    	if(hostIp.equals("130.65.132.222")){
    	targetPool = (ResourcePool) new InventoryNavigator(rootFolder).searchManagedEntities("ResourcePool")[0];
    	}else{
    		targetPool = (ResourcePool) new InventoryNavigator(rootFolder).searchManagedEntities("ResourcePool")[1];
    	}
    	Datastore ds = (Datastore) new InventoryNavigator(rootFolder).searchManagedEntity("Datastore","nfs4team10");
		
		
    	
    	//VirtualMachine[]vms=hosts.getVms();
		
		
		VirtualMachine vm = (VirtualMachine) new InventoryNavigator(
		        rootFolder).searchManagedEntity(
		            "VirtualMachine", vmtemplateName);
		
		if(vm==null)
	    {
	      System.out.println("No OS " + vmtemplateName + " found");
	      si.getServerConnection().logout();
	      return;
	    }

		VirtualMachineRelocateSpec spec = new VirtualMachineRelocateSpec();
		spec.setHost(hosts.getMOR());
		spec.setPool(targetPool.getMOR());
		spec.setDatastore(ds.getMOR());

		
	    VirtualMachineCloneSpec cloneSpec = 
	      new VirtualMachineCloneSpec();
	    cloneSpec.setLocation(spec);
	    cloneSpec.setPowerOn(false);
	    cloneSpec.setTemplate(false);

	    Task task = vm.cloneVM_Task((Folder) vm.getParent(), 
	    		vmdetails.getVmName(), cloneSpec);
	    
	    String status = task.waitForTask();
	    
	    if(status==Task.SUCCESS){
	    	
	    	
	    	VirtualMachine newVm = (VirtualMachine) new InventoryNavigator(
			        rootFolder).searchManagedEntity(
			            "VirtualMachine", vmdetails.getVmName());
	    	InventoryNavigator inv = new InventoryNavigator(rootFolder);
	    	HostSystem host = (HostSystem) inv.searchManagedEntity("HostSystem", hostIp);
	    	/*HostSystem hosts=(HostSystem)new InventoryNavigator(
			        rootFolder).searchManagedEntity(
				            "HostSystem", vmdetails.getVmName());*/
	    	
	    	
	    	
	    	//ManagedEntity[] mesAdmin = inv.searchManagedEntities("ComputeResource");
	    	
	    	
	    	
	    	VirtualMachineConfigSpec configSpec = new VirtualMachineConfigSpec();
	    	configSpec.setMemoryMB(vmdetails.getConfig().getMemory());
			configSpec.setNumCPUs(vmdetails.getConfig().getCpu());
			newVm.reconfigVM_Task(configSpec);
			newVm.powerOnVM_Task(host);
			storeIndatabase(userName,vmdetails.getVmName());
			GetVMConnections.getInstance().getVmList().add(newVm);
			MailUser mail=new MailUser();
			String text="Dear "+ userName+"," + "\n\n Congrtulations !! your Vm "+vmdetails.getVmName()+" is now Created";
			mail.mailUser(text, emailId);
			return ;
	    }
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	private void storeIndatabase(String username,String vmName){
		
		try{
			DB db = GetMongoConnection.getConnection();
			DBCollection collection = db.getCollection("userDatabase");
			BasicDBObject doc = new BasicDBObject();
			doc.put("userName", userName);
			DBCursor cursor = collection.find(doc);
			DBCursor cursor1 = collection.find(doc);
			if(cursor==null){
				return ;
			}
			ArrayList<String>vms=new ArrayList<String>();
			while(cursor.hasNext()){
				
				DBObject dobj = cursor.next();
				
				vms=(ArrayList<String>)dobj.get("vms");
				
			}
			vms.add(vmName);
			
			while(cursor1.hasNext()){
				DBObject dobj1 = cursor1.next();
				dobj1.put("vms", vms);
				collection.update(doc, dobj1);
				
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

}
