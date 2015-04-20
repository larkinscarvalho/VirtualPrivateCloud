<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   

    <title>VM List</title>

    <!-- Bootstrap CSS -->    
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- bootstrap theme -->
    <link href="css/bootstrap-theme.css" rel="stylesheet">
    <!--external css-->
   
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    
    <!-- Custom styles -->
	
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/style-responsive.css" rel="stylesheet" />
	
	<link href="${pageContext.request.contextPath}/resources/css/jquery-ui-1.10.4.min.css" rel="stylesheet">
	
	<script>
    
    function changePower(vmname,powerstatus1){
    	
    	
    	var id="#"+vmname;
    	var powerstatus=$(id).val();
    	var previousstatus=powerstatus;
    	var bgcolor="";
    	  $(id).prop('disabled',true);
    	 setTimeout(function() {
 	        $(id).prop('disabled',true);
 	       $(id).removeAttr("disabled");
 	    },6000); 
    	 
    	//$(id).prop("disabled",true);
    	
    	/* if(powerstatus=="poweredOn"){
    		$(id).css({"background-color": "red"});
    		$(id).html("poweredOff");
    		$(id).val("poweredOff");
    		bgcolor="green";
    	}else{
    		$(id).css({"background-color": "green"});
    		$(id).html("poweredOn");
    		$(id).val("poweredOn");
    		bgcolor="red";
    	} */
    	
    	
    	$.ajax({
	         type:"POST",
	         url:'/privatecloud/powerChange/'+powerstatus+'/'+vmname,
	         success:function(data){
	        	 if(data=="true"){
	        		 if(powerstatus=="poweredOn"){
	        	    		$(id).css({"background-color": "red"});
	        	    		$(id).html("poweredOff");
	        	    		$(id).val("poweredOff");
	        	    		bgcolor="green";
	        	    	}else{
	        	    		$(id).css({"background-color": "green"});
	        	    		$(id).html("poweredOn");
	        	    		$(id).val("poweredOn");
	        	    		bgcolor="red";
	        	    	}
	        		 
	        		 
	        		 
	        		 //$(id).removeAttr("disabled");
	        	 }else{
	        		 alert("Cannot restart");
	        		 $(id).val(previousstatus);
	        		 $(id).css({"background-color":bgcolor});
	        		 $(id).html(previousstatus);
	        		 $(id).removeAttr("disabled");
	        		 
	        	 }
	        	
	        	 
	         }
    	});	 
    	
    }
    
    
    </script>
    
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 -->
    <!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
      <script src="js/respond.min.js"></script>
      <script src="js/lte-ie7.js"></script>
    <![endif]-->
  </head>

  <body>
  <!-- container section start -->
  <section id="container" class="">
     
      
      <header class="header dark-bg">
            <div class="toggle-nav">
                <div class="icon-reorder tooltips" data-original-title="Toggle Navigation" data-placement="bottom"></div>
            </div>

            <!--logo start-->
            <a href="index" class="logo">VM <span class="lite">Admin</span></a>
            <a href="logout">LogOut</a>
            <!--logo end-->
      </header>      
      <!--header end-->

      <!--sidebar start-->
      <aside>
          <div id="sidebar"  class="nav-collapse ">
              <!-- sidebar menu start-->
              <ul class="sidebar-menu">                
                  <li class="active">
                      <a class="" href="index">
                          <i class="icon_house_alt"></i>
                          <span>Dashboard</span>
                      </a>
                  </li>

                  <!-- 1st Sidebar menu-->
          <li class="sub-menu">
                      <a href="javascript:;" class="">
                          <i class="icon_document_alt"></i>
                          <span>VM tasks</span>
                          <span class="menu-arrow arrow_carrot-right"></span>
                      </a>
                      <ul class="sub">
                          <li><a class="" href="createVM">Create/Deploy VM</a></li>
                          <li><a class="" href="#">Connect to VM</a></li>
                      </ul>
                  </li>  

                  <!-- 2nd Sidebar menu--> 

                  <li class="sub-menu">
                      <a href="javascript:;" class="">
                          <i class="icon_document_alt"></i>
                          <span>VM stats</span>
                          <span class="menu-arrow arrow_carrot-right"></span>
                      </a>
                      <ul class="sub">                          
                          <li><a class="" href="listVM">VM Status</a></li>
                          <li><a class="" href="getStat">Usage Stats</a></li>
                      </ul>
                  </li>       
                  
              </ul>
              <!-- sidebar menu end-->
          </div>
      </aside>
      <!--sidebar end-->
      
      <!--main content start-->
      <section id="main-content">
          <section class="wrapper">            
              <!--overview start-->
			  <div class="row">
				<div class="col-lg-12">
					<h3 class="page-header"><i class="fa fa-laptop"></i>VM STATUS!!</h3>
					
				</div>
			</div>
              <table class="table table-bordered" style="border:1px solid black" width="800">
    <thead>
      <tr>
        <th style="border:1px solid black">VM Name</th>
        
        <!-- <th>State</th> -->
        <th style="border:1px solid black">Change Power</th>
      </tr>
    </thead>
    <tbody>
    <c:choose>
    <c:when test="${vmlist ne null && not empty vmlist}">
    <c:forEach var="vm" items="${vmlist}">
    <tr>
   <td style="border:1px solid black">${vm.key}</td>
  <%--   <c:choose>
    <c:when test="${vm.value == 'poweredOff'}">
    <td style=color:red >${vm.value}</td>
    </c:when>
    <c:otherwise>
    <td style=color:green >${vm.value}</td>
    </c:otherwise>
    
    </c:choose>   --%>
    
    <c:choose>
    <c:when test="${vm.value == 'poweredOff'}">
    <td style="border:1px solid black">
    <button name="${vm.value}" id="${vm.key}" value="${vm.value}"  style="background-color:red" onClick=changePower("${vm.key}","${vm.value}")>${vm.value}</button>
    <input type="hidden" value="${vm.key}" name="${vm.key}"/>
    </td>
    </c:when>
    <c:otherwise>
    <td style="border:1px solid black">
    <button name="${vm.value}" value="${vm.value}"  id="${vm.key}" style="background-color:green" onClick=changePower("${vm.key}","${vm.value}")>${vm.value}</button>
    <input type="hidden" value="${vm.key}" name="${vm.key}"/>
    </td>
    </c:otherwise>
    
    </c:choose>
    
   
    </tr>
    </c:forEach>
    </c:when>
   <c:otherwise >
   <tr>
   <td>No Vm In your List</td>
   </tr>
   </c:otherwise>
   </c:choose>
     </tbody>
  </table>
          
			
              <!-- project team & activity end -->

          </section>
      </section>
      <!--main content end-->
  </section>
  <!-- container section start -->

    <!-- javascripts -->
    <script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery-ui-1.10.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui-1.9.2.custom.min.js"></script>
    <!-- bootstrap -->
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <!-- nice scroll -->
    <script src="${pageContext.request.contextPath}/resources/js/jquery.scrollTo.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.nicescroll.js" type="text/javascript"></script>
    
   
  
   
    <!--custome script for all page-->
    <script src="${pageContext.request.contextPath}/resources/js/scripts.js"></script>
    <!-- custom script for this page-->
    
  

  </body>
</html>
