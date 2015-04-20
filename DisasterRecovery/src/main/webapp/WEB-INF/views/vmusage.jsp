<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    

    <title>VM USAGE</title>

    <!-- Bootstrap CSS -->    
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <!-- bootstrap theme -->
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.css" rel="stylesheet">
    <!--external css-->
   
  
    
    <!-- Custom styles -->
	
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/style-responsive.css" rel="stylesheet" />
	
	<link href="${pageContext.request.contextPath}/resources/css/jquery-ui-1.10.4.min.css" rel="stylesheet">
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <!-- <script type="text/javascript">
      google.load("visualization", "1", {packages:["gauge"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['Label', 'Value'],
          ['Memory', 80],
          ['CPU', 55]
        ]);

        var options = {
          width: 800, height: 520,
          redFrom: 90, redTo: 100,
          yellowFrom:75, yellowTo: 90,
          minorTicks: 5
        };

        var chart = new google.visualization.Gauge(document.getElementById('chart_div'));

        chart.draw(data, options);

        setInterval(function() {
          data.setValue(0, 1, 40 + Math.round(60 * Math.random()));
          chart.draw(data, options);
        }, 13000);
        setInterval(function() {
          data.setValue(1, 1, 40 + Math.round(60 * Math.random()));
          chart.draw(data, options);
        }, 5000);
      }
    </script>
 -->
  </head>

  <body>
  <!-- container section start -->
  <section id="container" class="">
     
      
      <header class="header dark-bg">
            <div class="toggle-nav">
                <div class="icon-reorder tooltips" data-original-title="Toggle Navigation" data-placement="bottom"></div>
            </div>

            <!--logo start-->
            <a href="in" cldexass="logo">VM <span class="lite">Admin</span></a>
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
          
          <div class="row">
				<div class="col-lg-12">
					<h3 class="page-header"><i class="fa fa-laptop"></i>VM Stats!!</h3>
					
				</div>
			</div>          
              <!--overview start-->
			  <div class="table">
			  <c:choose>
    <c:when test="${vmstats ne null && not empty vmstats}">
			  <table class="table table-bordered">
			  <tr>
			  <th>Name</th>
			  <th>GuestName</th>
			  <th>GuestIP</th>
			  <th>Available Memory</th>
			  <th>Private Memory</th>
			  <th>Computer usage</th>
			  <th>Max CPU</th>
			  <th>Max Memory</th>
			  <th>UpTime</th>
			  </tr>
			  <c:forEach var="vms" items="${vmstats}">
			  <tr>
			  <td>${vms.name}</td>
			  <td>${vms.guestName}</td>
			  <td>${vms.guestIP}</td>
			  <td>${vms.availableMemory}</td>
			  <td>${vms.privateMemeory}</td>
			  <td>${vms.computerUsage}</td>
			  <td>${vms.maxCPU}</td>
			  <td>${vms.maxMemory}</td>
			  <td>${vms.uptime}</td>
			  </tr>
			  </c:forEach>
			  
			  </table>
			  
			  <h1>Format for SSH : ssh team-10@IPaddress of VM</h1>
			  <h1>For Windows RDP use IP address of VM</h1>
			  </c:when>
			  <c:otherwise >
               
   <h1>No Vm In your List</h1>
   
   </c:otherwise>
   </c:choose>
			     <%-- <select class="form-control" id="vm" name="vm">
			     <c:forEach var="vms" items="${vmstats}">
			     <option value="${vms.name}">${vms.name}</option>
                  </c:forEach>
                </select>
              <div class="col-lg-9 col-md-12">
                 <div id="chart_div" style="width: 400px; height: 120px;"></div>
               </div>  --%>
            </div>
			</div>
             
         
			
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
