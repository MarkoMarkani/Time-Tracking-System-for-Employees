package com.lab.software.engineering.project.workinghours.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;


/**
 * The persistent class for the JOBHISTORY database table.
 * 
 */
@Entity
@Table(name="jobhistory")
public class Jobhistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="JOBHISTORY_JOBHISTORYID_GENERATOR", sequenceName="JOBHISTORY_JOBHISTORYID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="JOBHISTORY_JOBHISTORYID_GENERATOR")
	private long jobhistoryid;

	@Temporal(TemporalType.DATE)
	private Date enddate;

	@Temporal(TemporalType.DATE)
	private Date startdate;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JsonIgnoreProperties("jobhistories")
	@JoinColumn(name="EMPLOYEEID")
	private Employee employee;

	//bi-directional many-to-one association to Job
	@ManyToOne
	@JsonIgnoreProperties("jobhistories")
	@JoinColumn(name="JOBID")
	private Job job;

	public Jobhistory() {
	}

	public long getJobhistoryid() {
		return this.jobhistoryid;
	}

	public void setJobhistoryid(long jobhistoryid) {
		this.jobhistoryid = jobhistoryid;
	}

	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public Date getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public long getEmployeeId() {
		return this.employee.getEmployeeid();
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Job getJob() {
		return this.job;
	}

	public void setJob(Job job) {
		this.job = job;
	}


}