package com.lab.software.engineering.project.workinghours.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab.software.engineering.project.workinghours.entity.Holidayperemployee;
import com.lab.software.engineering.project.workinghours.service.HolidayperemployeeService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class HolidayperemployeeRestController {
	
	@Autowired
	private HolidayperemployeeService holidayperemployeeService;
	
	@GetMapping("/employees/{employeeid}/holidays")
	public List<Holidayperemployee> getHolidaysByEmployeeId(@PathVariable Long employeeid) {
		return holidayperemployeeService.getHolidaysByEmployeeId(employeeid);
	}
	
	@PostMapping("/employees/{employeeid}/holiday")
	public Holidayperemployee saveHolidayperemployee(@RequestBody Holidayperemployee holidayperemployee, @PathVariable Long employeeid) {
		
		
		return holidayperemployeeService.saveHolidayperemployee(holidayperemployee,employeeid);
	
	}
}
