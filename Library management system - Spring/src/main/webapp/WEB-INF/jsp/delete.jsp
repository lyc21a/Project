<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.Date,java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head> <meta charset="EUC-KR"> </head>
<body>
	<% 
		String ID = request.getParameter("ID");
	%>
	<script>	
		alert("<%=ID%>님이 탈퇴하셨습니다.");
		window.location = "/FINAL";
	</script>
</body>
</html>