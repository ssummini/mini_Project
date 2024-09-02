<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="dao.MemberDAO" %>
<%@ page import="dto.MemberDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	//JAVA 구역
	String user_name = request.getParameter("user_name");
	String user_id = request.getParameter("user_id");
	String user_email = request.getParameter("user_email");
	String user_pwd = request.getParameter("user_pwd");
	
	System.out.println("User Name: " + user_name);
	System.out.println("User ID: " + user_id);
	System.out.println("User Email: " + user_email);
	System.out.println("User Password: " + user_pwd);
	
	//DB
	MemberDTO dto = new MemberDTO();
	dto.setUser_name(user_name);
    dto.setUser_id(user_id);
    dto.setUser_email(user_email);
    dto.setUser_pwd(user_pwd);
      
	MemberDAO dao = MemberDAO.getInstance();
	int result = dao.Join(dto);
	System.out.println("result");
%>

<%= result %>