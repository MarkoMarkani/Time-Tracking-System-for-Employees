package com.lab.software.engineering.project.workinghours.entity;

import java.io.Serializable;
import javax.persistence.*;




/**
 * The persistent class for the WEEKDAY database table.
 * 
 */
@Entity
@Table(name="weekday")
public class Weekday implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="WEEKDAY_WEEKDAYID_GENERATOR", sequenceName="WEEKDAY_WEEKDAYID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="WEEKDAY_WEEKDAYID_GENERATOR")
	private long weekdayid;

	private String name;


	public Weekday() {
	}

	public long getWeekdayid() {
		return this.weekdayid;
	}

	public void setWeekdayid(long weekdayid) {
		this.weekdayid = weekdayid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Weekday [weekdayid=" + weekdayid + ", name=" + name + "]";
	}

}