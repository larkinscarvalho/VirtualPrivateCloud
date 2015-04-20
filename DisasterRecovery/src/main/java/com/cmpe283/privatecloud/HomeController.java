package com.cmpe283.privatecloud;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cmpe283.privatecloud.DTO.ConfigDTO;
import com.cmpe283.privatecloud.DTO.UserDTO;
import com.cmpe283.privatecloud.DTO.VMDTO;
import com.cmpe283.privatecloud.constants.StringConstants;
import com.cmpe283.privatecloud.factory.ServiceFactory;
import com.cmpe283.privatecloud.service.interfaces.UserServices;
import com.cmpe283.privatecloud.service.interfaces.VMService;
import com.cmpe283.privatecloud.util.MailUser;



/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private final ServiceFactory service=new ServiceFactory();
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "login";
	}
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getIndex(HttpServletRequest request,Model model){
		return "index";
	}
	
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public String validateUser(HttpServletRequest request,Model model) {
		
		
		String userName=(String)request.getParameter(StringConstants.USERNAME);
		String password=(String)request.getParameter(StringConstants.PASSWORD);
		
		UserServices validate=service.getUserService();
		HttpSession session=request.getSession();
		session.putValue("key", "CMPE283".hashCode());
		session.setMaxInactiveInterval(1200);
		
		UserDTO userDTO=validate.validateUser(userName, password);
		if(userDTO!=null){
			session.putValue("userDTO", userDTO);
			model.addAttribute("userName", userName);
			return "index";
		}
		model.addAttribute("FailedLogin", "UserName and Password do not match");
		return "login";
		
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String getResgisterUserPage(){
		
		
		return "register";
		
	}
	
	
	@RequestMapping(value = "/registerinformation", method = RequestMethod.POST)
	public String registerUser(HttpServletRequest request,Model model) {
		
		
	
		String userName=(String)request.getParameter(StringConstants.USERNAME);
		String password=(String)request.getParameter(StringConstants.PASSWORD);
		UserDTO user=new UserDTO();
		user.setUserName(userName);
		user.setEmail((String)request.getParameter("email"));
		user.setPassword(password);
		user.setListofVMS(new ArrayList<String>());
		UserServices validate=service.getUserService();
		if(validate.registerUser(user)){
			model.addAttribute("registration","Registration Successfull Please Login");
			return "login";
		}
		
		
		return "failedLogin";
		
	}
	
	
	
	@RequestMapping(value = "/listVM", method = RequestMethod.GET)
	public String listVM(HttpServletRequest request,Model model) {
		
		HttpSession session=request.getSession();
		if(null!=session && null!=session.getAttribute("key")&& session.getAttribute("key")!=null  ){
		if(request.getSession()!=null &&  (Integer)session.getAttribute("key")=="CMPE283".hashCode()){
		UserDTO userDTO=(UserDTO)session.getAttribute("userDTO");
		
	   VMService vmservice=service.getVMService();
	   HashMap<String,String >vmState=vmservice.getVmList(userDTO.getListofVMS(),userDTO.getUserName());
	   
		model.addAttribute("vmlist", vmState);
		return "myvm";
		}
		}
		return "login";
		}
	
	@RequestMapping(value = "/createVM", method = RequestMethod.GET)
	public String getCreateVMPage(HttpServletRequest request){
	HttpSession session=request.getSession();
		if(null!=session ||null!=session.getAttribute("key")  ){
		if( (Integer)session.getAttribute("key")=="CMPE283".hashCode()){
		return "createvm";
		}
		}
		return "login";
	}
	
	@RequestMapping(value = "/createVM", method = RequestMethod.POST)
	public String createVM(HttpServletRequest request,Model model) {
		
		
		
		HttpSession session=request.getSession();
		if(null!=session ||null!=session.getAttribute("key") ){
		if(request.getSession()!=null && (Integer)session.getAttribute("key")=="CMPE283".hashCode()){
		UserDTO userDTO=(UserDTO)session.getAttribute("userDTO");
		
		String memory=(String)request.getParameter("memory");
		String location=(String)request.getParameter("location");
		String ip="";
		if(location.equals("West Coast")){
			ip="130.65.132.222";
		}else{
			ip="130.65.132.223";
		}
		
		long memorySize=Long.valueOf(memory);
		int cpu=Integer.valueOf((String)request.getParameter("cpu"));
		String os=(String)request.getParameter("optradio");
		
		ConfigDTO config=new ConfigDTO();
		config.setCpu(cpu);
		config.setMemory(memorySize);
		config.setOS(os);
        
		String vmName=(String)request.getParameter("vmname");
		
		
		
		VMDTO vminfo=new VMDTO();
		vminfo.setConfig(config);
		vminfo.setVmName(vmName);
		
		
		
	   VMService vmservice=service.getVMService();
	   boolean created=vmservice.createVirtualMachine(vminfo,userDTO.getUserName(),userDTO.getEmail(),ip);
	   
	   if(created){
		   
		   MailUser mail=new MailUser();
			String text="Dear "+ userDTO.getUserName()+"," + "\n\n You have requested for creation of  vm "+vmName+" with cpu="+cpu+"\n memory="+memorySize
					    +"\n we will notify you once your vm is created \n\n thanks,\n Team 10 Admin";
			mail.mailUser(text, userDTO.getEmail());
		   model.addAttribute("status","vmcreated");
		   model.addAttribute("name",vmName);
		   model.addAttribute("vmstate",true);
		   model.addAttribute("status"," Your VM ("+vmName +" ) creation is under process we will notify you by Email <"+userDTO.getEmail()+"> when your vm is ready for use ");
		   return "createvm";
	   }
	   
	   model.addAttribute("status","Change VM name else contact administrator");
	   return "createvm";
		}
		}
		return "login";
	}
	
	/*@RequestMapping(value = "/deleteVM", method = RequestMethod.POST)
	public String deleteVM(HttpServletRequest request,Model model) { 
	String vmName=request.getParameter("vmname");
	
	
	
	}*/
	
	@RequestMapping(value = "/getStat", method = RequestMethod.GET)
	public String getStat(HttpServletRequest request,Model model) {
		
        HttpSession session=request.getSession();
        if(null!=session ||null!=session.getAttribute("key")  ){
        if(request.getSession()!=null &&  (Integer)session.getAttribute("key")=="CMPE283".hashCode()){
		UserDTO userDTO=(UserDTO)session.getAttribute("userDTO");
		
		VMService vmservice=service.getVMService();
		
		model.addAttribute("vmstats", vmservice.getStatistics(userDTO.getListofVMS(),userDTO.getUserName()));
		
		return "vmusage";
        }
        }
        return "login";
		
	}
	
	

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logOut(HttpServletRequest request,Model model) {
	
		HttpSession session=request.getSession();
		request.getSession().removeAttribute("key");
		request.getSession().removeAttribute("userDTO");
		return "login";
	}
	
	@RequestMapping(value = "/powerChange/{power}/{vmName}", method = RequestMethod.POST)
	public @ResponseBody String powerChange(@PathVariable("power") String power,@PathVariable("vmName")String vmName ,HttpServletRequest request,Model model) {
		
		/*String power=request.getParameter("power");
		String vmName=request.getParameter("vmName");*/
		HttpSession session=request.getSession();
		UserDTO userDTO=(UserDTO)session.getAttribute("userDTO");
		VMService vmservice=service.getVMService();
		
		boolean status= vmservice.changePower(userDTO.getUserName(), vmName, power);
		String data="";
		if(status){
			data="true";
		}else{
			data="false";
		}
		return data;
	}
	
	}
	
	

