<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="dao.MemberDAO" %>
<%@ page import="dto.ReviewDTO" %>
<%@ page import="org.json.JSONObject" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    // review_seq 파라미터 받기
    String reviewSeqParam = request.getParameter("reviewSeq");
    int review_seq = 0;


    review_seq = Integer.parseInt(reviewSeqParam);


    // DB에서 리뷰 정보 가져오기
    MemberDAO dao = MemberDAO.getInstance();
    ReviewDTO dto = null;


    dto = dao.review(review_seq);


    // JSON 객체 생성 및 데이터 설정
    JSONObject jsonObject = new JSONObject();


	jsonObject.put("review_seq", dto.getReview_seq());
	jsonObject.put("user_id", dto.getUser_id());
	jsonObject.put("title", dto.getTitle());
	jsonObject.put("content", dto.getContent());
	jsonObject.put("logtime", dto.getLogtime());


    // JSON 데이터를 문자열로 변환하여 출력
    String json = jsonObject.toString();
%>

<%= json %>
