package com.lab.software.engineering.project.workinghours.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;

/**
 * The persistent class for the HOLIDAY database table.
 * 
 */
@Entity
@Table(name = "holiday")
public class Holiday implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "HOLIDAY_HOLIDAYID_GENERATOR", sequenceName = "HOLIDAY_HOLIDAYID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HOLIDAY_HOLIDAYID_GENERATOR")
	private long holidayid;

	@Temporal(TemporalType.DATE)
	@Column(name = "HOLIDAYDATE")
	private Date date;
	
	@Column(name = "NAME")
	private String name;
	
	// bi-directional many-to-one association to Holidaytype
	@ManyToOne
	@JsonIgnoreProperties("holidays")
	@JoinColumn(name = "HOLIDAYTYPEID")
	private Holidaytype holidaytype;

	// bi-directional many-to-one association to Weekday
	@ManyToOne
	@JsonIgnoreProperties("holidays")
	@JoinColumn(name = "WEEKDAYID")
	private Weekday weekday;

	public Holiday() {
	}

	public long getHolidayid() {
		return this.holidayid;
	}

	public void setHolidayid(long holidayid) {
		this.holidayid = holidayid;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Holidaytype getHolidaytype() {
		return this.holidaytype;
	}

	public void setHolidaytype(Holidaytype holidaytype) {
		this.holidaytype = holidaytype;
	}

	public Weekday getWeekday() {
		return this.weekday;
	}

	public void setWeekday(Weekday weekday) {
		this.weekday = weekday;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}