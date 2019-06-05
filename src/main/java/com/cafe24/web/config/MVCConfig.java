package com.cafe24.web.config;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

@Configuration
@EnableWebMvc
public class MVCConfig extends WebMvcConfigurerAdapter {

	/*
	 * viewResolver 
	 * spring-servlet.xml에서 작성하던 viewResolver Bean 생성 
	 */
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		
		return resolver; 
	}
//	*spring-servlet.xml*에 작성된 하기의 코드를 위와 같이 Java 로 설정  	
//	<!-- ViewResolver 설정 -->
//	<bean id="viewResolver"
//		class="org.springframework.web.servlet.view.InternalResourceViewResolver">
//		<property name="viewClass"
//			value="org.springframework.web.servlet.view.JstlView" />
//		<property name="prefix" value="/WEB-INF/views/" />
//		<property name="suffix" value=".jsp" />
//	</bean>
	
	
	/*
	 * defaultServlet Handler
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
//	*spring-servlet.xml*에 작성된 하기의 코드를 위와 같이 Java 로 설정  	
//	<!-- 서블릿 컨테이너의 디폴트 서블릿 위임 핸들러 -->
//	<mvc:default-servlet-handler />

	/*
	 * Message Converters
	 */
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		//indentoutput은 JSON 들여쓰기를 하라는 이야기
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder().indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd")).modulesToInstall(new ParameterNamesModule());
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(builder.build());
		converter.setSupportedMediaTypes(
				Arrays.asList(new MediaType("application","json",Charset.forName("UTF-8"))));	
		return converter;
	}
	
	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter() {
		StringHttpMessageConverter converter = new StringHttpMessageConverter();
		converter.setSupportedMediaTypes(
				Arrays.asList(new MediaType("text","html",Charset.forName("UTF-8"))));	
		return converter;
	}
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(mappingJackson2HttpMessageConverter());
		converters.add(stringHttpMessageConverter());
	}
//	*spring-servlet.xml*에 작성된 하기의 코드를 위와 같이 Java 로 설정  	
//	<!-- validator, conversionService, messageConverter를 자동으로 등록 -->
//	<mvc:annotation-driven>
//		<mvc:message-converters>
//			<bean
//				class="org.springframework.http.converter.StringHttpMessageConverter">
//				<property name="supportedMediaTypes">
//					<list>
//						<value>text/html; charset=UTF-8</value>
//					</list>
//				</property>
//			</bean>
//			<bean
//				class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
//				<property name="supportedMediaTypes">
//					<list>
//						<value>application/json; charset=UTF-8</value>
//					</list>
//				</property>
//			</bean>
//		</mvc:message-converters>
//	</mvc:annotation-driven>


	
	
}
