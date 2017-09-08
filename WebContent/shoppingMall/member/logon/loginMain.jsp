<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
	String id="";
	try{
		id = (String)session.getAttribute("id");
		
		if(id==null|id.equals(""))
			response.sendRedirect("loginForm.jsp");
		else{
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN MAIN</title>
</head>
<body>
	<b><%=id %>님 환영합니다.</b>
	<form method="post" action="logout.jsp">
	<input type="submit" value="로그아웃">
	</form>
</body>
</html>
<%		}
	}catch(Exception e){
		e.printStackTrace();
	}
%>