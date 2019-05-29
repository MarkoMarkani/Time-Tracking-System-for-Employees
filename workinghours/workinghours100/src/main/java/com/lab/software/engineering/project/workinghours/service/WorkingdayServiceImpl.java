package com.lab.software.engineering.project.workinghours.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lab.software.engineering.project.workinghours.dao.EmployeeRepository;
import com.lab.software.engineering.project.workinghours.dao.WorkingdayRepository;
import com.lab.software.engineering.project.workinghours.entity.Employee;
import com.lab.software.engineering.project.workinghours.entity.Weekday;
import com.lab.software.engineering.project.workinghours.entity.Workingday;

//working day service implementation
@Service
public class WorkingdayServiceImpl implements WorkingdayService {

	// inject Workingday repository
	@Autowired
	private WorkingdayRepository workingDayRepository;

	// inject Employee repository
	@Autowired
	private EmployeeRepository employeeRepository;

	// find all employees and their working days
	@Override
	public List<Workingday> employeeWithWorkingday() {
		return workingDayRepository.findAll();
	}

	//save working day for the given employee
	@Override
	public Workingday saveWorking(Workingday s, long employeeid) {
		Employee employee = null;
		//saving current date and time
		LocalDateTime w = LocalDateTime.now();
		//checking if there is employee with given employee id
		Optional<Employee> emp = employeeRepository.findById(employeeid);
		if (emp.isPresent()) {
			employee = emp.get();
			//if employee is present - saving working days for that employee in the list workingdays
			List<Workingday> workingdays = employee.getWorkingdays();
			Workingday workingday = null;
			//if the list of working days is not empty we compare check-in dates in the list with current date
			if (!workingdays.isEmpty()) {
				for (Workingday w1 : workingdays) {
					//if there is a working day with current date we return null
					if ((w1.getCheckin().getYear() == w.getYear()) && (w1.getCheckin().getMonth() == w.getMonth())
							&& (w1.getCheckin().getDayOfMonth() == w.getDayOfMonth())
							&& (employeeid == w1.getEmployee().getEmployeeid())) {
						workingday = w1;
						return null;
					}
				}
				//else we save that working day for the given employee and return working day
				if (workingday == null) {
					s.setEmployee(employee);
					workingDayRepository.save(s);
					return s;
				}
			} 
			//if the list of working days is empty we save that working day for the given employee and return working day
			else {
				s.setEmployee(employee);
				workingDayRepository.save(s);
				return s;
			}
		} 
		//if employee is not present - return null
		else {
			System.out.println("Cannot find employee");

		}
		return null;
	}

	//setting weekday for the given working day
	public void setWeekday(Workingday w) {
		Weekday weekday = new Weekday();
		String day = w.getCheckin().getDayOfWeek().toString();
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
		w.setWeekday(weekday);
	}

	
	@Override
	//getting working day for the given id
	public Workingday getWorkingday(long theId) {

		Optional<Workingday> result = workingDayRepository.findById(theId);
		Workingday workingday = null;
		if (result.isPresent()) {
			workingday = result.get();
		} else {
			throw new RuntimeException("Did not find working day id: " + theId);
		}
		return workingday;
	}

	@Override
	//returning list of working days for the given employee
	public List<Workingday> getWorkingDaysByEmployeeID(long employeeid) {

		Optional<Employee> result = employeeRepository.findById(employeeid);
		Employee theEmployee = null;
		if (result.isPresent()) {
			theEmployee = result.get();
		} else {
			throw new RuntimeException("Did not find employee id: " + employeeid);
		}

		return workingDayRepository.getWorkingDaysByEmployeeID(theEmployee);
	}
	
	//update working day - saving check-out
	@Override
	public Workingday updateWorkingDay(Workingday checkoutString, long employeeid) {
		
		//saving current date and time
		LocalDateTime checkout = LocalDateTime.now();
		System.out.println(checkout + " empl: " + employeeid);
		//checking if there is employee with given employee id
		Optional<Employee> result = employeeRepository.findById(employeeid);
		Employee theEmployee = null;
		if (result.isPresent()) {
			theEmployee = result.get();
		} else {
			throw new RuntimeException("Did not find employee id: " + employeeid);
		}
		//if employee is present - saving working days for that employee in the list workingdays
		List<Workingday> workingdays = theEmployee.getWorkingdays();	
		//in the list of working days we compare check-out dates in the list with current date 
		for (Workingday w1 : workingdays) {
			if (w1.getCheckout() != null) {
				if ((w1.getCheckout().getYear() == checkout.getYear())
						&& (w1.getCheckout().getMonth() == checkout.getMonth())
						&& (w1.getCheckout().getDayOfMonth() == checkout.getDayOfMonth())
						&& (employeeid == w1.getEmployee().getEmployeeid())) {
					//if check-out exist - return null
				return null;		}
				}
		}
		//finding working day with same date check-in
		Workingday workingday2 = null;
		for (Workingday w : workingdays) {
			if ((w.getCheckin().getYear() == checkout.getYear()) && (w.getCheckin().getMonth() == checkout.getMonth())
					&& (w.getCheckin().getDayOfMonth() == checkout.getDayOfMonth())
					&& (w.getCheckin().getDayOfMonth() == checkout.getDayOfMonth())
					&& (employeeid == w.getEmployee().getEmployeeid())) {
				workingday2 = w;
				break;
			}
		}
		//if there is not working day return null
		if (workingday2 == null) {
			return null;
		} 
		//else save check-out in that working day
		else {
			workingday2.setCheckout(checkout);
			workingDayRepository.save(workingday2);
			return workingday2;
		}
	}
	//calculate duration of work in minutes for the given working day
	public long duration(Workingday w) {
		Duration duration = Duration.between(w.getCheckin(), w.getCheckout());
		long diff = Math.abs(duration.toMinutes());
		return diff;
	}
	
	//returning list of overtime working days for all employees
	@Override
	public List<Employee> findOvertime() {
		List<Employee> overtimeList = new ArrayList<Employee>();
		List<Workingday> workingdays = workingDayRepository.findAll();
		for (Workingday workingday : workingdays) {
			
			System.out.println("workingday " + workingday.getWorkDuration());
			if (workingday.getWorkDuration() > 8) {
				overtimeList.add(workingday.getEmployee());
			}
		}
		return overtimeList;
	}
	//returning list of working days in the given time range
	@Override
	public List<Workingday> findWorkingdaysFromDateToDate(LocalDateTime checkin, LocalDateTime checkin1) {

		return workingDayRepository.findWorkingdaysFromDateToDate(checkin, checkin1);
	}

}
