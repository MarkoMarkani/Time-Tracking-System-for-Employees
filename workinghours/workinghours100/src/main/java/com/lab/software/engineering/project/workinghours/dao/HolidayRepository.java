package com.lab.software.engineering.project.workinghours.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lab.software.engineering.project.workinghours.entity.Holiday;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {

}
