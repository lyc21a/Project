<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.Date,java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head> <meta charset="EUC-KR"> </head>
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
	#submit_button{
	  border: none;
	  color: white;
	  padding: 15px 32px;
	  text-align: center;
	  text-decoration: none;
	  display: inline-block;
	  font-size: 16px;
	  cursor: pointer;
	  margin: 5px;
	  background-color: white;
  		color: black;
  		border: 2px solid #4CAF50;
	}
	#submit_button:hover {
	  background-color: #4CAF50; /* Green */
 	 	color: white;	
	}
	h4{
		margin: 15px;
	}
	.logout{
		position: absolute;
		width: 100px;
	    top: 130px;
	    right: 15%;
	    padding: 6px 10px 6px 11px;
	    background-color: #ccd0d4;
	    -webkit-border-radius: 14px;
	    border-radius: 14px;
	    font-size: 13px;
	    font-color : black;
	    color: #fff;
	    text-decoration: none;
    }
</style>
<body>
	<%
		String ID = request.getParameter("ID");
	    String pass = request.getParameter("PWD");
	%>
	<br/><br/>
	<div class = "header">
		<h1><a href ="/FINAL/menu?ID=<%=ID%>">도서관리시스템</a></h1>
	</div>
	<div id="zz">
		<div class ="logout"><%=ID %>님 <a href="/FINAL" >로그아웃</a></div>
	</div>
	<%= new SimpleDateFormat("yyyy년 MM월 dd일").format(new Date()) %>
	
	
	<form Action ="revise" Method = "post">
		<input type = "Hidden" name = "ID" Value =<%=ID %>>
		<Input Type = "Submit" Value = "정보수정"  id="submit_button">
		
	</form>
	
	<form Action ="bookregist" Method = "post">
		<input type = "Hidden" name = "ID" Value =<%=ID %>>
		<Input Type = "Submit" Value = "도서등록" id="submit_button" >
	</form>
	<form Action ="bookborrow" Method = "post">
		<input type = "Hidden" name = "ID" Value =<%=ID %>>
		<Input Type = "Submit" Value = "도서대여" id="submit_button">
	</form>
	
	<form Action ="bookback" Method = "post">
		<input type = "Hidden" name = "ID" Value =<%=ID %>>
		<Input Type = "Submit" Value = "도서반납" id="submit_button">
	</form>
	
	<form Action ="bookreserve" Method = "post">
		<input type = "Hidden" name = "ID" Value =<%=ID %>>
		<Input Type = "Submit" Value = "예약하기" id="submit_button">
	</form>
	
	<form Action ="delete" Method = "post">
		<input type = "Hidden" name = "ID" Value =<%=ID %>>
		<Input Type = "Submit" Value = "회원탈퇴" id="submit_button">
	</form>
	
</body>
</html>