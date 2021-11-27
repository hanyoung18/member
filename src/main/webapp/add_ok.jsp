<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.crud.dao.MemberDAO"%>

<% request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="u" class="com.crud.bean.MemberVO" />
<jsp:setProperty property="*" name="u"/>

<%
	MemberDAO boardDAO = new MemberDAO();
	int i = boardDAO.insertMember(u);
	String msg = "데이터 추가 성공 !";
	if(i == 0) msg = "[에러] 데이터 추가 ";
%>

<script>
	alert('<%=msg%>');
	location.href='list.jsp';
</script>