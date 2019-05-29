package com.lab.software.engineering.project.workinghours.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lab.software.engineering.project.workinghours.entity.Employee;
import com.lab.software.engineering.project.workinghours.entity.Jobhistory;

@Repository
public class JobHistoryRepositoryCustomImpl implements JobHistoryRepositoryCustom {
	@Autowired
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<Jobhistory> getJobhistoryByEmployeeID(long employeeid) {
		Session session = entityManager.unwrap(Session.class);
		Employee employee = session.get(Employee.class, employeeid);
		String hql = "from Jobhistory j where j.employee=:id";
		

		List<Jobhistory> jobHistories = session.createQuery(hql).setParameter("id", employee).list();
		System.out.println(jobHistories);
		return jobHistories;
	}

}
