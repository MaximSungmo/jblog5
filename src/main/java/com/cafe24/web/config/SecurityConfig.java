package com.cafe24.web.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.cafe24.security.AuthAdminInterceptor;
import com.cafe24.security.AuthInterceptor;
import com.cafe24.security.AuthLoginInterceptor;
import com.cafe24.security.AuthLogoutInterceptor;
import com.cafe24.security.AuthUserHandlerMethodArgumentResolver;

@Configuration
@EnableWebMvc
public class SecurityConfig extends WebMvcConfigurerAdapter {
	//
	// Argument Resolver
	//
	@Bean
	public AuthUserHandlerMethodArgumentResolver authUserHandlerMethodArgumentResolver() {
		return new AuthUserHandlerMethodArgumentResolver();
	}
//	*spring-servlet.xml*에 작성된 하기의 코드를 위와 같이 Java 로 설정  	
//	<!-- Argument Resolvers -->
//	<mvc:annotation-driven>
//		<mvc:argument-resolvers>
//			<bean class="com.cafe24.security.AuthUserHandlerMethodArgumentResolver" />
//		</mvc:argument-resolvers>
//	</mvc:annotation-driven>
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(authUserHandlerMethodArgumentResolver());
	}
	
	@Bean
	public AuthLoginInterceptor authLoginInterceptor() {
		return new AuthLoginInterceptor();
	}

	@Bean
	public AuthLogoutInterceptor authLogoutInterceptor() {
		return new AuthLogoutInterceptor();
	}
	
	@Bean
	public AuthAdminInterceptor authAdminInterceptor() {
		return new AuthAdminInterceptor();
	}

	@Bean
	public AuthInterceptor authInterceptor() {
		return new AuthInterceptor();
	}
	
	//
	// Interceptor
	//
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
		.addInterceptor(authLoginInterceptor())
		.addPathPatterns("/user/auth");
		
		registry
		.addInterceptor(authLogoutInterceptor())
		.addPathPatterns("/user/logout");
		
		registry
		.addInterceptor(authAdminInterceptor())
		.addPathPatterns("/*/admin/**");
		
		
		registry
		.addInterceptor(authInterceptor())
		.addPathPatterns("/**")
		.excludePathPatterns("/user/auth")
		.excludePathPatterns("/user/logout")
		.excludePathPatterns("/*/admin/**")
		.excludePathPatterns("/assets/**");
	}
//	*spring-servlet.xml*에 작성된 하기의 코드를 위와 같이 Java 로 설정  	
//	<!-- Interceptors -->
//	<mvc:interceptors>
//		<mvc:interceptor>
//			<mvc:mapping path="/user/auth" />
//			<bean class="com.cafe24.security.AuthLoginInterceptor" />
//		</mvc:interceptor>
//		<mvc:interceptor>
//			<mvc:mapping path="/user/logout" />
//			<bean class="com.cafe24.security.AuthLogoutInterceptor" />
//		</mvc:interceptor>
//
//		<mvc:interceptor>
//			<mvc:mapping path="/*/admin/**" />
//			<bean class="com.cafe24.security.AuthAdminInterceptor" />
//		</mvc:interceptor>
//
//		<mvc:interceptor>
//			<mvc:mapping path="/**" />
//			<mvc:exclude-mapping path="/user/auth" />
//			<mvc:exclude-mapping path="/user/logout" />
//			<mvc:exclude-mapping path="/assets/**" />
//			<mvc:exclude-mapping path="/*/admin/**" />
//			<bean class="com.cafe24.security.AuthInterceptor" />
//		</mvc:interceptor>
//	</mvc:interceptors>
}