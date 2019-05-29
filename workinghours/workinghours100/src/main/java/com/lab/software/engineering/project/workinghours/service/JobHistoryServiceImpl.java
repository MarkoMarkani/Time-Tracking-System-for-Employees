package com.lab.software.engineering.project.workinghours.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lab.software.engineering.project.workinghours.dao.JobHistoryRepositoryCustom;
import com.lab.software.engineering.project.workinghours.entity.Jobhistory;

@Service
public class JobHistoryServiceImpl implements JobHistoryService {

	@Autowired
	private JobHistoryRepositoryCustom employeeRepositoryCustom;
	
	@Override
	@Transactional
	public List<Jobhistory> getJobhistoryByEmployeeID(long employeeid) {
		return employeeRepositoryCustom.getJobhistoryByEmployeeID(employeeid);
	}

}
