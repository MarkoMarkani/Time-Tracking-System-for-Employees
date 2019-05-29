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

import com.lab.software.engineering.project.workinghours.entity.Vacation;
import com.lab.software.engineering.project.workinghours.service.VacationService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class VacationRestController {

	@Autowired
	private VacationService vacationService;

	@GetMapping("/vacations")
	public List<Vacation> getAll() {
		return vacationService.getVacation();
	}

	@GetMapping("/employees/{employeeid}/vacations")
	public List<Vacation> getVacationByEmployeeID(@PathVariable Long employeeid) {

		return vacationService.getVacationByEmployeeID(employeeid);
	}

	@PostMapping("/employees/{employeeid}/vacation")
	public Vacation saveVacationByEmployeeId(@RequestBody Vacation vacation,@PathVariable long employeeid ) {
		System.out.println("EID: " + employeeid);
		
		return vacationService.saveVacationByEmployeeId(vacation,employeeid);
	}

}
