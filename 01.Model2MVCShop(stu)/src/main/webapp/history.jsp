-<%@ page contentType="text/html; charset=EUC-KR" %>

<html>
<head>

<title>��� ��ǰ ����</title>

</head>
<body>
	����� ��� ��ǰ�� �˰� �ִ�
<br>
<br>
<%
	request.setCharacterEncoding("euc-kr");
	response.setCharacterEncoding("euc-kr");
	String history = null;
	Cookie[] cookies = request.getCookies();
	if (cookies!=null && cookies.length > 0) { // ��Ű�� �����ϸ�
		for (int i = 0; i < cookies.length; i++) { //��Ű�� ���̸�ŭ for���� �����ڴ�. = ��Ű�� ���� �о���ڴ�.
			Cookie cookie = cookies[i]; // �ش��ϴ� ��Ű �ϳ��ϳ��� �����ϰڴ�.
			if (cookie.getName().equals("history")) { // ���� ��Ű�� �̸��� �����Դµ� ��Ű�� �̸��� history��� ��Ű�� key���� history�� ����
				history = cookie.getValue(); // �����丮��� ��Ű�� Ű���� ������ ��. ���������� �����̵ȴٸ�
			}
		}
		if (history != null) { //���࿡ �����̵ƴٸ� ������ �������ض�.
			String[] h = history.split(","); // history�� value ���� ��. ,�� ���������� �迭�� �����ϰڴ�.
			for (int i = 0; i < h.length; i++) {
				if (!h[i].equals("null")) { //�������� ��ǰ��ȣ prodNo��� ���� �˼�����.
%>
<a href="/getProduct.do?prodNo=<%=h[i]%>&menu=search"	target="rightFrame"><%=h[i]%></a>
<br>
<%
				}
			}
		}
	}
%>

</body>
</html>