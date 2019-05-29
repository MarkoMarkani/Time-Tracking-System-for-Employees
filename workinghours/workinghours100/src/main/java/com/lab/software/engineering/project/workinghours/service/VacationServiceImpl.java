package com.lab.software.engineering.project.workinghours.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab.software.engineering.project.workinghours.dao.EmployeeRepository;
import com.lab.software.engineering.project.workinghours.dao.VacationRepository;
import com.lab.software.engineering.project.workinghours.entity.Employee;
import com.lab.software.engineering.project.workinghours.entity.Vacation;

@Service
public class VacationServiceImpl implements VacationService {

	@Autowired
	private VacationRepository vacationRepository;
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Vacation> getVacation() {
		return vacationRepository.findAll();
	}

	@Override
	public List<Vacation> getVacationByEmployeeID(long employeeid) {
	
		Optional<Employee> emp = employeeRepository.findById(employeeid);
		return vacationRepository.getVacationByEmployeeID(emp);

	}

	@Override

	public Vacation saveVacationByEmployeeId(Vacation vacation, long employeeid) {
		vacation.setVacationid(0);
		Employee employee = null;
		Date fromDate = vacation.getFromdate();
		Date toDate = vacation.getTodate();
		Optional<Employee> emp = employeeRepository.findById(employeeid);
		
		if(fromDate.after(toDate)) {
			System.out.println("Not valid"); 
			return vacation;
		}
		
		
		if (emp.isPresent()) {
			employee = emp.get();
			List<Vacation> vacations = vacationRepository.getVacationByEmployeeID(emp);
			if(vacations.isEmpty()) {
				vacation.setEmployee(employee);
	    		
	    		vacationRepository.save(vacation);
				return null;
			}else {
				for(Vacation v: vacations) {
					
					 if ((fromDate.after(v.getFromdate()) && fromDate.before(v.getTodate())) 
							 || (toDate.after(v.getFromdate()) && toDate.before(v.getTodate()))
							 || (fromDate.before(v.getFromdate()) && ((toDate.after(v.getTodate())) || (toDate.equals(v.getTodate()))))
							 || (fromDate.equals(v.getFromdate()) && toDate.equals(v.getTodate()))
							 ) { 
						  
				            // When Date d1 > Date d2 
				            System.out.println("It is between"); 
				            return v;
				        }else {
				        	
				        	
				    		vacation.setEmployee(employee);
				    		
				    		vacationRepository.save(vacation);
				    		return null;
				        }
				
				}
			}
		}
		return null;
		
		
	}


}
