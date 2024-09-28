<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- 절대경로 -->
<c:set var="root" value="${pageContext.request.contextPath }/" />

<!DOCTYPE html>
<html style="font-size: 16px;" lang="ko">
<head>
    <meta charset="UTF-8">
    <title>チームメイトの求人投稿</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- CSS 파일 포함 -->
    <link rel="stylesheet" href="${root}css/nicepage.css" media="screen">
    <link rel="stylesheet" href="${root}css/index.css" media="screen"> 
    
    <!-- Custom CSS -->
    <style>
        body {
            background-color: #f0f4f8;
            font-family: 'Arial', sans-serif;
            color: #333;
        }

        .container {
            margin: 100px auto 100px; /* Top margin 100px, Bottom margin 100px */
            padding: 30px;
            max-width: 800px;
            background-color: #ffffff;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
            border-radius: 12px;
            border-left: 5px solid #4794D1; /* 이미지에서 추출한 파란색 코드 */
            box-sizing: border-box; /* Ensure padding and border are included in the element's width and height */
        }

        h2 {
            color: #4794D1; /* 이미지에서 추출한 파란색 코드 */
            text-align: center;
            margin-bottom: 30px;
            font-size: 28px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 8px;
            color: #4794D1; /* 이미지에서 추출한 파란색 코드 */
            font-size: 16px;
        }

        .form-group input[type="text"],
        .form-group textarea,
        .form-group select {  /* select 요소에도 동일한 스타일 적용 */
            width: 100%;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 8px;
            font-size: 16px;
            box-sizing: border-box;
            background-color: #f9f9f9;
            transition: border-color 0.3s;
        }

        .form-group input[type="text"]:focus,
        .form-group textarea:focus,
        .form-group select:focus {  /* select 요소에도 포커스 스타일 적용 */
            border-color: #4794D1; /* 이미지에서 추출한 파란색 코드 */
            outline: none;
        }

        .form-group textarea {
            height: 150px;
            resize: vertical;
        }

        .btn-submit {
            display: inline-block;
            padding: 14px 28px;
            background-color: #4794D1; /* 이미지에서 추출한 파란색 코드 */
            color: white;
            text-decoration: none;
            border-radius: 8px;
            transition: background-color 0.3s ease;
            cursor: pointer;
            font-size: 18px;
            text-align: center;
            border: none;
        }

        .btn-submit:hover {
            background-color: #3a78a7; /* 이미지에서 추출한 파란색 코드의 다크톤 */
        }

        .btn-cancel {
            display: inline-block;
            padding: 14px 28px;
            background-color: #ccc;
            color: white;
            text-decoration: none;
            border-radius: 8px;
            transition: background-color 0.3s ease;
            cursor: pointer;
            font-size: 18px;
            text-align: center;
            margin-left: 15px;
            border: none;
        }

        .btn-cancel:hover {
            background-color: #999;
        }

        .btn-group {
            text-align: center;
            margin-top: 30px;
        }
    </style>
</head>

<body>

    <!-- 상단메뉴 -->
    <c:import url="/WEB-INF/views/include/top_menu.jsp"></c:import>

    <!-- 글쓰기 컨테이너 -->
<div class="container">
    <h2>チームメイトの求人投稿</h2>
    <form:form action="${root}myPage/add_teammember_pro" modelAttribute="writeTeamPostBean" method="post">

			<!-- 숨겨진 필드 추가 -->
			<form:hidden path="eventName" id="eventName" value="" />
			<form:hidden path="category" id="category" value="" />

			<!-- 추가 BOARD_INFO_IDX값도 writeContentBean에 담음 -->
        <form:hidden path="user_idx" value="${user_idx}"/>
 
        <!-- 제목 입력 필드 -->
        <div class="form-group">
            <form:label path="title" for="title">タイトル</form:label>
            <form:input path="title" type="text" id="title" required="required" />
        </div>
        
        <!-- 내용 입력 필드 -->
        <div class="form-group">
            <form:label path="content" for="content">内容</form:label>
            <form:textarea path="content" id="content" required="required"></form:textarea>
        </div>
        
        <!-- 팀 선택 필드 -->
        <div class="form-group">
            <form:label path="team_id" class="form-label">チーム選択</form:label>
            <form:select path="team_id" id="teamSelect" cssClass="form-control">
                <form:option value="0">チームを選択してください。</form:option>
                <c:forEach var="team" items="${teamList3}">
                    <form:option value="${team.team_id}" data-event-name="${team.eventName}" 
                    data-category="${team.category}">${team.teamName}</form:option>
                </c:forEach>
            </form:select>
        </div>

        <!-- 종목 및 등급 표시 -->
			<div class="form-group">
				<label>チーム種目</label>
				<div id="displayEventName" class="form-control" style="background-color: #f9f9f9; padding: 10px;">選択したチームの種目が表示されます。</div>
			</div>

			<div class="form-group">
				<label>チームの実力</label>
				<div id="displayCategory" class="form-control" style="background-color: #f9f9f9; padding: 10px;">選択したチームの実力が表示されます。</div>
			</div>

			<!-- 작성자 입력 필드 -->
        <div class="form-group">
            <form:label path="user_idx" for="writer">作成者</form:label>
            <form:input path="user_idx" type="text" id="writer" required="required" readonly="true" value="${user_name}" />
        </div> 
        
        <!-- 버튼 그룹 -->
        <div class="btn-group">
            <button type="submit" class="btn-submit">投稿する</button>
            <a href="${root}myPage/TeamOrTeamMemberAdd?user_idx=${user_idx}" class="btn-cancel">キャンセル</a>
        </div>
    </form:form>
</div>

<!-- 하단정보 -->	
<c:import url="/WEB-INF/views/include/bottom_info.jsp" />

<script>
    document.addEventListener('DOMContentLoaded', function() {
        const teamSelect = document.getElementById('teamSelect');
        const displayEventName = document.getElementById('displayEventName');
        const displayCategory = document.getElementById('displayCategory');
        
        teamSelect.addEventListener('change', function() {
            const selectedOption = teamSelect.options[teamSelect.selectedIndex];
            console.log("o"+selectedOption.value)
            const eventName = selectedOption.getAttribute('data-event-name');
            console.log("a"+eventName)
            const category = selectedOption.getAttribute('data-category');
            console.log("b"+category)
            
            if(eventName) {	
                displayEventName.textContent = eventName;
                console.log(eventName)
                document.getElementById("eventName").value = eventName;

            } else {
                displayEventName.textContent = '선택된 팀의 종목이 표시됩니다.';
            }

            if(category) {
                displayCategory.textContent = category;
                document.getElementById("category").value = category;
            } else {
                displayCategory.textContent = '선택된 팀의 등급이 표시됩니다.';
            }
        });
    });
</script>
  
</body>
</html>
