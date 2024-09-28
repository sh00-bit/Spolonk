<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- 절대경로 -->
<c:set var="root" value="${pageContext.request.contextPath}/" />

<html style="font-size: 16px;" lang="ko-KR">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <title>書いた投稿</title>
    
    <!-- 스타일 설정 -->
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f7fc;
        }
        
        .container {
            width: 60%;
            margin: 100px auto;
            background-color: #fff;
            padding: 40px;
            border-radius: 50px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }
        
        .header {
            font-size: 2.5rem;
            font-weight: bold;
            margin-bottom: 20px;
            text-align: center;
            color: #2c3e50;
        }
        
        h2 {
            font-size: 1.5rem;
            font-weight: bold;
            margin-bottom: 20px;
            text-align: center;
            color: #2980b9;
        }
        
        table {
            width: 100%;
            border-radius: 50px;
            border-collapse: collapse;
            margin-bottom: 40px;
        }
        
        th, td {
            padding: 15px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }
        
        th {
            background-color: #2980b9;
            color: white;
            text-transform: uppercase;
        }
        
        td {
            color: #333;
        }
        
        tr:hover {
            background-color: #f2f6fb;
        }
        
        .btn {
            display: inline-block;
            padding: 10px 20px;
            text-align: center;
            background-color: #2980b9;
            color: white;
            border-radius: 25px;
            text-decoration: none;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }
        
        .btn:hover {
            background-color: #1f6398;
        }

        .btn-back {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #2980b9;
            color: white;
            text-align: center;
            border-radius: 25px;
            text-decoration: none;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        .btn-back:hover {
            background-color: #1f6398;
        }

        /* 카드 스타일 */
        .card {
            background-color: #ffffff;
            border-radius: 20px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
            padding: 30px;
            transition: box-shadow 0.3s ease;
        }

        .card:hover {
            box-shadow: 0 6px 18px rgba(0, 0, 0, 0.15); 
        }

        .card-header {
            font-size: 18px;
            font-weight: bold;
            color: #333;
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-bottom: 1px solid #f0f0f0;
            padding-bottom: 12px;
            margin-bottom: 16px;
        }

        .card-body {
            font-size: 14px;
            color: #555;
        }

        .card-body p {
            margin: 0;
            padding: 5px 0;
        }

        .card-body p i {
            margin-right: 8px;
            color: #2980b9;
        }

        .card-footer {
            margin-top: 20px;
            text-align: right;
        }

        /* 삭제 버튼 */
        .delete-button {
            background-color: #e74c3c;
            color: white;
            border: none;
            padding: 10px 20px;
            font-size: 14px;
            border-radius: 20px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .delete-button:hover {
            background-color: #c0392b;
        }
    </style>
    

</head>
<body>

    <!-- 상단메뉴 -->
    <c:import url="/WEB-INF/views/include/top_menu.jsp"></c:import>

    <!-- 왼쪽 사이드바 -->
    <c:import url="/WEB-INF/views/include/side_menu_mypage.jsp"></c:import>

    <div class="container">
        <div class="header">書いた投稿</div>
        

        <table>
            <thead>
                <tr>
                    <th>ＮＯ．</th>
                    <th>タイトル</th>
                    <th>登録日</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="content" items="${myPostList}">
                    <tr>
                        <td>${content.post_id}</td>
                        <td>${content.title}</td>
                        <td>${content.writing_time}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- 마이페이지로 돌아가기 버튼 -->
        <a href="${root}myPage/mypage" class="btn-back">戻る</a>

    </div>

    <!-- 하단정보 -->
    <c:import url="/WEB-INF/views/include/bottom_info.jsp" />

</body>
</html>