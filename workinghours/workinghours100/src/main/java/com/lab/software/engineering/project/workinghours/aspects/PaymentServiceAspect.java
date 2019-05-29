package com.lab.software.engineering.project.workinghours.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;



@Aspect
@Component
public class PaymentServiceAspect {

	@Pointcut("execution(* com.lab.software.engineering.project.workinghours.service.PaymentServiceImpl.*(..))")
	public void paymentPointcut() {
		}
	
	@Before("paymentPointcut()")
		public void loggingBefore(JoinPoint joinPoint) {
			System.out.println("Before method: " + joinPoint.getSignature().getName());
			System.out.println("About to execute method in PaymentService: "+ joinPoint.getSignature().getName());
		}
	@After("paymentPointcut()")
	public void loggingAfter(JoinPoint joinPoint) {
		System.out.println("After method: " + joinPoint.getSignature().getName());
		System.out.println("Succsessuful execute method in PaymentService: " + joinPoint.getSignature().getName());
	}
}
