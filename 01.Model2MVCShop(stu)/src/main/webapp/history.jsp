-<%@ page contentType="text/html; charset=EUC-KR" %>

<html>
<head>

<title>열어본 상품 보기</title>

</head>
<body>
	당신이 열어본 상품을 알고 있다
<br>
<br>
<%
	request.setCharacterEncoding("euc-kr");
	response.setCharacterEncoding("euc-kr");
	String history = null;
	Cookie[] cookies = request.getCookies();
	if (cookies!=null && cookies.length > 0) { // 쿠키가 존재하면
		for (int i = 0; i < cookies.length; i++) { //쿠키가 길이만큼 for문을 돌리겠다. = 쿠키를 전부 읽어오겠다.
			Cookie cookie = cookies[i]; // 해당하는 쿠키 하나하나를 저장하겠다.
			if (cookie.getName().equals("history")) { // 내가 쿠키의 이름을 가져왔는데 쿠키의 이름이 history라면 쿠키의 key값을 history로 저장
				history = cookie.getValue(); // 히스토리라는 쿠키의 키값을 가진게 들어감. 정상적으로 실행이된다면
			}
		}
		if (history != null) { //만약에 실행이됐다면 밑으로 실행을해라.
			String[] h = history.split(","); // history에 value 값이 들어감. ,로 나눈값들을 배열에 저장하겠다.
			for (int i = 0; i < h.length; i++) {
				if (!h[i].equals("null")) { //나눈값이 상품번호 prodNo라는 것을 알수있음.
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