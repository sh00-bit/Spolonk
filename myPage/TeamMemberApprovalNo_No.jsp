<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 절대경로 -->
<c:set var="root" value="${pageContext.request.contextPath }/" />

<script>
	alert("拒否しました。")
	location.href = "${root}myPage/TeamMemberApprovalService?user_idx=${loginUserBean.user_idx}	"
</script>