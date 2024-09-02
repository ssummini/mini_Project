<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="dao.MemberDAO" %>
<%@ page import="dto.MemberDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	//JAVA 구역
	String user_id = request.getParameter("user_id");
	String user_pwd = request.getParameter("new_pwd");
	
	System.out.println("User ID: " + user_id);
	System.out.println("User Password: " + user_pwd);
	//DB
	MemberDAO dao = new MemberDAO();
	int result = dao.updateArticle(user_id, user_pwd);
%>

<%=result %>