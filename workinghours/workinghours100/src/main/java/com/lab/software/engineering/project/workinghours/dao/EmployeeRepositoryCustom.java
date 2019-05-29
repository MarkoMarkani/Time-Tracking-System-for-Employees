package com.lab.software.engineering.project.workinghours.dao;

import java.util.List;

import com.lab.software.engineering.project.workinghours.entity.Jobhistory;
import com.lab.software.engineering.project.workinghours.entity.Workingday;

public interface EmployeeRepositoryCustom {

	public List<?> getEmployeesAndTheirJobs();
	public List<Workingday> getWorkingDaysByEmployeeID(long employeeid);
	public List<Jobhistory> getJobhistoryByEmployeeID(long employeeid);
	
}
