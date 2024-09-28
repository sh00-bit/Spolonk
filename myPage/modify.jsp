<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- 절대경로 -->
<c:set var="root" value="${pageContext.request.contextPath}/" />

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8">
    <meta name="keywords" content="Join us">
    <meta name="description" content="">
    <title>会員情報修正</title>

    <!-- CSS 링크 -->
    <link rel="stylesheet" href="${root}css/nicepage.css" media="screen">
    <link rel="stylesheet" href="${root}css/join.css" media="screen">

    <!-- Bootstrap CDN -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

    <!-- 스타일 설정 -->
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #edf6ff;
        }
        .container {
            width: 60%;
            margin: 120px auto;
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
            color: #1877c9;
        }
        .form-group label {
            font-weight: bold;
            margin-bottom: 5px;
            display: block;
            
        }
        .form-group input, .form-group select {
            width: 100%;
            height: 50px;
            padding: auto;
            margin: auto;
            border-radius: 50px;
            border: 1px solid #ddd;
            box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1);
            box-sizing: border-box; /* 패딩과 테두리를 높이 계산에 포함 */
        }
        select {
       line-height: 50px; /* 높이를 늘리기 위해 line-height 설정 */
       height: 50px; /* 혹은 명시적으로 높이 설정 */
   }
        .form-group {
            margin-bottom: 20px;
        }
        .btn-primary {
            background-color: #1367c2;
            color: white;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 20px;
            cursor: pointer;
        }
        .btn-primary:hover {
            background-color: #104e8b;
        }
        .btn-secondary {
            background-color: #777;
            color: white;
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 20px;
            margin-left: 10px;
            cursor: pointer;
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
<div class="container">
    <h2 class="header" style="color: #1877c9;">会員情報修正</h2>
    <form:form modelAttribute="modifyUserBean" action="${root}myPage/modify_pro" method="post" onsubmit="return validateForm()">
        <form:hidden path="user_idx" />

        <!-- 아이디 -->
        <div class="form-group">
            <form:label path="user_id">ID</form:label>
            <form:input path="user_id" id="user_id" class="form-control" disabled="disabled" />
        </div>

        <!-- 이름 -->
        <div class="form-group">
            <form:label path="user_name">お名前</form:label>
            <form:input path="user_name" id="user_name" class="form-control" disabled="disabled" />
        </div>

        <!-- 성별 -->
        <div class="form-group">
            <form:label path="gender">性別</form:label>
            <form:select path="gender" class="form-control">
                <form:option value="" label="選択してください。" />
                <form:option value="male" label="男性" />
                <form:option value="female" label="女性" />
            </form:select>
        </div>

        <!-- 운동 종목 -->
        <div class="form-group">
            <form:label path="event_id">運動種目</form:label>
            <form:select path="event_id" class="form-control">
                <form:option value="" label="選択してください。" />
                <form:option value="1000" label="サッカー" />
                <form:option value="1001" label="フットサル" />
                <form:option value="1002" label="野球" />
                <form:option value="1003" label="バレーボール" />
            </form:select>
        </div>

        <!-- 비밀번호 -->
        <div class="form-group">
            <form:label path="user_pass1">パスワード</form:label>
            <form:input type="password" path="user_pass1" class="form-control" />
             <form:errors path="user_pass1" cssClass="text-danger" />
        </div>

        <!-- 비밀번호 확인 -->
        <div class="form-group">
            <form:label path="user_pass2">パスワード確認</form:label>
            <form:input type="password" path="user_pass2" class="form-control" />
            <form:errors path="user_pass2" style="color:red;" />
        </div>

        <!-- 휴대폰 번호 -->
        <div class="form-group">
            <form:label path="user_Phone">電話番号</form:label>
            <form:input path="user_Phone" class="form-control" />
            <form:errors path="user_Phone" cssClass="text-danger" />
            
        </div>

        <!-- 이메일 -->
        <div class="form-group">
            <form:label path="user_email">メールアドレス</form:label>
            <form:input path="user_email" class="form-control" />
            <form:errors path="user_email" cssClass="text-danger" />
        </div>

        <!-- 정보 수정 버튼 -->
        <div class="form-group">
            <button type="submit" class="btn btn-primary">情報修正</button>
            <a href="${root}myPage/mypage" class="btn btn-secondary">キャンセル</a>
        </div>
    </form:form>
</div>

<!-- 하단정보 -->
<c:import url="/WEB-INF/views/include/bottom_info.jsp"></c:import>

<script>
    document.getElementById("user_name").disabled = true;
    document.getElementById("user_id").disabled = true;
    
    function validateForm() {
        var pass1 = document.getElementById("user_pass1").value;
        var pass2 = document.getElementById("user_pass2").value;
        var phone = document.getElementById("user_Phone").value;
        var email = document.getElementById("user_email").value;

        // 비밀번호 유효성 검사
        if (pass1 === "" || pass2 === "") {
            alert("パスワードを入力してください。");
            return false;
        }
        if (pass1.length < 6) {
            alert("パスワードは最低6文字以上である必要があります。");
            return false;
        }
        if (pass1 !== pass2) {
            alert("パスワードが一致しません。");
            return false;
        }

        // 휴대폰 번호 유효성 검사
        var phonePattern = /^\d{3}-\d{3,4}-\d{4}$/;
        if (!phonePattern.test(콜)) {
            alert("携帯番号は010-1234-5678の形式である必要があります。");
            return false;
        }

        // 이메일 유효성 검사
        var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
        if (!emailPattern.test(email)) {
            alert("有効なメールアドレスを入力してください。");
            return false;
        }

        return true; // 유효성 검사를 통과한 경우 폼 제출
    }
</script>

</body>
</html>