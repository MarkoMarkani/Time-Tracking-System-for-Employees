package com.lab.software.engineering.project.workinghours.entity;

import java.io.Serializable;
import javax.persistence.*;




/**
 * The persistent class for the HOLIDAYTYPE database table.
 * 
 */
@Entity
@Table(name="holidaytype")
public class Holidaytype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="HOLIDAYTYPE_HOLIDAYTYPEID_GENERATOR", sequenceName="HOLIDAYTYPE_HOLIDAYTYPEID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HOLIDAYTYPE_HOLIDAYTYPEID_GENERATOR")
	private long holidaytypeid;
	@JoinColumn(name="NAME")
	private String name;


	public Holidaytype() {
	}

	public long getHolidaytypeid() {
		return this.holidaytypeid;
	}

	public void setHolidaytypeid(long holidaytypeid) {
		this.holidaytypeid = holidaytypeid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}