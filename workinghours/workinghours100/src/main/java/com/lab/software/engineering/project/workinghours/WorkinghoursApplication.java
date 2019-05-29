package com.lab.software.engineering.project.workinghours;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class WorkinghoursApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkinghoursApplication.class, args);
	}

}
