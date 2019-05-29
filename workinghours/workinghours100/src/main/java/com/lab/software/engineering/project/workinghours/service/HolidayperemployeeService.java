package com.lab.software.engineering.project.workinghours.service;

import java.util.List;

import com.lab.software.engineering.project.workinghours.entity.Holidayperemployee;

public interface HolidayperemployeeService {
	
	public List<Holidayperemployee> getHolidaysByEmployeeId(long employeeid);

	public Holidayperemployee saveHolidayperemployee(Holidayperemployee holidayperemployee,long employeeid);
}
