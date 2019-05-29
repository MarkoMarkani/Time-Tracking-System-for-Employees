package com.lab.software.engineering.project.workinghours.rest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab.software.engineering.project.workinghours.entity.Employee;
import com.lab.software.engineering.project.workinghours.entity.Workingday;
import com.lab.software.engineering.project.workinghours.service.WorkingdayService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class WorkingdayRestController {

	@Autowired
	private WorkingdayService workingDayService;

	@GetMapping("/all")
	public List<Workingday> getAll() {
		return workingDayService.employeeWithWorkingday();
	}

	@GetMapping("/workingdays/{workingdayid}")
	public Workingday getWorkingdayByID(@PathVariable long workingdayid) {
		return workingDayService.getWorkingday(workingdayid);
	}

	@PostMapping("/employees/{employeeid}/workingday")
	public Workingday saveWorking(@RequestBody Workingday w, @PathVariable long employeeid) {

		workingDayService.setWeekday(w);
		System.out.println("Working day: " + w);

		return workingDayService.saveWorking(w, employeeid);
	}

	@GetMapping("/employees/{employeeid}/workingdays")
	public List<Workingday> getWorkingDaysByEmployeeID(@PathVariable long employeeid) {

		return workingDayService.getWorkingDaysByEmployeeID(employeeid);
	}

	@PostMapping("/employees/{employeeid}/workingday/checkout")
	public Workingday updateWorking(@RequestBody Workingday checkout1, @PathVariable long employeeid) {

		System.out.println(checkout1);
		return workingDayService.updateWorkingDay(checkout1, employeeid);

	}

	@GetMapping("/workingdays/overtime")
	public List<Employee> findOvertime() {
		System.out.println("Findovertime");
		return workingDayService.findOvertime();
	}

	@GetMapping("/workingdays/after/{checkin}/{checkin1}")
	public List<Workingday> findAfter(@PathVariable String checkin, @PathVariable String checkin1) {
		checkin = checkin + "T00:00:00";
		checkin1 = checkin1 + "T00:00:00";
		List<Workingday> overtimeList = new ArrayList<Workingday>();
		LocalDateTime checkin01 = LocalDateTime.parse(checkin);
		LocalDateTime checkin11 = LocalDateTime.parse(checkin1);
		List<Workingday> workingdays = workingDayService.findWorkingdaysFromDateToDate(checkin01, checkin11);
		for (Workingday workingday : workingdays) {
			if (workingday.getWorkDuration() > 480) {
				overtimeList.add(workingday);
			}
		}
		return overtimeList;
	}

}