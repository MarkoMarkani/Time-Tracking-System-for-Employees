package com.lab.software.engineering.project.workinghours.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lab.software.engineering.project.workinghours.dao.EmployeeRepository;
import com.lab.software.engineering.project.workinghours.dao.EmployeeRepositoryCustom;
import com.lab.software.engineering.project.workinghours.dao.JobHistoryRepository;
import com.lab.software.engineering.project.workinghours.entity.Employee;
import com.lab.software.engineering.project.workinghours.entity.Job;
import com.lab.software.engineering.project.workinghours.entity.Jobhistory;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	// inject Employee repository
	@Autowired
	private EmployeeRepository employeeRepository;

	// inject Employee custom repository
	@Autowired
	private EmployeeRepositoryCustom employeeRepositoryCustom;

	// inject JobHistory repository
	@Autowired
	private JobHistoryRepository jobhistoryRepository;

	@Override

	public List<Employee> getEmployee() {
		return employeeRepository.findAll(new Sort(Sort.Direction.ASC, "employeeid"));
	}

	// save employee
	@Override
	public Employee saveEmployee(Employee theEmployee) {
		System.out.println("Save Employee");
		Job job = theEmployee.getJob();

		Employee employeeUsername = employeeRepository.findByUserame(theEmployee.getUsername());
		Employee employeeEmail = employeeRepository.findByEmail(theEmployee.getEmail());
		if (employeeUsername == null && employeeEmail == null) {
			System.out.println("Employee doesn't exist");
			employeeRepository.save(theEmployee);
			Employee employee2 = employeeRepository.findByUserame(theEmployee.getUsername());

			Jobhistory jobHistory = new Jobhistory();
			jobHistory.setJob(job);
			jobHistory.setStartdate(theEmployee.getHiredate());
			jobHistory.setEmployee(employee2);

			jobhistoryRepository.save(jobHistory);
			return null;
		} else {
			if (employeeEmail == null) {
				System.out.println(
						"Employee exist : " + employeeUsername.getEmail() + " - " + employeeUsername.getUsername());
				return employeeUsername;
			} else {
				System.out.println(
						"Employee doesn't exist : " + employeeEmail.getEmail() + " - " + employeeEmail.getUsername());
				return employeeEmail;
			}

		}

	}

	@Override
	// return employee by employee id
	public Employee getEmployee(long theId) {

		Optional<Employee> result = employeeRepository.findById(theId);
		Employee theEmployee = null;
		if (result.isPresent()) {
			theEmployee = result.get();
		} else {
			throw new RuntimeException("Did not find employee id: " + theId);
		}
		return theEmployee;
	}

	@Override
	// delete employee
	public void deleteEmployee(long theId) {

		employeeRepository.deleteById(theId);
	}

	@Override
	@Transactional
	// get employees and their jobs-join
	public List<?> getEmployeesAndTheirJobs() {

		return employeeRepositoryCustom.getEmployeesAndTheirJobs();
	}

	@Override
	@Transactional
	// get job history for given employee
	public List<Jobhistory> getJobhistoryByEmployeeID(long employeeid) {

		return employeeRepositoryCustom.getJobhistoryByEmployeeID(employeeid);
	}

	// change employee job
	@Override
	public void changeEmployeeJob(Job job, long employeeid) {

		Optional<Employee> result = employeeRepository.findById(employeeid);
		Employee theEmployee = null;
		if (result.isPresent()) {
			theEmployee = result.get();
			List<Jobhistory> jobhistories = theEmployee.getJobhistories();
			Jobhistory jobhistory = null;
			for (Jobhistory jh : jobhistories) {
				if ((jh.getEmployeeId() == theEmployee.getEmployeeid())
						&& (jh.getJob().getJobid() == theEmployee.getJob().getJobid()) && (jh.getEnddate() == null)) {
					jobhistory = jh;

					Date date = new Date();
					System.out.println(date);
					jobhistory.setEnddate(date);
					jobhistoryRepository.save(jobhistory);

					Jobhistory jobHistory = new Jobhistory();
					jobHistory.setJob(job);
					jobHistory.setStartdate(date);
					jobHistory.setEmployee(theEmployee);
					jobhistoryRepository.save(jobHistory);
					theEmployee.setJob(job);
					employeeRepository.save(theEmployee);
				}
			}
		} else {
			throw new RuntimeException("Did not find employee id: " + employeeid);
		}

	}

	@Override
	// update employee
	public void updateEmployee(Employee employee, long employeeid) {

		Optional<Employee> result = employeeRepository.findById(employeeid);
		Employee theEmployee = null;
		if (result.isPresent()) {
			theEmployee = result.get();

			theEmployee.setFirstname(employee.getFirstname());
			theEmployee.setLastname(employee.getLastname());
			theEmployee.setPassword(employee.getPassword());
			employeeRepository.save(theEmployee);

		} else {
			throw new RuntimeException("Did not find employee id: " + employeeid);
		}

	}

	@Override
	// login employee
	public Employee loginEmployee(Employee theEmployee) {

		Employee emp = employeeRepository.findByUserame(theEmployee.getUsername());

		if (emp != null) {
			System.out.println("Username: " + emp.getUsername());
			if (emp.getPassword().equals(theEmployee.getPassword())) {
				return emp;
			} else {
				System.out.println("Invalid password: " + emp.getPassword() + "=" + theEmployee.getPassword());
				return null;
			}
		} else {
			System.out.println("Employee doesn't exist");
			return null;
		}

	}

}
