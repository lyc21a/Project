<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head> <meta charset="EUC-KR"> </head>
<body>
	<h1> Book List </h1> 
	<ul>
		<c:forEach var="book" items="${books}" varStatus="status">
			<li> ${status.index+1} : ${book.title}, ${book.author}, ${book.stock} </li>
		</c:forEach>
	</ul>
</body>
</html>