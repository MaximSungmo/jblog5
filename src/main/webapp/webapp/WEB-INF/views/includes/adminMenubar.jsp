<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:choose>
		<c:when test='${param.menu }== "basic"'>
			<ul class="admin-menu">
				<li class="selected">기본설정</li>
				<li><a href="${pageContext.request.contextPath}/${authUser.id }/admin/category">카테고리</a></li>
				<li><a href="${pageContext.request.contextPath}/${authUser.id }/admin/write">글작성</a></li>
			</ul>
		</c:when>
		<c:when test='${param.menu }== "category"'>
			<ul class="admin-menu">
				<li><a href="${pageContext.request.contextPath}/${authUser.id }/admin/basic">기본설정</a></li>
				<li class="selected">카테고리</li>
				<li><a href="${pageContext.request.contextPath}/${authUser.id }/admin/write">글작성</a></li>
			</ul>
		</c:when>
		<c:when test='${param.menu }== "write"'>
			<ul class="admin-menu">
				<li><a href="${pageContext.request.contextPath}/${authUser.id }/admin/basic">기본설정</a></li>
				<li><a href="${pageContext.request.contextPath}/${authUser.id }/admin/category">카테고리</a></li>
				<li class="selected">글작성</li>
			</ul>
		</c:when>
		<c:otherwise>
			<ul class="admin-menu">	
				<li><a href="${pageContext.request.contextPath}/${authUser.id }/admin/basic">기본설정</a></li>
				<li><a href="${pageContext.request.contextPath}/${authUser.id }/admin/category">카테고리</a></li>
				<li><a href="${pageContext.request.contextPath}/${authUser.id }/admin/write">글작성</a></li>
			</ul>
		</c:otherwise>
	</c:choose>

	
</body>
</html>