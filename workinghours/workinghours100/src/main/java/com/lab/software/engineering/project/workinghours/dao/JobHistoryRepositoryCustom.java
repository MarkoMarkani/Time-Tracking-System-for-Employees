package com.lab.software.engineering.project.workinghours.dao;

import java.util.List;

import com.lab.software.engineering.project.workinghours.entity.Jobhistory;

public interface JobHistoryRepositoryCustom {

	public List<Jobhistory> getJobhistoryByEmployeeID(long employeeid);
	
}
