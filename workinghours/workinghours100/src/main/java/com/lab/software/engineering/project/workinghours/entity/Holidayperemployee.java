package com.lab.software.engineering.project.workinghours.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;


/**
 * The persistent class for the HOLIDAYPEREMPLOYEE database table.
 * 
 */
@Entity
@Table(name = "holidayperemployee")
public class Holidayperemployee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="HOLIDAYPEREMPLOYEE_HOLIDAYPEREMPLOYEEID_GENERATOR", sequenceName="HOLIDAYPEREMPLOYEE_HOLIDAYPERE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HOLIDAYPEREMPLOYEE_HOLIDAYPEREMPLOYEEID_GENERATOR")
	private long holidayperemployeeid;

	@Temporal(TemporalType.DATE)
	@Column(name="HOLIDAYDATE")
	private Date date;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JsonIgnoreProperties("holidayperemployees")
	@JoinColumn(name="EMPLOYEEID")
	private Employee employee;
	@JoinColumn(name="NAME")
	private String name;
	//bi-directional many-to-one association to Holidaytype
	@ManyToOne
	@JsonIgnoreProperties("holidayperemployees")
	@JoinColumn(name="HOLIDAYTYPEID")
	private Holidaytype holidaytype;

	//bi-directional many-to-one association to Weekday
	@ManyToOne
	@JsonIgnoreProperties("holidayperemployees")
	@JoinColumn(name="WEEKDAYID")
	private Weekday weekday;

	public Holidayperemployee() {
	}

	public long getHolidayperemployeeid() {
		return this.holidayperemployeeid;
	}

	public void setHolidayperemployeeid(long holidayperemployeeid) {
		this.holidayperemployeeid = holidayperemployeeid;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
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