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
        String TITLE = request.getParameter("TITLE");
	%>
	<script>
		alert("<%=TITLE%> 도서를 반납하였습니다.");
		window.location = "/FINAL/menu?ID=<%=ID%>";
		
	</script>
</body>
</html>