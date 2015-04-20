<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    

    <title>VM Creation</title>

    <!-- Bootstrap CSS -->    
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <!-- bootstrap theme -->
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.css" rel="stylesheet">
    <!--external css-->
   
  
    
    <!-- Custom styles -->
	
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/style-responsive.css" rel="stylesheet" />
	
	<link href="${pageContext.request.contextPath}/resources/css/jquery-ui-1.10.4.min.css" rel="stylesheet">
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
					<h3 class="page-header"><i class="fa fa-laptop"></i>Create VM!!</h3>
					<%-- <c:if test="${name ne null &&  not empty name}">
					<p>${name} Created ,Please check after sometime</p>
					
					</c:if> --%>
					<c:if test="${status ne null &&  not empty status}">
					<p>${status}</p>
					
					</c:if>
				</div>
			</div>
             
<!--FORM TO CREATE VM-->
      <form role="form" method="POST" action="createVM">
    <div class="form-group">
      <label for="usr">VM Name:</label>
      <input type="text" class="form-control" name="vmname" id="name" placeholder="Enter VM Name" required >
    </div>
    <div class="form-group">
      <label for="usr">Memory:</label>
      <select class="form-control" id="memory" name="memory" required>
        <option value="1024">1024</option>
        <option value="2048">2048</option>
      </select>
    </div>
    <label for="usr">OS:</label>
    <label class="radio-inline">
      <input type="radio" name="optradio"  id="os" value="Windows" >Windows
    </label>
    <label class="radio-inline">
      <input type="radio" name="optradio" id="os" value="Ubuntu" required>Ubuntu
    </label>

    <br>

    <label for="sel1">CPU:</label>
      <select class="form-control" id="cpu" name="cpu" required>
        <option value="1">1</option>
        <option value="2">2</option>
      </select>
      
      <label for="sel1">Location:</label>
      <select class="form-control" id="location" name="location" required>
        <option value="West Coast">West Coast</option>
        <option value="East Coast">East Coast</option>
      </select>
<br>
    <button type="submit" class="btn btn-default">CREATE</button>
  </form>
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
