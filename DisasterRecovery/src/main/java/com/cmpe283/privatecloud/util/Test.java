package com.cmpe283.privatecloud.util;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Test {
	
	
	/*public static void main(String[] args) {
		 
		final String username = "cmpe283team10@gmail.com";
		final String password = "TeamTen10";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("cmpe283team10@gmail.com"));
			message.setRecipients(javax.mail.Message.RecipientType.TO,
				InternetAddress.parse("arpitjoshi_177@yahoo.com"));
			message.setSubject("VM Created");
			message.setText("Dear User,"
				+ "\n\n your Vm is Created , please check!");
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
*/
	
	      
		public static void main(String[] args) throws InterruptedException {
			// TODO Auto-generated method stub
			
			try {
				System.out.println(System.getProperty("os.name"));
				String os=System.getProperty("os.name");
				String command="";
				if(os.equals("Linux")){
			      //command= "/usr/bin/xterm  "; 
					command="/usr/bin/gnome-terminal";
		    	Runtime rt = Runtime.getRuntime(); 	
		    	//Process pr = rt.exec(command);
		    	String []cmdarray={"/usr/bin/xterm","-c",". abc.sh"};
		    	rt.exec(cmdarray);
		    	//File 
		    	//rt.exec(cmdarray, new String[0],new File( "/home/arpit/Documents/KSTechnologies"));
		    	//rt.exec(cmdarray,"/home/arpit/Documents/KSTechnologies/abc.sh");
				}
				if(os.contains("Windows")){
					command="cmd /c start cmd.exe";
					Runtime rt = Runtime.getRuntime(); 	
			    	Process pr = rt.exec(command);
				}
				
				if(os.equals("Mac")){
					command="/bin/bash";
					Runtime rt = Runtime.getRuntime(); 	
			    	Process pr = rt.exec(command);
					
				}
				
		        System.out.println("ok");
		    } catch (IOException ex) {
		        ex.printStackTrace();
		    }
		}

}