<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원가입</title>
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
	#button{
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
	h4{
		margin: 15px;
	}
</style>
<body>
	<br/><br/>
	<div class = "header">
		<h1><a href ="/FINAL">도서관리시스템</a></h1>
	</div>
	<div class = "container">
		<form Action ="signup_process" Method = "POST">
			<h4>아이디</h4>
			<Input Type = "Text" Name = "ID" autofocus required id="inp">
			<h4>비밀번호</h4>
			<Input Type = "PassWord" Name = "PWD" id="inp" required>
			<h4>이름</h4>
			<Input Type = "Text" Name = "NAME" id="inp" required >
			<h4>주소</h4>
			<Input Type = "text" Name = "ADDR" id="inp" required>
			<h4>전화번호</h4>
			<input type="number" Name = "PHONE" id="inp" required>
			<br/>
			<font color = red>${message}</font><br/>
			<Input Type = "Submit" Value = "가입하기" id="button">
		</form>
	</div>
</body>
</html>