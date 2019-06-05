package com.cafe24.web.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageConfig {

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("com/cafe24/config/web/messages/messages_ko");
		//추 후 소스 변경 시 위 setBasename 변경 
		messageSource.setDefaultEncoding("UTF-8");
		
		return messageSource;
	}
	
//	*spring-servlet.xml*에 작성된 하기의 코드를 위와 같이 Java 로 설정  	
//	<!-- MessageSource -->
//	<bean id="messageSource"
//		class="org.springframework.context.support.ResourceBundleMessageSource">
//		<property name="basenames">
//			<list>
//				<value>messages/messages_ko</value>
//			</list>
//		</property>
//	</bean>
	
}
