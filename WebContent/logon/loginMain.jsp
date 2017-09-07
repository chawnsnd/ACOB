<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN MAIN</title>
</head>
<body>
	<table>
		<tr>
			<td width="150" valign="top">
				<!-- 메인 베너 -->
			</td>
			<td>
				<!-- 경로는 마음대로 이름도 마음대로  탑 -->
				<jsp:include page="../mainTop.jsp" flush="false"/>
			</td>
		</tr>
		<tr>
			<td width="150" valign="top">
				<!-- 왼쪽 -->
				<jsp:include page="../mainLeft.jsp" flush="false"/>
			</td>
			<td width="700" valign="top">
				<jsp:include page="메인"flush="false"/>
			</td>
		</tr>
		<tr>
			<td width="150" valign="top">
				<img src="../logo.png" border="0" width="90" height="60">
			</td>
			<td width="700" valign="top">
				<jsp:include page="../mainbottom.jsp" flush="false"/>
			</td>
		</tr>
	</table>
</body>
</html>