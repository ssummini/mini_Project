<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="dao.MemberDAO" %>
<%@ page import="dto.ReviewDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	//JAVA 구역
	String user_id = request.getParameter("user_id");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	//DB
	ReviewDTO dto = new ReviewDTO();
	dto.setUser_id(user_id);
	dto.setTitle(title);
	dto.setContent(content);
	
	MemberDAO dao = MemberDAO.getInstance();
	int result = dao.reviewWrite(user_id, title, content);
%>

<%= result %>