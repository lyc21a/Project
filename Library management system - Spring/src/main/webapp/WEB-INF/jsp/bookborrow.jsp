<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>도서 대여</title>
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
</style>
<body>
	<%
		String ID = request.getParameter("ID");
	%>
	<br/><br/>
	<div class = "header">
		<h1><a href ="/FINAL/menu?ID=<%=ID%>">도서관리시스템</a></h1>
	</div><h3>※도서대여※</h3>
	<div class = "container">
		<form Action ="bookborrow_ok" Method = post">
			<input type = "Hidden" name = "ID" Value =<%=ID %>>
			<h4>도서명</h4>
			<Input Type = "Text" Name = "TITLE" autofocus required id="inp"> <BR/>
			<h4>저자</h4>
			<Input Type = "Text" Name = "AUTHOR" required id="inp"> <BR/>
			<font color = red>${message}</font><br/>
			<Input Type = "Submit" Value = "대여하기" id= "savebutton">
		</form>
	</div>
</body>
</html>