package com.lab.software.engineering.project.workinghours.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WorkingdayServiceAspect {

	@Pointcut("execution(* com.lab.software.engineering.project.workinghours.service.WorkingdayServiceImpl.saveWorking(..))")
	public void saveWorkingday() {
		
	}
	@Before("saveWorkingday()")
		public void loggingAdvice(JoinPoint joinPoint) {
			System.out.println("Before method: "+ joinPoint.getSignature());
			}
	
	@After("saveWorkingday()")
	public void loggingAdviceAfter(JoinPoint joinPoint) {
		System.out.println("After method: "+ joinPoint.getSignature());
		}
	
}
