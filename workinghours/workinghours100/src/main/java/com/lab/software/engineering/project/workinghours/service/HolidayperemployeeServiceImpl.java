package com.lab.software.engineering.project.workinghours.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab.software.engineering.project.workinghours.dao.EmployeeRepository;
import com.lab.software.engineering.project.workinghours.dao.HolidayperemployeeRepository;
import com.lab.software.engineering.project.workinghours.entity.Employee;
import com.lab.software.engineering.project.workinghours.entity.Holidayperemployee;
import com.lab.software.engineering.project.workinghours.entity.Weekday;

@Service
public class HolidayperemployeeServiceImpl implements HolidayperemployeeService{

	
	@Autowired
	private HolidayperemployeeRepository holidayperemployeeRepository;
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Holidayperemployee> getHolidaysByEmployeeId(long employeeid){
		Employee employee=null;
		
		Optional<Employee> emp = employeeRepository.findById(employeeid);
		if (emp.isPresent()) {
			employee = emp.get();
		}
		return holidayperemployeeRepository.getHolidaysByEmployeeId(employee);
	}
	
	@Override
	public Holidayperemployee saveHolidayperemployee(Holidayperemployee holidayperemployee,long employeeid) {

	holidayperemployee.setHolidayperemployeeid(0);
	Employee employee=null;
	
	Optional<Employee> emp = employeeRepository.findById(employeeid);
	if (emp.isPresent()) {
		employee = emp.get();
	}
	List<Holidayperemployee> holidays = holidayperemployeeRepository.getHolidaysByEmployeeId(employee);
	Weekday weekday = new Weekday();
	
	
	if(holidays.isEmpty()) {
		weekday = setWeekday(holidayperemployee.getDate());
		holidayperemployee.setEmployee(employee);
		holidayperemployee.setWeekday(weekday);
		
		holidayperemployeeRepository.save(holidayperemployee);
	}else {
		for(Holidayperemployee h: holidays) {
			if(h.getDate().equals(holidayperemployee.getDate())) {
				return h;
			}
			
		}
	}
	weekday = setWeekday(holidayperemployee.getDate());
	holidayperemployee.setEmployee(employee);
	holidayperemployee.setWeekday(weekday);

	
	holidayperemployeeRepository.save(holidayperemployee);
	
	
	return null;
	}
	public Weekday setWeekday(Date date) {
		Weekday weekday = new Weekday();
		LocalDateTime l = convertToLocalDateTimeViaInstant(date);
		String day = l.getDayOfWeek().toString();
		weekday.setName(day);
		switch (day) {
		case "MONDAY":
			weekday.setWeekdayid(1l);
			break;

		case "TUESDAY":
			weekday.setWeekdayid(2l);
			break;
		case "WEDNESDAY":
			weekday.setWeekdayid(3l);
			break;
		case "THURSDAY":
			weekday.setWeekdayid(4l);
			break;
		case "FRIDAY":
			weekday.setWeekdayid(5l);
			break;
		case "SATURDAY":
			weekday.setWeekdayid(6l);
			break;
		case "SUNDAY":
			weekday.setWeekdayid(7l);
			break;
		}
		System.out.println("Weekday" + weekday);
		return weekday;
	}
	public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDateTime();
	}
}
