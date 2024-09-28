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
    <title>チーム追加</title>
    
    <!-- 탑메뉴 css -->
    <link rel="stylesheet" href="${root }css/nicepage.css" media="screen">
   <link rel="stylesheet" href="${root }css/join.css" media="screen">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
   
   <!-- Bootstrap CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

<style>
         body {
            background-color: #ffffff;
            color: #000000;
        }
        .nav-tabs .nav-link {
            color: #000000;
            border-color: #cccccc;
        }
        .nav-tabs .nav-link.active {
            background-color: #007bff;
            color: #ffffff;
        }
        .content-container {
            padding: 20px;
            background-color: #f8f9fa;
            border-radius: 5px;
            margin-top: 20px;
            margin-bottom: 100px;
            border: 1px solid #dddddd;
        }
</style>   
</head>
<body>
<%-- 
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <!-- 상단메뉴 -->
    <c:import url="/WEB-INF/views/include/top_menu.jsp"></c:import>

    <div class="container mt-5">
        <h2 class="mb-4">팀 / 팀원 등록</h2>
        <p class="mb-4">회원의 팀을 등록해주세요. 팀과 팀원 등록은 관리자의 승인이 필요합니다.</p>
      <div class="content-container">
      <!-- 탭 네비게이션 -->
            <ul class="nav nav-tabs mt-4" id="myTab" role="tablist">
                <li class="nav-item" role="presentation">
                    <button class="nav-link active" id="education-tab" data-bs-toggle="tab" data-bs-target="#education" type="button" role="tab" aria-controls="education" aria-selected="true">팀 등록</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="awards-tab" data-bs-toggle="tab" data-bs-target="#awards" type="button" role="tab" aria-controls="awards" aria-selected="false">팀원 추가</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="training-tab" data-bs-toggle="tab" data-bs-target="#training" type="button" role="tab" aria-controls="training" aria-selected="false">팀 리스트</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="honors-tab" data-bs-toggle="tab" data-bs-target="#honors" type="button" role="tab" aria-controls="honors" aria-selected="false">여기에 뭘 넣을까요</button>
                </li>
            </ul>           
       --%>    
    <div class="container mt-5">
        <h2 class="mb-4">チーム登録</h2>
        
        <!-- 팀 등록 폼 -->
        <form:form method="post" action="${root}myPage/addTeam_pro" modelAttribute="team" onsubmit="return submitForm();">
            <!-- user_idx값 폼에서 전달하기 -->
            <form:hidden path="user_idx" value="${user_idx}"/>
            <div class="form-group">
                <form:label path="teamName" class="form-label">チーム名</form:label>
                <form:input path="teamName" cssClass="form-control" placeholder="チーム名を入力してください。" required="true"/>
            </div>
            <div class="form-group">
             <form:label path="eventName" class="form-label">種目</form:label>
             <form:select path="eventName" cssClass="form-control">
                 <form:option value="">種目を選択してください。</form:option>
                 <form:option value="サッカー">サッカー</form:option>
                 <form:option value="フットサル">フットサル</form:option>
                 <form:option value="野球">野球</form:option>
                 <form:option value="バレーボール">バレーボール</form:option>
             </form:select>
         </div>
         <div class="form-group">
             <form:label path="category" class="form-label">実力</form:label>
             <form:select path="category" cssClass="form-control">
                 <form:option value="">チームの実力を選択してください。</form:option>
                 <form:option value="プロ">プロ</form:option>
                 <form:option value="素人">素人</form:option>
                 <form:option value="一般">一般</form:option>
             </form:select>
         </div>
            <button type="submit" class="btn btn-primary">チーム登録</button>
        </form:form>
    </div>
    
    <script>
    function submitForm() {
       // 폼이 정상적으로 제출되도록 함
        setTimeout(function() {
            // 부모 창(원래 창)을 새로고침
            if (window.opener) {
                window.opener.location.reload();
            }
            // 팝업창 닫기
            window.close();
        }, 10); // 딜레이
        
        return true; // 폼 제출 계속 진행
    }
</script>

    <!-- 하단정보 --><%-- 
    <c:import url="/WEB-INF/views/include/bottom_info.jsp" />
    --%>
</body>

</html>