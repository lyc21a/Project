<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원정보 수정</title>
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
		String name = request.getParameter("NAME");
		String addr = request.getParameter("ADDR");
		String ph = request.getParameter("PH");
	%> 
	<br/>
	<br/>
	<div class = "header">
		<h1>
			회원정보수정
		</h1>
		<br>
	</div>
	<div class = "container">
		<form Action ="revise_process" Method = "post">
			<input type = "hidden"  value =<%=ID %> name = "ID">
			<Input Type = "PassWord" Name = "oldPWD" required id="inp" placeholder ="현재 비밀번호"><BR/>
			<Input Type = "PassWord" Name = "newPWD" required id="inp" placeholder = "변경할 비밀번호"><BR/>
			<h4>이름</h4>
			<Input Type = "text" Name = "NAME" value= ${name} required id="inp" required><BR/>
			<h4>주소</h4>
			<Input Type = "text" Name = "ADDR" id="inp" value= ${addr} required> <BR/>
			<h4>전화번호</h4>
			<Input Type = "number" Name = "PHONE" id="inp" value= ${ph} required><BR/>
			<font color = red>${STR}</font><br>
			<Input Type = "Submit" Value = "저장"  id= "savebutton">
		</form>
	</div>
</body>
</html>