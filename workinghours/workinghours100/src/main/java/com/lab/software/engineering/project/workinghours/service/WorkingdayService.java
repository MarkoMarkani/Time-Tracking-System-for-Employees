
package com.lab.software.engineering.project.workinghours.service;

import java.time.LocalDateTime;
import java.util.List;

import com.lab.software.engineering.project.workinghours.entity.Employee;
import com.lab.software.engineering.project.workinghours.entity.Workingday;

//working day service inteface
public interface WorkingdayService {
	public List<Workingday> employeeWithWorkingday();

	public Workingday saveWorking(Workingday w, long employeeid);

	public void setWeekday(Workingday w);

	public Workingday getWorkingday(long theId);

	public List<Workingday> getWorkingDaysByEmployeeID(long employeeid);

	public Workingday updateWorkingDay(Workingday checkoutString, long employeeid);

	public List<Employee> findOvertime();

	public List<Workingday> findWorkingdaysFromDateToDate(LocalDateTime checkin, LocalDateTime checkin1);
}