<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    

    <title>VM User Login</title>

    <!-- Bootstrap CSS -->    
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <!-- bootstrap theme -->
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.css" rel="stylesheet">
    <!--external css-->
   
  
    
    <!-- Custom styles -->
	<link rel="stylesheet" href="css/fullcalendar.css">
	<link href="${pageContext.request.contextPath}/resources/css/widgets.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/style-responsive.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/resources/css/xcharts.min.css" rel=" stylesheet">	
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
            <a href="index.html" class="logo">VM <span class="lite">Admin</span></a>
            <!--logo end-->

           
            
                    
      </header>      
      <!--header end-->
<div id="loginModal" class="modal show" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog">
  <div class="modal-content">
      <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
          <h1 class="text-center">Login</h1>
      </div>

      <div class="modal-body">

    <!--FORM FOR LOGIN-->



          <form class="form col-md-12 center-block" method="post" action="validate">
          
            <div class="form-group">
              <input type="text" class="form-control input-lg" name="username" id="username" placeholder="User Name">
            </div>
            <div class="form-group">
              <input type="password" class="form-control input-lg" name="password" id="password" placeholder="Password">
            </div>
            <div class="form-group">
              <button class="btn btn-primary btn-lg btn-block"> Login</button>
              <span class="pull-right"><a href="register">Register</a></span>
              <c:if test="${FailedLogin ne null &&  not empty FailedLogin}">
              <p>${FailedLogin}</p>
              </c:if>
              
               <c:if test="${registration ne null &&  not empty registration}">
              <p>${registration}</p>
              </c:if>
            </div>
          </form>
      </div>


      <div class="modal-footer">
          <div class="col-md-12">
      
      </div>  
      </div>
  </div>
  </div>
</div>
      

  </body>
</html>
