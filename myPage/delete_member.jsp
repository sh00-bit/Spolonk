<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- 절대경로 -->
<c:set var="root" value="${pageContext.request.contextPath}/" />

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <meta name="keywords" content="Join us">
    <meta name="description" content="">
    <title>退会</title>

    <!-- CSS 링크 -->
    <link rel="stylesheet" href="${root}css/nicepage.css" media="screen">
    <link rel="stylesheet" href="${root}css/myPage/Mypage.css" media="screen">

    <!-- Bootstrap CDN -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

    <!-- 추가 스타일 -->
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #edf6ff;
           
           
        }
        .container {
            width: 50%;
            margin: 150px auto;
            background-color: #fff;
            padding: 50px;
            border-radius: 50px;
            box-shadow: 0 0 10px #61b0ff;
        }
        .header {
            font-size: 3rem;
            font-weight: bold;
            margin-bottom: 20px;
            text-align: center;
            color: #1877c9
        }
        .sub-header {
            font-size: 24px;
            color: #777;
            margin-bottom: 30px;
            text-align: center;
        }
        .form-group label {
            font-weight: bold;
            font-size: 20px;
        }
        .form-group input {
            width: 100%;
            padding: 10px;
            margin-top: 10px;
            border: 1px solid #ddd;
            border-radius: 50px;
        }
        .form-buttons {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }
        .form-buttons button {
            padding: 10px 20px;
            border-radius: 20px;
            font-weight: bold;
            cursor: pointer;
        }
        .btn-danger {
            background-color: #ff4d4d;
            color: white;
            border: none;
        }
        .btn-danger:hover {
            background-color: #e60000;
        }
        .btn-secondary {
            background-color: #777;
            color: white;
            border: none;
            border-radius: 20px;
        }
        .btn-secondary:hover {
            background-color: #555;
        }
    </style>
</head>
<body>

    <!-- 상단메뉴 -->
    <c:import url="/WEB-INF/views/include/top_menu.jsp"></c:import>

    <!-- 왼쪽 사이드바 -->
    <c:import url="/WEB-INF/views/include/side_menu_mypage.jsp"></c:import>

    <!-- 회원수정 컨테이너 -->
    <div class="container" style="height: 650px;">
        <div class="header">退会</div>
        <div class="sub-header">本当に退会しますか？この操作は取り消すことができません</div>

        <form:form action="${root}myPage/delete_member_pro" method="post" modelAttribute="loginUserBean">
            <form:hidden path="user_idx" value="${loginUserBean.user_idx}" />

            <!-- 사용자 확인을 위한 비밀번호 입력 -->
            <div class="form-group">
                <form:label path="user_pass1">パスワード確認</form:label>
                <form:input type="password" path="user_pass1" class="form-control" placeholder="パスワードを入力してください。" required="required" />
            </div>

            <!-- 탈퇴 및 취소 버튼 -->
            <div class="form-buttons">
                <button type="submit" class="btn btn-danger">退会</button>
                <a href="${root}myPage/mypage" class="btn btn-secondary">キャンセル</a>
            </div>
        </form:form>
    </div>


</body>

    <!-- 하단정보 -->
    <c:import url="/WEB-INF/views/include/bottom_info.jsp"></c:import>
</html>