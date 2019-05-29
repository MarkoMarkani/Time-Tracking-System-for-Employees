package com.lab.software.engineering.project.workinghours.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lab.software.engineering.project.workinghours.entity.Weekday;

public interface WeekdayRepository extends JpaRepository<Weekday, Long> {

}
