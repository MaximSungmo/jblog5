package com.cafe24.jblog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.cafe24.web.config.FileuploadConfig;
import com.cafe24.web.config.MVCConfig;
import com.cafe24.web.config.MessageConfig;
import com.cafe24.web.config.SecurityConfig;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan({"com.cafe24.jblog.controller", "com.cafe24.jblog.controller.api","com.cafe24.jblog.exception"})
@Import({MVCConfig.class, SecurityConfig.class, MessageConfig.class, FileuploadConfig.class})
public class WebConfig {

//	@ComponentScan으로 인하여 기존에 spring-servlet.xml이 사라질 수 있게 되었으므로 web.xml에서 하기의 코드는 변경한다.
//	본 역할은 applicationContext에 미리 생성된 객체를 맵핑하여 사용하는 데 쓰인다.
//	<servlet>
//		<servlet-name>spring</servlet-name>
//		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
//	</servlet>
//	<servlet-mapping>
//		<servlet-name>spring</servlet-name>
//		<url-pattern>/</url-pattern>
//	</servlet-mapping>

//	*web.xml*에서 변경된 코드 
//	<!-- Dispatcher Server(Front Controller) -->	
//	<servlet>
//		<servlet-name>spring</servlet-name>
//		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
//		<init-param>
//			<param-name>contextClass</param-name>
//			<param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value>
//		</init-param>
//		<init-param>
//			<param-name>contextConfigLocation</param-name>
//			<param-value>com.cafe24.jblog.config.WebConfig</param-value>
//		</init-param>
//		<load-on-startup>1</load-on-startup>	
//	</servlet>
//	<servlet-mapping>
//		<servlet-name>spring</servlet-name>
//		<url-pattern>/</url-pattern>
//	</servlet-mapping>

//	spring-servlet.xml의 하기 코드를 @EnableAspectJAutoProxy으로 대체 
//	<!-- 빈설정을 annotation으로 하겠다.(@Controller에 auto scanning) -->
//	<context:annotation-config />
//	<!-- Controller Scanning 하는 base package 지정 -->
//	<context:component-scan
//		base-package="com.cafe24.jblog.controller, com.cafe24.jblog.controller.api, com.cafe24.jblog.exception" />

//	spring-servlet.xml의 하기 코드를 @EnableAspectJAutoProxy으로 대체 
//	<!-- auto proxy -->
//	<aop:aspectj-autoproxy />

}
