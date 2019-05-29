package com.lab.software.engineering.project.workinghours.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.lab.software.engineering.project.workinghours.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	@Query("Select e FROM Employee e WHERE e.username=?1")
	Employee findByUserame(String username);
	@Query("Select e FROM Employee e WHERE e.email=?1")
	Employee findByEmail(String email);
	@Query("SELECT t FROM Employee t WHERE t.active=true")
	 List<Employee> findByActive();
	 

}
