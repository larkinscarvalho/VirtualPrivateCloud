<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
  

    <title>VM Admin Panel</title>

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
					<h3 class="page-header">Welcome!! ${userName}</h3>
					
				</div>
			</div>
            
					       
<!--CODE GOES HERE-->


 <div class="row">
<div class="col-lg-12 col-md-3 col-sm-12 col-xs-12">
<div class="info-box brown-bg">
<div class="count"><a href="createVM">CREATE VIRTUAL MACHINE</a></div>
</div><!--/.info-box-->
</div><!--/.col-->
<div class="col-lg-12 col-md-3 col-sm-12 col-xs-12">
<div class="info-box dark-bg">
<div class="count"><a href="listVM">CHECK AVAILABLE VM</a></div>

</div><!--/.info-box-->
</div><!--/.col-->
        <div class="col-lg-12 col-md-3 col-sm-12 col-xs-12">
          <div class="info-box brown-bg">
            
            <div class="count"><a href="getStat">CHECK VM USAGE STATISTICS</a></div>
                      
          </div><!--/.info-box-->     
        </div><!--/.col-->
</div><!--/.row-->
               </div>	
						</div>
	
					</div>
				</div>
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
    
   
  <script type="text/javascript" src="https://www.google.com/jsapi?autoload={'modules':[{'name':'visualization','version':'1.1','packages':['gauge']}]}"></script>
   
    <!--custome script for all page-->
    <script src="${pageContext.request.contextPath}/resources/js/scripts.js"></script>
    <!-- custom script for this page-->
  

  </body>
</html>