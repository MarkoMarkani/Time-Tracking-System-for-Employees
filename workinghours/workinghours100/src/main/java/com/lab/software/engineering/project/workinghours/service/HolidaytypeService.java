package com.lab.software.engineering.project.workinghours.service;

import java.util.List;


import com.lab.software.engineering.project.workinghours.entity.Holidaytype;

public interface HolidaytypeService {
	
	public List<Holidaytype> getHolidaytype();
	
	public void saveHolidaytype(Holidaytype holidaytype);
	public void deleteHolidaytype(Long holidaytypeid);
}
