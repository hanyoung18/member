<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="com.crud.dao.MemberDAO, com.crud.bean.MemberVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Form</title>
</head>
<body>

<%
	MemberDAO boardDAO = new MemberDAO();
	String id=request.getParameter("sid");	
	MemberVO u=boardDAO.getMember(Integer.parseInt(id));
%>

<h1>Members</h1>
<form action="edit_ok.jsp" method="post">
<input type="hidden" name="seq" value="<%=u.getSid() %>"/>
<table>
<tr><td>User ID:</td><td><input type="text" name="userid" value="<%= u.getUserid()%>"/></td></tr>
<tr><td>User name:</td><td><input type="text" name="username" value="<%= u.getUsername()%>" /></td></tr>
<tr><td>Email address:</td><td><input type="text" name="email" value="<%= u.getEmail()%>" /></td></tr>
<tr><td>Introduce myself</td><td><textarea cols="50" rows="5" name="introduce"><%= u.getDetail()%></textarea></td></tr>

<tr><td colspan="2"><input type="submit" value="Edit List"/>
<input type="button" value="Cancel" onclick="history.back()"/></td></tr>
</table>
</form>

</body>
</html>