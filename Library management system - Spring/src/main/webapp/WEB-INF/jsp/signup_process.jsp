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
		alert("<%=ID%>���� ȸ�������ϼ̽��ϴ�.");
		window.location = "/FINAL";
	</script>
</body>
</html>	