<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
	<form method="post" action="loginPro.jsp">
		<!-- style="ime-mode:inactive ==>타자 입력시 영어로  active ==> 한글로-->
		아이디 : <input type="text" name="id" maxlength="50" style="ime-mode:inactive;"><br>
		비밀번호 : <input type="password" name="passwd" maxlength="16" style="ime-mode:active"><br>
		<input type="submit" value="로그인">
		
		<input type="button" value="회원 가입" onclick="location.href='MemberRegistForm.jsp'">
	</form>
</body>
</html>