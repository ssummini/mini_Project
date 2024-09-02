<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="dao.MemberDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
	//JAVA 구역
	String user_id = request.getParameter("user_id");

	//DB
	MemberDAO dao = MemberDAO.getInstance();
	int result = dao.IdCheck(user_id);
%>

<%= result %>