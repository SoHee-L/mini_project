<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>

<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="ko">
	
<head>
	<meta charset="EUC-KR">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--   jQuery , Bootstrap CDN  -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	<!-- Bootstrap Dropdown Hover CSS -->
   <link href="/css/animate.min.css" rel="stylesheet">
   <link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
   
    <!-- Bootstrap Dropdown Hover JS -->
   <script src="/javascript/bootstrap-dropdownhover.min.js"></script>
	
	<!--  CSS 추가 : 툴바에 화면 가리는 현상 해결 :  주석처리 전, 후 확인-->
	<style>
        body {
            padding-top : 70px;
        }
        
        .carousel-inner .item {text-align:center;}
        .carousel-inner .item img {display: inline-block;}
        .container h1 {text-align:center;}
       
		.carousel-control.right{    background-image:linear-gradient(to right,rgb(122 45 45 / 0%) 0,rgb(236 207 176) 100%);}
		.carousel-control.left{    background-image:linear-gradient(to left,rgb(122 45 45 / 0%) 0,rgb(236 207 176) 100%);}	
		
		.navbar-inverse {background-image: linear-gradient(to bottom,#fce4d7 0,#e8caa7 100%);
						border-color: #e2c6aa;}
						
		.navbar-inverse .navbar-nav>li>a {
    							color: #1b1a19;}	
    							
    	.navbar-inverse .navbar-brand { color: #161414;}
    	
    	
    									
		    	
   	</style>
   	
     <!--  ///////////////////////// JavaScript ////////////////////////// -->
	 	
	
</head>
	
<body>

	<!-- ToolBar Start /////////////////////////////////////-->
	<jsp:include page="/layout/toolbar.jsp" />
   	<!-- ToolBar End /////////////////////////////////////-->

	<!--  아래의 내용은 http://getbootstrap.com/getting-started/  참조 -->	
   	<div class="container">
      <!-- Main jumbotron for a primary marketing message or call to action -->
     

	<!-- 참조 : http://getbootstrap.com/css/   : container part..... -->
	
	
	
	
	<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner" role="listbox">
    <div class="item active">
		      <img src="../images/y1.jpg" width="650" height="433" alt="First slide">
		    </div>
		   
		    
		    <div class="item">
		      <img src="/images/y2.jpg" width="650" height="433" alt="Second slide">
		    </div>
		      
		    <div class="item">
		    	<img src="../images/y3.jpg" width="650" height="433" alt="Third slide">
		    </div>

  <!-- Controls -->
  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>

</body>

</html>