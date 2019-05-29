package com.lab.software.engineering.project.workinghours.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class JobServiceAspect {
	@Pointcut("execution(* com.lab.software.engineering.project.workinghours.service.JobServiceImpl.*(..))")
	public void jobPointcut() {
		}
	
	@Before("jobPointcut()")
	public void loggingBefore(JoinPoint joinPoint) {
		System.out.println("Before method: " + joinPoint.getSignature() + joinPoint.getSignature().getName());
		System.out.println("About to execute method in JobService");
	}
	
	@After("jobPointcut()")
	public void loggingAfter(JoinPoint joinPoint) {
		System.out.println("After method: " + joinPoint.getSignature() + joinPoint.getSignature().getName());
		System.out.println("Successuful execution of method in JobService");
	}
	
	
}
