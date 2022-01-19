<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.Date,java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head> 

<meta charset="EUC-KR">
<title>도서관리시스템</title>
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
	.container{
		margin: 0;
	    padding: 0;
   		-webkit-text-size-adjust: none;
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
	#loginbutton{
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
	<br/><br/>
	<div class = "header">
		<h1><a href ="/FINAL">도서관리시스템</a></h1>
	</div>
	<br/>
	<div class = "container">
		<form Action ="logincheck" Method = "post">
			<Input Type = "Text" Name = "ID" autofocus required placeholder="아이디" id = "inp"> <BR/>
			<Input Type = "PassWord" Name = "PWD" required placeholder="비밀번호" id = "inp"><BR/>
			<font color = red>${errorID}</font><BR/>
			<Input Type = "Submit" Value = "로그인"  id= "loginbutton">
		</form>
		<a href = "/FINAL/signup">회원가입</a>
		
	</div>
	
</body>
</html>