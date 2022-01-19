<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>도서예약처리</title>
</head>
<body>
	<% 
		String ID = request.getParameter("ID");
        String bookname = request.getParameter("BOOKNAME");
	%>
	<script>
		alert("<%=ID%>님이 <%=bookname%> 도서를 예약하셨습니다.");
		window.location = "/FINAL/menu?ID=<%=ID%>";
	</script>
</body>
</html>