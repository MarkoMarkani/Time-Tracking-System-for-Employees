package com.lab.software.engineering.project.workinghours.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lab.software.engineering.project.workinghours.entity.Holiday;
import com.lab.software.engineering.project.workinghours.service.HolidayService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class HolidayRestController {

	@Autowired
	private HolidayService holidayService;
	
	@GetMapping("/holidays")
	public List<Holiday> getAll() {
		return holidayService.getHolidays();
	}
	
	@PostMapping("/holidays")
	public Holiday saveHoliday(@RequestBody Holiday holiday) {
		
		
		return holidayService.saveHoliday(holiday);
	
	}
	}
	
	
	
	

