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
					<c:param name="menu" value="write" />
				</c:import>

				<form:form modelAttribute="postVo" action="${pageContext.request.contextPath}/${authUser.id }/admin/write" method="post">
					<table class="admin-cat-write">
						<tr>
							<td class="title">제목</td>
							<td><input type="text" size="60" name="title"> 
								<select name="category_no">
									<c:forEach items='${categoryList }' var='categoryvo' varStatus='status'>
										<option  value="${categoryvo.no }">${categoryvo.name }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<form:errors path="title"/>
						<tr>
							<td class="t">내용</td>
							<td><textarea name="content"></textarea></td>
						</tr>
						<form:errors path="content"/>
						<tr>
							<td>&nbsp;</td>
							<td class="s"><input type="submit" value="포스트하기"></td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp" />

	</div>
</body>
</html>