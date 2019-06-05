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
<!-- 제이쿼리 라이브러리 호출  -->

<script
	src="<%=request.getContextPath()%>/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
	$(function() {

		$("#blog-id").keydown(function() {
			$("#btn-checkid").show();
			$("#img-checkid").hide();
		});
		$("#btn-checkid")
				.click(	function() {
							var id = $("#blog-id").val();
							if (id == '') {
								return;
							}
							$.ajax({
								url : "${pageContext.servletContext.contextPath}/user/api/check/checkid?id="+ id,
								type : "get",
								dataType : "json",
								data : "",
								success : function(response) {
									if (response.result != 'success') {
										console.log(response);
										return;
									}
									if (response.data == true) {
										console.log(response);
										alert("이미 존재하는 아이디 입니다.\n다른 아이디를 사용하세요.");
										$("#blog-id").focus().val("");
										return;
									}
									$("#btn-checkid").hide();
									$("#img-checkid").show();
								}
							});
						});
	});
</script>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div class="center-content">
		<h1 class="logo">JBlog</h1>
		<jsp:include page="/WEB-INF/views/includes/menubar.jsp" />

		<form:form modelAttribute="userVo" class="join-form" id="join-form"
			method="post" action="${pageContext.request.contextPath}/user/join">
			<label class="block-label" for="name">이름</label>
			<input id="name" name="name" type="text" value="">
				<!-- 
			<p>
				<spring:hasBindErrors name="userVo">
		            <c:if test="${errors.hasFieldErrors('name') }">                                     
		               <strong>${errors.getFieldError( 'name' ).defaultMessage }</strong>
					</c:if>
				</spring:hasBindErrors>
			</p>
		 	-->
			<p style="font-weight:bold; color:#f00; text-align:left; padding:0; margin:0;">
				<form:errors path="name"/>
			</p>
			
			<label class="block-label" for="blog-id">아이디</label>
			<input id="blog-id" name="id" type="text">
			<input id="btn-checkid" type="button" value="id 중복체크">
			<img id="img-checkid" style="display: none;"
				src="${pageContext.request.contextPath}/assets/images/check.png">
			<!-- 
			<p>
				<spring:hasBindErrors name="userVo">
				<c:if test="${errors.hasFieldErrors('id') }">                                     
	               <strong>${errors.getFieldError( 'id' ).defaultMessage }</strong>
				</c:if>
				</spring:hasBindErrors>
			</p>
 			-->
			<p style="font-weight:bold; color:#f00; text-align:left; padding:0; margin:0;">
				<form:errors path="id"/>
			</p>
			<label class="block-label" for="password">패스워드</label>
			<input id="password" name="password" type="password" />
			<!-- 
			<p>
				<spring:hasBindErrors name="userVo">
				<c:if test="${errors.hasFieldErrors('password') }">                                     
	               <strong>${errors.getFieldError( 'password' ).defaultMessage }</strong>
				</c:if>
				</spring:hasBindErrors>
		
			</p>
			-->
			<p style="font-weight:bold; color:#f00; text-align:left; padding:0; margin:0;">
				<form:errors path="password"/>
			</p>
 			
			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">
		</form:form>
	</div>
</body>
</html>
