<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>��������ó��</title>
</head>
<body>
	<% 
		String ID = request.getParameter("ID");
        String bookname = request.getParameter("BOOKNAME");
	%>
	<script>
		alert("<%=ID%>���� <%=bookname%> ������ �����ϼ̽��ϴ�.");
		window.location = "/FINAL/menu?ID=<%=ID%>";
	</script>
</body>
</html>