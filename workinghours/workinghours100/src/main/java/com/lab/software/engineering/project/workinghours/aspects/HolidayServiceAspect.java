package com.lab.software.engineering.project.workinghours.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HolidayServiceAspect {

	@Pointcut("execution(* com.lab.software.engineering.project.workinghours.service.HolidayServiceImpl.*(..))")
	public void holidayPointcut() {
		}
	
	@Before("holidayPointcut()")
		public void loggingBefore(JoinPoint joinPoint) {
		System.out.println("Before method: " + joinPoint.getSignature());
		System.out.println("About to execute method in PaymentService: "+ joinPoint.getSignature().getName());
		}
	@After("holidayPointcut()")
	public void loggingAfter(JoinPoint joinPoint) {
	System.out.println("After method: " + joinPoint.getSignature());
	System.out.println("Successuful execute of method in PaymentService: "+ joinPoint.getSignature().getName());
	}
	
}
