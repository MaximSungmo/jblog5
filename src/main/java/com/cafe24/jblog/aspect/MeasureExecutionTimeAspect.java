package com.cafe24.jblog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MeasureExecutionTimeAspect{

	@Around("execution(* *..repository.*.*(..))")
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable{
		//before
		StopWatch sw = new StopWatch();
		sw.start();
		
		//method 실행
		Object result =  pjp.proceed();
		
		//after
		sw.stop();
		Long totalTime = sw.getTotalTimeMillis();
		
		String className = pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();
		String taskName = className + "." + methodName;
		System.out.println("[Execution Time]["+taskName+"]"+totalTime+"mils");
		return result;
	}
	
	@Before("execution(* *..controller.*.*(..))")
	public void beforeAdvice(JoinPoint joinpoint) {
		MethodSignature signature = (MethodSignature)joinpoint.getSignature();
		String methodName = signature.getMethod().getName();
		
		System.out.println("============="+methodName);
	}	
}