<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>


<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- ///////////////////////////// �α��ν� Forward  /////////////////////////////////////// -->
 <c:if test="${ ! empty user }">
 	<jsp:forward page="main.jsp"/>
 </c:if>
 <!-- //////////////////////////////////////////////////////////////////////////////////////////////////// -->


<!DOCTYPE html>

<html lang="ko">
	
<head>
	<meta charset="EUC-KR">
	
	<!-- ���� : http://getbootstrap.com/css/   -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	<!--  ///////////////////////// CSS ////////////////////////// -->
	<style>
	.btn-info{background-image: linear-gradient(to bottom,#e78d7a 0,#fee4d7 100%);
				border-color: #fccdc9;}
	.yoona01{width:100%;}
	
	.panel-primary>.panel-heading {background-image: linear-gradient(to bottom,#ffeeee 0,#f5c3b9 100%);
									    border-color: #e2b0b0;}
	.panel-primary {border-color: #e2b0b0;}
	
	a {color: #ce5f5d;
    text-decoration: none;}
    
    .btn-info:focus, .btn-info:hover { background-color: #fcddd0;
    									background-position: 0 -15px;}
    
    body {
    font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
    font-size: 14px;
    line-height: 1.42857143;
    color: #ddb6ae;
    background-color: #fff;}
    
    .btn-info.active, .btn-info:active {background-color: #ec9e8c;
    									border-color: #f4beb7;}	
    									
    .btn-info.active, .btn-info:active:focus{color: #fff;
    background-color: #e99683;
    border-color: #f4beb7;}																	
    									
    									
	
	</style>
   	
   	<!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script type="text/javascript">
		
		//============= ȸ�������� ȭ���̵� =============
		$( function() {
			//==> �߰��Ⱥκ� : "addUser"  Event ����
			$("a[href='#' ]:contains('ȸ������')").on("click" , function() {
				self.location = "/user/addUser"
			});
		});
		
		//============= �α��� ȭ���̵� =============
		$( function() {
			//==> �߰��Ⱥκ� : "addUser"  Event ����
			$("a[href='#' ]:contains('�� �� ��')").on("click" , function() {
				self.location = "/user/login"
			});
		});
		
	</script>	
	
</head>

<body>
<!-- ToolBar Start /////////////////////////////////////-->
		<ul class="nav nav-tabs">
		  <li class="nav-item">
		    <a class="nav-link active" aria-current="page" href="#">Model2 MVC Shop</a>
		  </li>
		  <li class="nav-item pull-right">
		    <a class="nav-link" href="#">ȸ������</a>
		  </li>
		  <li class="nav-item pull-right">
		    <a class="nav-link" href="#">�� �� ��</a>
		  </li>
		  
		</ul>


	<!-- ToolBar End /////////////////////////////////////-->


   
   	
	<!--  ȭ�鱸�� div Start /////////////////////////////////////-->
	<div class="container" style="margin-top:40px;">
		
		
		<!-- �ٴܷ��̾ƿ�  Start /////////////////////////////////////-->
		<div class="row">
	
			<!--  Menu ���� Start /////////////////////////////////////-->     	
			<div class="col-md-3" style= "margin-top: 72px;">
		        
		       	<!--  ȸ������ ��Ͽ� ���� -->
				<div class="panel panel-primary">
					<div class="panel-heading">
						<i class="glyphicon glyphicon-heart"></i> ȸ������
         			</div>
         			<!--  ȸ������ ������ -->
					<ul class="list-group">
						 <li class="list-group-item">
						 	<a href="#">����������ȸ</a> <i class="glyphicon glyphicon-gift"></i>
						 </li>
						 <li class="list-group-item">
						 	<a href="#">ȸ��������ȸ</a> <i class="glyphicon glyphicon-gift"></i>
						 </li>
					</ul>
		        </div>
               
               
				<div class="panel panel-primary">
					<div class="panel-heading">
							<i class="glyphicon glyphicon-gift"></i> �ǸŻ�ǰ����
         			</div>
					<ul class="list-group">
						 <li class="list-group-item">
						 	<a href="#">�ǸŻ�ǰ���</a> <i class="glyphicon glyphicon-gift"></i>
						 </li>
						 <li class="list-group-item">
						 	<a href="#">�ǸŻ�ǰ����</a> <i class="glyphicon glyphicon-gift"></i>
						 </li>
					</ul>
		        </div>
               
               
				<div class="panel panel-primary">
					<div class="panel-heading">
							<i class="glyphicon glyphicon-shopping-cart"></i> ��ǰ����
	    			</div>
					<ul class="list-group">
						 <li class="list-group-item">
						 <a href="#">��ǰ�˻� </a><i class="glyphicon glyphicon-gift"></i></li>
						  <li class="list-group-item">
						  	<a href="#">�����̷���ȸ</a> <i class="glyphicon glyphicon-gift"></i>
						  </li>
						 <li class="list-group-item">
						 	<a href="#">�ֱٺ���ǰ</a> <i class="glyphicon glyphicon-gift"></i>
						 </li>
					</ul>
				</div>
				
			</div>
			<!--  Menu ���� end /////////////////////////////////////-->   

	 	 	<!--  Main start /////////////////////////////////////-->   		
	 	 	<div class="col-md-7">
	 	 		<h1>Model2 MVC Shop</h1>
	 	 		<img src="../images/YoonA01.jpg" class="yoona01" alt="First slide">
				
			  		
			  		
			  		<div class="text-right">
			  			<a class="btn btn-info btn-lg" href="#" role="button" >ȸ������</a>
			  			<a class="btn btn-info btn-lg" href="#" role="button">�� �� ��</a>
			  		</div>
			  	
			  	
	        </div>
	   	 	<!--  Main end /////////////////////////////////////-->   		
	 	 	
		</div>
		<!-- �ٴܷ��̾ƿ�  end /////////////////////////////////////-->
		
	</div>
	<!--  ȭ�鱸�� div end /////////////////////////////////////-->

</body>

</html>