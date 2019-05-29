package com.lab.software.engineering.project.workinghours.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.lab.software.engineering.project.workinghours.entity.Employee;
import com.lab.software.engineering.project.workinghours.entity.Vacation;

public interface VacationRepository extends JpaRepository<Vacation, Long> {
	@Query("Select v FROM Vacation v WHERE v.employee=?1")
	List<Vacation> getVacationByEmployeeID(Optional<Employee> emp);
}
