<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var='root' value='${pageContext.request.contextPath}/'/>
<script>
	alert('会員退会に失敗しました。')
	location.href = '${root}myPage/delete_member?user_idx=${user_idx}'
</script>