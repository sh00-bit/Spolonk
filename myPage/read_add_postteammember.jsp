<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- 절대경로 -->
<c:set var="root" value="${pageContext.request.contextPath }/" />

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <meta name="keywords" content="​Join us">
    <meta name="description" content="">
	 <title>チームメイト求人掲示板</title>  
	   
    <!-- 탑메뉴 css -->
    <link rel="stylesheet" href="${root }css/nicepage.css" media="screen">
	<link rel="stylesheet" href="${root }css/join.css" media="screen">
	
	<!-- Bootstrap CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	 <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
        }
        
        .container {
            max-width: 800px;
            margin: 30px auto;
            padding: 20px;
            background-color: white;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }
        
        .post-header {
            text-align: center;
            border-bottom: 2px solid #f1f1f1;
            padding-bottom: 20px;
            margin-bottom: 30px;
        }
        
        .post-header h1 {
            color: #4794D1;
            margin: 0;
        }
        
        .post-header .team-info {
            font-size: 18px;
            color: #6c757d;
        }
        
        .post-body {
            line-height: 1.6;
            color: #333;
        }
        
        .post-footer {
            margin-top: 30px;
            text-align: right;
        }
        
        .post-footer a {
            text-decoration: none;
            color: white;
            background-color: #4794D1;
            padding: 10px 20px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        
        .post-footer a:hover {
            background-color: #3578a5;
        }
        
        .badge {
            display: inline-block;
            padding: 5px 10px;
            font-size: 14px;
            color: white;
            background-color: #4794D1;
            border-radius: 12px;
        }
        
        .info-section {
            margin-bottom: 20px;
        }
        
        .info-section span {
            font-weight: bold;
            color: #4794D1;
        }
        
        hr {
            border: 0;
            height: 1px;
            background-color: #f1f1f1;
        }
    </style>
</head>
<body>


 <!-- 상단메뉴 -->
<c:import url="/WEB-INF/views/include/top_menu.jsp"></c:import>
	
	
		<div class="container">
    <!-- 게시글 제목 -->
    <div class="post-header">
        <h2>${postList.title}</h2>
        <p class="team-info">
            ${postList.teamName} &bull; ${postList.eventName}
        </p>
        <%-- <span class="badge">${postList.category}</span> --%>
        <span class="badge" style="background-color: ${
                            postList.category == 'プロ' ? 'red' : postList.category == '素人' ? 'blue' : 'yellow'}">${postList.category}</span>
    </div>
    
    <!-- 게시글 본문 -->
    <div class="post-body">
        <p>
            ${postList.content} <!-- 게시글의 실제 내용이 이곳에 들어갑니다. -->
        </p>
    </div>

    <hr>

    <!-- 추가 정보 -->
    <div class="info-section">
        <%-- <p><span>작성자 ID:</span> ${loginUserBean.getUserName}</p> --%>
        <p><span>チーム名:</span> ${postList.teamName}</p>
        <p><span>種目:</span> ${postList.eventName}</p>
        <p><span>実力:</span> ${postList.category}</p>
    </div>
    
    <!-- 버튼 또는 하단 작업 -->
    <div class="post-footer">
    	<!-- 포스트쓴 팀 id랑 유저idx 끌고 가입시더 -->
  		<a href="${root}myPage/applyTeamMember?team_id=${postList.team_id}&user_idx=${loginUserBean.user_idx}" >申請</a>
        <a href="${root}myPage/TeamOrTeamMemberAdd?user_idx=${loginUserBean.user_idx}">リストへ戻る</a>
    </div>
</div>

	<!-- 하단정보 -->
	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />


</body>
</html>