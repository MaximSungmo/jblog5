package com.cafe24.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@PropertySource("classpath:multipart/multipart.properties")
public class FileuploadConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private Environment env;
	
	/*
	 * Multipart Resolver
	 */
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(env.getProperty("maxUploadSize", Long.class));
		multipartResolver.setMaxInMemorySize(env.getProperty("maxInmemorySize", Integer.class));
		multipartResolver.setDefaultEncoding(env.getProperty("defaultEncoding"));
		return multipartResolver;
	}
//	*spring-servlet.xml*에 작성된 하기의 코드를 위와 같이 Java 로 설정  	
//	<!-- 멀티파트 리졸버 -->
//	<bean id="multipartResolver"
//		class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
//		<!-- 최대업로드 가능한 바이트크기 -->
//		<property name="maxUploadSize" value="52428800" />
//		<!-- 디스크에 임시 파일을 생성하기 전에 메모리에 보관할수있는 최대 바이트 크기 -->
//		<!-- property name="maxInMemorySize" value="52428800" / -->
//		<!-- defaultEncoding -->
//		<property name="defaultEncoding" value="utf-8" />
//	</bean>
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {		
		registry.addResourceHandler("/assets/image/**").addResourceLocations("file:/jblog-uploads/");
	}
//	*spring-servlet.xml*에 작성된 하기의 코드를 위와 같이 Java 로 설정  	
//	<!-- the mvc resources url mapping -->
//	<mvc:resources mapping="/assets/image/**" location="file:/jblog-uploads/" />

	
}
