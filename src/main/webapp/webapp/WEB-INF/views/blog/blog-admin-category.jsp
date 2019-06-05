<!-- JSTL 사용을 위한 태그 라이브러리 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- 스프링에서 제공하는 태그 라이브러리 -->
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- 폼을 만들어주는 태그 라이브러리 -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />

		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url='/WEB-INF/views/includes/adminMenubar.jsp'>
					<c:param name="menu" value="category" />
				</c:import>
				
				<table class="admin-cat">
					<tr>
						<th>번호</th>
						<th>카테고리명</th>
						<th>포스트 수</th>
						<th>설명</th>
						<th>삭제</th>
					</tr>
					
					<c:set var='count' value='${fn:length(categoryList) }' />
					<c:forEach items='${categoryList }' var='categoryvo' varStatus='status'>
						<tr>
							<td>${status.index +1}</td>
							<td>${categoryvo.name }</td>
							<td>${categoryvo.countPost }</td>
							<td>${categoryvo.description }</td>
							<td>
							<!-- 기본카테고리는 삭제가 불가능하도록 작업 -->
							<c:choose>
							<c:when test = "${status.index+1 == 1}">
								
							</c:when>	
							<c:otherwise>
								<a href= "${pageContext.request.contextPath}/${authUser.id}/admin/category/delete/${categoryvo.no}">								
								<img src="${pageContext.request.contextPath}/assets/images/delete.jpg"></a>
							</c:otherwise>
							</c:choose>
							</td>
							
						</tr>
					</c:forEach>
				</table>

				<h4 class="n-c">새로운 카테고리 추가</h4>
				<form:form 
					modelAttribute="categoryVo" 
					class="category-form" 
					id="category-form"
					method="post" 
					action="${pageContext.request.contextPath}/${authUser.id }/admin/category">
					<input type="hidden" name="id" value="${authUser.id }">
					<table id="admin-cat-add">
						<tr>
							<td class="t">카테고리명</td>
							<td><input type="text" name="name"></td>
						</tr>
						<tr>
							<td class="t">설명</td>
							<td><input type="text" name="description"></td>
						</tr>
						<tr>
							<td class="s">&nbsp;</td>
							<td><input type="submit" value="카테고리 추가"></td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp" />

	</div>
</body>
</html>