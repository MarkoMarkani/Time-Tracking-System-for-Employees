package com.lab.software.engineering.project.workinghours.dao;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.lab.software.engineering.project.workinghours.entity.Employee;
import com.lab.software.engineering.project.workinghours.entity.Workingday;

//working day repository
public interface WorkingdayRepository extends JpaRepository<Workingday, Long> {

	//custom method - returnig list of working days for the given employee
	@Query("Select w FROM Workingday w WHERE w.employee=?1")
	List<Workingday> getWorkingDaysByEmployeeID(Employee emp);

	//custom method - returnig list of working days for the given time frame
	@Query("FROM Workingday w WHERE w.checkin>:checkin AND w.checkin<:checkin1")
	List<Workingday> findWorkingdaysFromDateToDate(@Param("checkin") LocalDateTime checkin,
			@Param("checkin1") LocalDateTime checkin1);
}
