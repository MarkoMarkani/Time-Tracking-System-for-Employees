package com.lab.software.engineering.project.workinghours.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab.software.engineering.project.workinghours.entity.Jobhistory;
import com.lab.software.engineering.project.workinghours.service.JobHistoryService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class JobHistoryRestController {

	@Autowired
	private JobHistoryService jobHistoryService;
	
//	@GetMapping("/jobhistory")
//	public List<Jobhistory> getAll(){
//		return jobHistoryService.getJobhistory();
//		//return employeeService.getEmployeesAndTheirJobs();
//	}
//	
//	@GetMapping("/jobhistory/{jobhistoryid}")
//	public Optional<Jobhistory> getJobhistoryByID(@PathVariable long employeeid){
//		return jobHistoryService.findById(employeeid);
//	}
//	
	@GetMapping("/employees/{employeeid}/jobhistories")
	public List<Jobhistory> getJobhistoryByEmployeeID(@PathVariable long employeeid){
		
		return jobHistoryService.getJobhistoryByEmployeeID(employeeid);
	}
	
//	@PostMapping("/jobhistory")
//	public Jobhistory createJobhistory(@RequestBody Jobhistory newJobhistory){
//		return jobHistoryService.save(newJobhistory);
//	
//	}
}