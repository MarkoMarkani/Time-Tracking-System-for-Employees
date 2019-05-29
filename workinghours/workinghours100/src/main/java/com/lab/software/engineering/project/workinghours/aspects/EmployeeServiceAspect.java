package com.lab.software.engineering.project.workinghours.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.lab.software.engineering.project.workinghours.entity.Employee;




@Aspect
@Component
public class EmployeeServiceAspect {
	
	@Before(value = "execution(* com.lab.software.engineering.project.workinghours.service.EmployeeServiceImpl.getEmployee())")
	public void beforeAdvice(JoinPoint joinPoint) {
		System.out.println("Before method:" + joinPoint.getSignature());

		System.out.println("About to get employees from database");
	}
	@After(value = "execution(* com.lab.software.engineering.project.workinghours.service.EmployeeServiceImpl.getEmployee())")
	public void afterAdvice(JoinPoint joinPoint) {
		System.out.println("After method:" + joinPoint.getSignature());

		System.out.println("Successuful.Getting employees from database");
	}
	
	@Around(value = "execution(* com.lab.software.engineering.project.workinghours.service.EmployeeServiceImpl.saveEmployee(..))")
	public Object employeeAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
		System.out.println("Before invoking saveEmployee() method");
		Object value = null;
		try {
			value = proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("After invoking saveEmployee() method. Return value=" + value);
		return value;
	}
	@AfterReturning(pointcut = "execution(* com.lab.software.engineering.project.workinghours.service.EmployeeServiceImpl.getEmployee(long))", returning = "theEmployee")
	 public void afterReturningAdvice(JoinPoint jp, Employee theEmployee){
	      System.out.println("Method Signature: "  + jp.getSignature());  
	      System.out.println("Returning:" + theEmployee.getFirstname() + " " + theEmployee.getLastname());
	   }
	
}
