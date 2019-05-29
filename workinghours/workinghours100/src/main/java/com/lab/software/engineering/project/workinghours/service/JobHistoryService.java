package com.lab.software.engineering.project.workinghours.service;

import java.util.List;

import com.lab.software.engineering.project.workinghours.entity.Jobhistory;

public interface JobHistoryService {

	public List<Jobhistory> getJobhistoryByEmployeeID(long employeeid);
	
}
