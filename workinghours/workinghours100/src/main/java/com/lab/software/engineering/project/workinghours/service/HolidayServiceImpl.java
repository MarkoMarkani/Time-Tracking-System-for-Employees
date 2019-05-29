package com.lab.software.engineering.project.workinghours.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lab.software.engineering.project.workinghours.dao.HolidayRepository;
import com.lab.software.engineering.project.workinghours.entity.Holiday;
import com.lab.software.engineering.project.workinghours.entity.Weekday;


@Service
public class HolidayServiceImpl implements HolidayService{


	@Autowired
	private HolidayRepository holidayRepository;
	
	@Override
	public List<Holiday> getHolidays() {
		return holidayRepository.findAll();
	}

	
	@Override
	public Holiday saveHoliday(Holiday holiday) {

	holiday.setHolidayid(0);
		
	List<Holiday> holidays=holidayRepository.findAll();
	Weekday weekday = new Weekday();
	
	
	if(holidays.isEmpty()) {
		weekday = setWeekday(holiday.getDate());
		
		holiday.setWeekday(weekday);
		
		holidayRepository.save(holiday);
	}else {
		for(Holiday h: holidays) {
			if(h.getDate().equals(holiday.getDate())) {
				return h;
			}
			
		}
	}
	weekday = setWeekday(holiday.getDate());
	
	holiday.setWeekday(weekday);

	
	holidayRepository.save(holiday);
	
	
	return null;
	}
	public Weekday setWeekday(Date date) {
		Weekday weekday = new Weekday();
		LocalDateTime l = convertToLocalDateTimeViaInstant(date);
		String day = l.getDayOfWeek().toString();
		weekday.setName(day);
		switch (day) {
		case "MONDAY":
			weekday.setWeekdayid(1l);
			break;

		case "TUESDAY":
			weekday.setWeekdayid(2l);
			break;
		case "WEDNESDAY":
			weekday.setWeekdayid(3l);
			break;
		case "THURSDAY":
			weekday.setWeekdayid(4l);
			break;
		case "FRIDAY":
			weekday.setWeekdayid(5l);
			break;
		case "SATURDAY":
			weekday.setWeekdayid(6l);
			break;
		case "SUNDAY":
			weekday.setWeekdayid(7l);
			break;
		}
		System.out.println("Weekday" + weekday);
		return weekday;
	}
	public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDateTime();
	}
	
}
