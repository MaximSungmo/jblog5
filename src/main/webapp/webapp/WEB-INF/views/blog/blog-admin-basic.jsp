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
<script
	src="${pageContext.servletContext.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>


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
					<c:param name="menu" value="basic" />
				</c:import>
				<form:form 
				modelAttribute="blogVo"
				class="blog-form"
				method="post"
				action="${pageContext.request.contextPath}/${authUser.id}/admin/basic"
				enctype="multipart/form-data">
					<table class="admin-config">
						<tr>
							<td class="t">블로그 제목</td>
							<td><input type="text" size="40" name="title" value="${blogvo.title }"></td>
						</tr>
						<tr>
							<td class="t" id="imagePriview">로고이미지</td>
							<td><img id="logo-image" name="logo-name" src="${pageContext.servletContext.contextPath }/assets/image/${blogvo.logo}"></td>
						</tr>
						<tr>
							<td class="t">&nbsp;</td>
							<td><input type="file" id= "imgInp" name="logo_file"></td>
						</tr>
						<tr>
							<td class="t">&nbsp;</td>
							<td class="s"><input type="submit" value="기본설정 변경"></td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp" />

	</div>

	<!-- 이미지 업로드 미리보기 -->
	<script type="text/javascript">
		function readURL(input) {
	        if (input.files && input.files[0]) {
	            var reader = new FileReader();
	            
	            reader.onload = function (e) {
	                $('#logo-image').attr('src', e.target.result);
	            }
	        
	            reader.readAsDataURL(input.files[0]);
	        }
	    }
	    $("#imgInp").change(function(){
	        readURL(this);
	    });
	</script>

</body>

</html>