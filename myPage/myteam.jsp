<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath }/" />

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <meta name="keywords" content="​Join us">
    <meta name="description" content="">
    <title>내 팀 정보</title>
    
    <!-- 탑메뉴 css -->
    <link rel="stylesheet" href="${root }css/nicepage.css" media="screen">
	<link rel="stylesheet" href="${root }css/join.css" media="screen">
	
	<!-- Bootstrap CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	
</head>
<body>
<style>
    .container {
        margin-bottom: 400px;
        margin-top : 200px;
    }
</style>

 <!-- 상단메뉴 -->
<c:import url="/WEB-INF/views/include/top_menu.jsp"></c:import>

<!-- 왼쪽 사이드바 -->
   <c:import url="/WEB-INF/views/include/side_menu_mypage.jsp"></c:import>
	
<!-- 메인 바디 -->
<!-- 	
   구분 필터 셀렉트박스
    <div class="mb-3 text-end">
        <label for="filter-select" class="form-label">구분 선택:</label>
        <select id="filter-select" class="form-select d-inline-block w-auto" aria-label="구분 필터">
            <option value="all">모두 보기</option>
            <option value="프로">프로</option>
            <option value="아마추어">아마추어</option>
            <option value="일반">일반</option>
        </select>
    </div>
 -->
<div class="container mt-5">
    <h2 class="mb-4">팀 리스트</h2>
    <div class="d-flex justify-content-between mb-4">
        <!-- 필터링 셀렉트박스 -->
        <select id="filter-select" class="form-select d-inline-block w-auto" aria-label="구분 필터">
            <option value="all">모두 보기</option>
            <c:forEach var="team" items="${teamArrayList}">
                <option value="${team}">${team}</option>
            </c:forEach>
        </select>

        <!-- 팀원 관리 버튼을 필터링 셀렉트박스 우측에 배치 -->
        <a href="/team/manage" class="btn btn-primary d-inline-block">
            팀원 관리
        </a>
    </div>

    <table class="table table-striped mb-5">
        <thead>
            <tr>
                <th scope="col">번 호</th>
                <th scope="col">팀 이름</th>
                <th scope="col">멤버 이름</th>
                <th scope="col">멤버 아이디</th>
                <th scope="col">휴대폰 번호</th>
                <th scope="col">이메일</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="team" items="${teamList}" varStatus="stat">
                <tr>
                    <th scope="row">${stat.index + 1}</th>
                    <td class="teamName">${team.teamName}</td>
                    <td>${team.user_name}</td>
                    <td>${team.user_id}</td>
                    <td>${team.user_Phone}</td>
                    <td>${team.user_email}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<script>
    // 필터링 기능 구현
    document.getElementById('filter-select').addEventListener('change', function() {
        var selectedTeam = this.value;
        var rows = document.querySelectorAll('table tbody tr');
        
        rows.forEach(function(row) {
            var rowTeamName = row.querySelector('.teamName').innerText;
            /* var rowTeamName = row.querySelector('td:nth-child(2)').innerText; */
            if (selectedTeam === 'all' || rowTeamName === selectedTeam) {
                row.style.display = ''; 
            } else {
                row.style.display = 'none';  
            }
        });
    });
</script>

	
	<!-- 하단정보 -->
	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />


</body>
</html>