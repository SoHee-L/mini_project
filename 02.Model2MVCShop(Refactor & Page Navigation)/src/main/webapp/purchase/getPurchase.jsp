<%@ page contentType="text/html; charset=EUC-KR" %>

<%@ page import="com.model2.mvc.service.domain.Product" %>
<%@ page import="com.model2.mvc.service.domain.Purchase" %>
<%@ page import="com.model2.mvc.service.domain.User" %>


<%
	User user = (User)session.getAttribute("user");
%>

<%
	Product vo = (Product)session.getAttribute("product");
%>	

<%
	Purchase purchase = (Purchase)session.getAttribute("purchase");
	System.out.println(purchase);
%>	


<html>
<head>
<title>���Ż���ȸ</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

</head>

<body bgcolor="#ffffff" text="#000000">

<table width="100%" height="37" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif"	width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">���Ż���ȸ</td>
					<td width="20%" align="right">&nbsp;</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif"	width="12" height="37"/>
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 13px;">
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">
			��ǰ��ȣ <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="105">
					<%=purchase.getTranNo() %></td>
					<td></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">
			�����ھ��̵� <img	src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01"><%=user.getUserId() %></td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>

	<tr>
		<td width="104" class="ct_write">���Ź��</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<%=purchase.getPaymentOption() %>
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">�������̸�</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01"><%=purchase.getReceiverName() %></td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">�����ڿ���ó</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01"><%=purchase.getReceiverPhone() %></td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">�������ּ�</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01"><%=purchase.getDivyAddr() %></td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">���ſ�û����</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01"><%=purchase.getDivyRequest() %></td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">��������</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01"><%=purchase.getDivyDate() %></td>
	</tr>

	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>

	<tr>
		<td width="104" class="ct_write">�ֹ���</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01"><%=purchase.getOrderDate() %></td>
	</tr>

	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td width="53%"></td>
		<td align="right">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01"	style="padding-top: 3px;">
						<a href="/updatePurchaseView.do?tranNo=10032">����</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
					</td>
					<td width="30"></td>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01"	style="padding-top: 3px;">
						<a href="javascript:history.go(-1);">Ȯ��</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif"width="14" height="23"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

</body>
</html>