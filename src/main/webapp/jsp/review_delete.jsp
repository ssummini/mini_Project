<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="dao.MemberDAO" %>
<%@ page import="dto.MemberDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	// review_seq 파라미터 받기
	String reviewSeqParam = request.getParameter("reviewSeq");
	int review_seq = 0;
	
    review_seq = Integer.parseInt(reviewSeqParam);
	
	//DB
	MemberDAO dao = MemberDAO.getInstance();
	int result = dao.Delete_Review(review_seq);
%>

<%= result %>