package com.lab.software.engineering.project.workinghours.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class JobHistoryServiceAspect {
	@Pointcut("execution(* com.lab.software.engineering.project.workinghours.service.JobHistoryServiceImpl.*(..))")
	public void jobHistoryPointcut() {
		}
	@Before("jobHistoryPointcut()")
	public void loggingBefore(JoinPoint joinPoint) {
	System.out.println("Before method: " + joinPoint.getSignature());
	System.out.println("About to execute method in JobHistoryService: "+ joinPoint.getSignature().getName());
	}
	
	@After("jobHistoryPointcut()")
	public void loggingAfter(JoinPoint joinPoint) {
		System.out.println("After method: " + joinPoint.getSignature());
		System.out.println("Succsessuful executing of method in JobHistoryService " + joinPoint.getSignature().getName());
	}
}
