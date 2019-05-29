package com.lab.software.engineering.project.workinghours.dao;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.lab.software.engineering.project.workinghours.entity.Employee;
import com.lab.software.engineering.project.workinghours.entity.Payment;
import com.lab.software.engineering.project.workinghours.entity.Workingday;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query("Select p FROM Payment p WHERE p.employee=?1")
	List<Payment>getPaymentByEmployeeId(Optional<Employee>emp);
    @Query("Select wd FROM Workingday wd WHERE wd.employee=?1 AND wd.checkin BETWEEN ?2 AND ?3")
	List<Workingday>getAllWorkingDaysBetweenTwoDates(Employee theEmployee, LocalDateTime startDate, LocalDateTime EndDate);
    @Query("Select p FROM Payment p WHERE p.employee=?1 AND p.fromdate=?2")
    Optional<Payment> findByDate(Employee theEmployee, Date date);
}
