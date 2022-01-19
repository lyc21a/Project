<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<% 
		String ID = request.getParameter("ID");
	%>
	<script>	
		alert("<%=ID%>님이 회원가입하셨습니다.");
		window.location = "/FINAL";
	</script>
</body>
</html>	