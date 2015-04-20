package com.cmpe283.privatecloud.DAO.implementation;

import java.util.ArrayList;

import com.cmpe283.privatecloud.DAO.interfaces.UserServiceDAO;
import com.cmpe283.privatecloud.DTO.UserDTO;
import com.cmpe283.privatecloud.util.GetMongoConnection;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class UserServiceDAOImplementation implements UserServiceDAO {

	@Override
	public UserDTO validateUser(String userName, String password) {
		// TODO Auto-generated method stub
				try{
				if(userName==null || userName.isEmpty()){
					return null;
				}
				
				if(password==null || password.isEmpty()){
					return null;
				}
				
				DB db = GetMongoConnection.getConnection();
				DBCollection collection = db.getCollection("userDatabase");
				BasicDBObject doc = new BasicDBObject();
				
				doc.put("userName", userName);
				DBCursor cursor = collection.find(doc);
				
				if(cursor==null){
					return null;
				}
				
				while(cursor.hasNext()){
					
					DBObject dobj = cursor.next();
					if(password.equals((String)dobj.get("password"))){
						UserDTO user= new UserDTO();
						user.setUserName(userName);
						user.setPassword(password);
						user.setEmail((String)dobj.get("email"));
						ArrayList<String>listOfVms=(ArrayList<String>)dobj.get("vms");
						user.setListofVMS(listOfVms);
						return user;
					}
				}
				
				
				}catch(Exception e){
					e.printStackTrace();
					return null;
				}
				
				return null;
			
	}

	@Override
	public boolean registerUser(UserDTO user) {
		// TODO Auto-generated method stub
		try{
		DB db = GetMongoConnection.getConnection();
		DBCollection collection = db.getCollection("userDatabase");
		BasicDBObject doc = new BasicDBObject();
		doc.put("userName", user.getUserName());
		doc.put("email", user.getEmail());
		doc.put("password", user.getPassword());
		doc.put("vms", user.getListofVMS());
		collection.insert(doc);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
