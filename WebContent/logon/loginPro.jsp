<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--이거는 html소스 볼때 빈칸 없애주는거 --%>
<%@ page trimDirectiveWhitespaces="true" %>
<%--회원 데이터 빈 --%>
<%@page import="member.LogonDBBean" %>
<% request.setCharacterEncoding("utf-8");%>

<%	
	//이거 간단하게 바꾸는거 있다고 했는데...
	//무슨 파람 써서
	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");
	
	LogonDBBean member = LogonDBBean.getInstance();
	int check = member.userCheck(id, passwd);
	
	if(check==1){
		session.setAttribute("memberId", id);
		response.sendRedirect("../loginMain.jsp");
	}else if(check==0){%>
	<script>
		alert("비밀번호가 맞지 않습니다.");
		history.go(-1);
	</script>
	<%}else{%>
	<script>
		alert("아이디가 맞지 않습니다.");
		history.go(-1);
	</script>
	<%}%>