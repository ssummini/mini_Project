<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="dao.MemberDAO" %>
<%@ page import="dto.ReviewDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="org.json.JSONArray" %>
<%@ page import="org.json.JSONObject" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	//DB
	MemberDAO dao = MemberDAO.getInstance();
	List<ReviewDTO> list = dao.reviewList();
	
	// JSON 배열 생성
    JSONArray jsonArray = new JSONArray();
	
    for (ReviewDTO review : list) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("review_seq", review.getReview_seq());
        jsonObject.put("user_id", review.getUser_id());
        jsonObject.put("title", review.getTitle());
        jsonObject.put("content", review.getContent());
        jsonObject.put("logtime", review.getLogtime());

        // JSON 배열에 추가
        jsonArray.put(jsonObject);
    }
	
    // JSON 배열을 문자열로 변환
    String json = jsonArray.toString();
		
%>

<%= json %>