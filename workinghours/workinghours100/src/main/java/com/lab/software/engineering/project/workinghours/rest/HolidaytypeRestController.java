package com.lab.software.engineering.project.workinghours.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lab.software.engineering.project.workinghours.entity.Holidaytype;
import com.lab.software.engineering.project.workinghours.service.HolidaytypeService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class HolidaytypeRestController {
	
	@Autowired
	private HolidaytypeService holidaytypeService;
	
	
	//GET ALL HOLIDAY TYPES
	@GetMapping("/holidaytypes")
	public List<Holidaytype> getAll() {
		return holidaytypeService.getHolidaytype();
	}
	
	//Add HOLIDAY TYPE
	@PostMapping("/holidaytype")
	public Holidaytype saveHolidayType(@RequestBody Holidaytype holidaytype) {
		holidaytypeService.saveHolidaytype(holidaytype);
		return holidaytype;
	}
	
	@DeleteMapping("/holidaytypes/{holidaytypeid}")
	public void deleteHolidaytype(@PathVariable long holidaytypeid) {
		System.out.println("Deleting holiday type " + holidaytypeid);
		
		holidaytypeService.deleteHolidaytype(holidaytypeid);
	}
}
