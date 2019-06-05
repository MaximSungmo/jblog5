<!-- JSTL 사용을 위한 태그 라이브러리 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- 스프링에서 제공하는 태그 라이브러리 -->
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- 폼을 만들어주는 태그 라이브러리 -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
			<div id="content">
				<div class="blog-content">
					<h4>${postvo.title }</h4>
					<p>
					${postvo.content }
					<c:if test="${postNo eq null }">
					<h1>게시글이 없습니다.</h1>
					</c:if>
					</p>
				</div>
				<ul class="blog-list">
					<c:forEach items='${postList}' var='postvo' varStatus='status'>
					<li><a href="${pageContext.request.contextPath}/${id}/${categoryNo}/${postvo.no}">${postvo.title }</a> <span>${postvo.reg_date }</span></li>
					</c:forEach>
				</ul>
				<!-- pager 추가 -->
				<div class="pager">
					<fmt:parseNumber value="${((pagevo.pageNo-1)/5) }" var="pageView" integerOnly="true" />
					<div>
						<p>페이지 목록</p>
					</div>
					<ul>
						<c:if test="${pagevo.pageNo==1}">
							<li>◀</li>
						</c:if>
						<c:if test="${pagevo.pageNo!=1 }">
							<li><a
								href="${pageContext.request.contextPath}/${id}/${categoryNo}/${postvo.no}?page=${pagevo.pageNo-1}">◀</a></li>
						</c:if>
						<c:forEach var="i" begin="1" end="5" step="1">
							<!-- 현재 페이지 셀렉티드 표시 -->
							<c:choose>
								<c:when test="${(pageView*5)+i > pagevo.totalPageNo}">
										<li>${(pageView*5)+i}</li>
								</c:when>
								<c:when test="${pagevo.pageNo eq ((pageView*5)+i)}">
									<li class="selected"><a
										href="${pageContext.request.contextPath}/${id}/${categoryNo}/${postvo.no}?page=${(pageView*5)+i}">${(pageView*5)+i}</a></li>
								</c:when>
								<c:when test="${pagevo.pageNo ne ((pageView*5)+i)}">
									<li><a
										href="${pageContext.request.contextPath}/${id}/${categoryNo}/${postvo.no}?page=${(pageView*5)+i}">${(pageView*5)+i}</a></li>
								</c:when>	
							</c:choose>
							
						</c:forEach>
						<c:if test="${pagevo.totalPageNo <= pagevo.pageNo}">
							<li>▶</li>
						</c:if>
						<c:if test="${pagevo.totalPageNo > pagevo.pageNo}">
							<li><a
								href="${pageContext.request.contextPath}/${id}/${categoryNo}/${postvo.no}?page=${pageVo.pageNo+1}">▶</a></li>
						</c:if>
					
					</ul>
				</div>
				<!-- pager 추가 -->
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img
					src="${pageContext.servletContext.contextPath }/assets/image/${blogvo.logo}">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>

			<c:set var='count' value='${fn:length(categoryList) }' />
				<ul>
			<c:forEach items='${categoryList }' var='categoryvo' varStatus='status'>
					<li><a href="${pageContext.request.contextPath}/${id }/${categoryvo.no}?page=1">${categoryvo.name } </a></li>
			</c:forEach>
				</ul>
		</div>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp" />

	</div>
</body>
</html>