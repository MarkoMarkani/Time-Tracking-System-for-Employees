package com.lab.software.engineering.project.workinghours.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;


/**
 * The persistent class for the VACATION database table.
 * 
 */
@Entity
@Table(name="vacation")
public class Vacation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="VACATION_VACATIONID_GENERATOR", sequenceName="VACATION_VACATIONID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="VACATION_VACATIONID_GENERATOR")
	private long vacationid;

	@Temporal(TemporalType.DATE)
	private Date fromdate;

	@Temporal(TemporalType.DATE)
	private Date todate;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JsonIgnoreProperties("vacations")
	@JoinColumn(name="EMPLOYEEID")
	private Employee employee;

	public Vacation() {
	}

	public long getVacationid() {
		return this.vacationid;
	}

	public void setVacationid(long vacationid) {
		this.vacationid = vacationid;
	}

	public Date getFromdate() {
		return this.fromdate;
	}

	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}

	public Date getTodate() {
		return this.todate;
	}

	public void setTodate(Date todate) {
		this.todate = todate;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}