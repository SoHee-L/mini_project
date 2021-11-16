<%@ page contentType="text/html; charset=euc-kr" %>
<%@ page pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--  /////// EL / JSTL �������� �ּ� ó�� ///////

<%@ page import="java.util.List"  %>

<%@ page import="com.model2.mvc.service.domain.Product" %>
<%@ page import="com.model2.mvc.common.Search" %>
<%@page import="com.model2.mvc.common.Page"%>
<%@page import="com.model2.mvc.common.util.CommonUtil"%>


<%
	List<Product> list= (List<Product>)request.getAttribute("list");
	Page resultPage=(Page)request.getAttribute("resultPage");
	
	Search search = (Search)request.getAttribute("search");
	String menu = request.getParameter("menu");
	
	System.out.println(search + "~~// serach");
	//==> null �� ""(nullString)���� ����
	String searchCondition = CommonUtil.null2str(search.getSearchCondition());
	String searchKeyword = CommonUtil.null2str(search.getSearchKeyword());
%> --%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
<%-- <% if(menu.equals("manage")) { %>
	<title>��ǰ ����</title>
<% } else { %>
	<title>��ǰ �����ȸ</title>
<% } %> --%>

<c:choose>  
	<c:when test="${param.menu=='manage'}">
		<title>��ǰ ����</title>
	</c:when> 
	<c:otherwise> 
		<title>��ǰ �����ȸ</title>
	</c:otherwise> 	
</c:choose>  


<link rel="stylesheet" href="/css/admin.css" type="text/css">

<!-- CDN(Content Delivery Network) ȣ��Ʈ ��� -->
	<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script type="text/javascript">
	
		//=====����Code �ּ� ó�� ��  jQuery ���� ======//
		// �˻� / page �ΰ��� ��� ��� Form ������ ���� JavaScrpt �̿�  
		function fncGetUserList(currentPage) {
			//document.getElementById("currentPage").value = currentPage;
			$("#currentPage").val(currentPage)
		   	//document.detailForm.submit();
			$("form").attr("method" , "POST").attr("action" , "/product/listProduct").submit();
		}
		//===========================================//
		//==> �߰��Ⱥκ� : "�˻�" ,  userId link  Event ���� �� ó��
		 $(function() {
			 
			//==> �˻� Event ����ó���κ�
			//==> DOM Object GET 3���� ��� ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			//==> 1 �� 3 ��� ���� : $("tagName.className:filter�Լ�") �����. 
			 $( "td.ct_btn01:contains('�˻�')" ).on("click" , function() {
				//Debug..
				//alert(  $( "td.ct_btn01:contains('�˻�')" ).html() );
			
				fncGetUserList(1);
			});
			
			
			//==> userId LINK Event ����ó��
			//==> DOM Object GET 3���� ��� ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			//==> 3 �� 1 ��� ���� : $(".className tagName:filter�Լ�") �����.
			$( ".ct_list_pop td:nth-child(3)" ).on("click" , function() {
					//Debug..
					alert(  $( this ).attr("data-prodNo") );
					
					var prodNo = $(this).attr("data-prodNo");
					$.ajax( 
							{
								url : "/product/json/getProduct/"+prodNo ,
								method : "GET" ,
								dataType : "json" ,
								headers : {
									"Accept" : "application/json",
									"Content-Type" : "application/json"
								},
								success : function(JSONData , status) {
									
									alert(  "hi") ;

									//Debug...
									//alert(status);
									//Debug...
									//alert("JSONData : \n"+JSONData);
									
									var displayValue = "<h3>"
																+"��ǰ�� : "+JSONData.prodName+"<br/>"
																+"��ǰ������  : "+JSONData.prodDetail+"<br/>"
																+"��������  : "+JSONData.manuDate+"<br/>"
																+"����  : "+JSONData.price+"<br/>"
																+"��ǰ�̹��� : "+JSONData.fileName+"<br/>"
																+"</h3>";
									//Debug...									
									//alert(displayValue);
									$("h3").remove();
									$( "#"+prodNo+"" ).html(displayValue);
								}
						});
					
					//self.location ="/user/getUser?userId="+$(this).text().trim();
			});
			
			//==> UI ���� �߰��κ�  :  userId LINK Event End User ���� ���ϼ� �ֵ��� 
			$( ".ct_list_pop td:nth-child(3)" ).css("color" , "red");
			$("h7").css("color" , "red");
			
			
			//==> �Ʒ��� ���� ������ ������ ??
			//==> �Ʒ��� �ּ��� �ϳ��� Ǯ�� ���� �����ϼ���.					
			$(".ct_list_pop:nth-child(4n+6)" ).css("background-color" , "whitesmoke");
			//console.log ( $(".ct_list_pop:nth-child(1)" ).html() );
			//console.log ( $(".ct_list_pop:nth-child(2)" ).html() );
			//console.log ( $(".ct_list_pop:nth-child(3)" ).html() );
			//console.log ( $(".ct_list_pop:nth-child(4)" ).html() ); //==> ok
			//console.log ( $(".ct_list_pop:nth-child(5)" ).html() ); 
			//console.log ( $(".ct_list_pop:nth-child(6)" ).html() ); //==> ok
			//console.log ( $(".ct_list_pop:nth-child(7)" ).html() ); 
			
			///////////////////////////////////////////////////
			
			
			
		});	
	</script>		

</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/listProduct.do?menu=${param.menu}" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
				<%-- <% if(menu.equals("manage")) { %>
				<td width="93%" class="ct_ttl01">��ǰ ����</td>
				<% } else { %>
				<td width="93%" class="ct_ttl01">��ǰ �����ȸ</td>
				<% } %> --%>
				
					<%System.out.println("menu request.getPrameter"+request.getParameter("menu")); %>
				<c:choose>  
					<c:when test="${param.menu=='manage'}">
					<td width="93%" class="ct_ttl01">��ǰ ����</td>
					</c:when> 
					<c:otherwise> 
					<td width="93%" class="ct_ttl01">��ǰ �����ȸ</td>
					</c:otherwise> 
				</c:choose>  
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		
		<td align="right">
			<select class="form-control" name="searchCondition" >
                  <option value="0" ${!empty search.searchCondition && searchCondition==0 ? "selected" : "" }>��ǰ��ȣ</option>
                  <option value="1" ${!empty search.searchCondition && searchCondition==1 ? "selected" : "" }>��ǰ��</option>
                  <option value="2" ${!empty search.searchCondition && searchCondition==2 ? "selected" : "" }>��ǰ����</option>
               </select>
			<input type="text" name="searchKeyword"  
			value="${! empty search.searchKeyword ? search.searchKeyword : ""}"  
			class="ct_input_g" style="width:200px; height:19px" />
		</td>
	
		
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<!-- <a href="javascript:fncGetProductList();">�˻�</a> -->
						�˻�
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<%-- <td colspan="11" >
			��ü  <%= resultPage.getTotalCount() %> �Ǽ�, ���� <%= resultPage.getCurrentPage() %> ������	
		</td> --%>
		<td colspan="11" >
			��ü  ${resultPage.totalCount } �Ǽ�, ���� ${resultPage.currentPage}  ������
		</td>
		
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">��ǰ��</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">����</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">�����</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">�������</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<%-- <%	
		int no=list.size();
		for(int i=0; i<list.size(); i++) {
			Product vo = (Product)list.get(i);
	%> --%>
		
	
		<%-- <td align="center">${no}</td> --%>
	
		
		<c:set var="i" value="0" />
		<c:forEach var="product" items="${list}">
		<c:set var="i" value="${ i+1 }" />
		<tr class="ct_list_pop">
			<td align="center">${ i }</td>
		
		<td></td>
		<td align="left" data-prodNo="${product.prodNo }">
		${product.prodName}
			<%-- <a href="/product/getProduct?prodNo=${product.prodNo}&menu=${menu}">${product.prodName}</a> --%></td>
		<td></td>
		<td align="left">${product.price}</td>
		<td></td>
		<td align="left">${product.manuDate}
		<td></td>
		<td align="left">
		
			��� ����
		
		</td>	
	</tr>
	<tr>
		<td id= "${product.prodNo }" colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>	
	<%-- <% } %> --%>
	</c:forEach>
</table>
<!-- PageNavigation Start... -->
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
		   <input type="hidden" id="currentPage" name="currentPage" value=""/>
			<%-- <% if( resultPage.getCurrentPage() <= resultPage.getPageUnit() ){ %>
					�� ����
			<% }else{ %>
					<a href="javascript:fncGetProductList('<%=resultPage.getCurrentPage()-1%>')">�� ����</a>
			<% } %>

			<%	for(int i=resultPage.getBeginUnitPage();i<= resultPage.getEndUnitPage() ;i++){	%>
					<a href="javascript:fncGetProductList('<%=i %>');"><%=i %></a>
			<% 	}  %>
	
			<% if( resultPage.getEndUnitPage() >= resultPage.getMaxPage() ){ %>
					���� ��
			<% }else{ %>
					<a href="javascript:fncGetProductList('<%=resultPage.getEndUnitPage()+1%>')">���� ��</a>
			<% } %> --%>
			<jsp:include page="../common/pageNavigator.jsp"/>
    	</td>
	</tr>
</table>
<!--  ������ Navigator �� -->

</form>

</div>
</body>
</html>