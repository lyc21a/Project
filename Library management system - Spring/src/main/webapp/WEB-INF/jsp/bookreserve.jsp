<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>도서 예약</title>
</head>
<style>
	body{
		background : #f8f8fa;
		text-align : center;
	}
	h1 {
	    display: block;
	    font-size: 2em;
	    font-weight: bold;
	}
	.header{
	    width: auto;
	    margin: 0 15px;
	}
	a{
		text-decoration : none;
	    cursor: pointer;
	}
	input#inp{
    	position: relative;
    	width: 300px;
    	height: 29px;
    	margin-bottom: 8px;
   		padding: 7px 35px 10px 11px;
   	 	border: solid 1px #dadada;
   	 	background: #fff;
	}
	#savebutton{
	    width: 330px;
	    height: 56px;
	    line-height: 55px;
	    margin: 12px 0 14px;
	    border-radius: 0;
	    border: solid 1px rgba(0,0,0,.1);
	    background-color: #03c75a;
	    color: #fff;
	    font-size: 16px;
	    font-weight: 700;
	    cursor: pointer;
	    text-align: center;
	}
	select {
	    padding: .8em .5em;

	    -webkit-appearance: none;
	    -moz-appearance: none;
	    appearance: none;
	    
	    position: relative;
    	width: 350px;
    	height: 49px;
	    margin-bottom: 8px;
	    padding: 7px 35px 10px 11px;
	    border: solid 1px #dadada;
	    background: #fff;
	    
	}	


</style>
<body>
	<%
		String ID = request.getParameter("ID");
	%>
	<br/><br/>
	<div class = "header">
		<h1><a href ="/FINAL/menu?ID=<%=ID%>">도서관리시스템</a></h1>
	</div><h3>※도서예약※</h3>
	
	
	<form action = "bookreserve_process" >
		<input type = "Hidden" name = "ID" Value =<%=ID %>>
		<h4>예약가능한 도서</h4>
		<select name="BOOKNAME">
			<c:forEach var="book" items="${books}" varStatus="status">
				<option value=${book.title}>${book.title}
			</c:forEach>
		</select><br>
	
		<input type = "submit" id = "savebutton" value = "예약하기"/> 
	</form>
</body>
</html>