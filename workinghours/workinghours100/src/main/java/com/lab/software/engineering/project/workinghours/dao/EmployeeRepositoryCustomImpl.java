package com.lab.software.engineering.project.workinghours.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.lab.software.engineering.project.workinghours.entity.Employee;
import com.lab.software.engineering.project.workinghours.entity.Jobhistory;
import com.lab.software.engineering.project.workinghours.entity.Workingday;

@Repository
public class EmployeeRepositoryCustomImpl implements EmployeeRepositoryCustom {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<?> getEmployeesAndTheirJobs() {
		
		Session session = entityManager.unwrap(Session.class);
		String hql = "FROM Employee e INNER JOIN Job j ON\r\n" + 
				"    e.job = j.jobid\r\n" + 
				"ORDER BY\r\n" + 
				"    employeeid ASC\r\n" + 
				"    \r\n" + 
				"";
		
		List<?> list = session.createQuery(hql).list();
	
		return list;
	}

	@Override
	public List<Workingday> getWorkingDaysByEmployeeID(long employeeid) {
	
		Session session = entityManager.unwrap(Session.class);
		
		Employee employee = session.get(Employee.class, employeeid);
		
		List<Workingday> workingdays = employee.getWorkingdays();
		
		return workingdays;
	}

	@Override
	public List<Jobhistory> getJobhistoryByEmployeeID(long employeeid) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Employee employee = session.get(Employee.class, employeeid);
		
		List<Jobhistory> jobHistories = employee.getJobhistories();
		
		return jobHistories;
	}

}
