package com.lab.software.engineering.project.workinghours.service;

import java.util.List;

import com.lab.software.engineering.project.workinghours.entity.Vacation;

public interface VacationService {

	public List<Vacation> getVacation();
	
	public List<Vacation> getVacationByEmployeeID(long employeeid);

	public Vacation saveVacationByEmployeeId(Vacation vacation,long employeeid);
}
