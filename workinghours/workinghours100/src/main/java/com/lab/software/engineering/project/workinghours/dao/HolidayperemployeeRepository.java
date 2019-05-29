package com.lab.software.engineering.project.workinghours.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lab.software.engineering.project.workinghours.entity.Employee;
import com.lab.software.engineering.project.workinghours.entity.Holidayperemployee;

public interface HolidayperemployeeRepository extends JpaRepository<Holidayperemployee, Long> {
	@Query("Select v FROM Holidayperemployee v WHERE v.employee=?1")
	List<Holidayperemployee> getHolidaysByEmployeeId(Employee emp);

}
