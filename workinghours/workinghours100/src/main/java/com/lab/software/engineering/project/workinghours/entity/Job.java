package com.lab.software.engineering.project.workinghours.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the JOB database table.
 * 
 */
@Entity
@Table(name="job")
public class Job implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="JOB_JOBID_GENERATOR", sequenceName="JOB_JOBID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="JOB_JOBID_GENERATOR")
	private long jobid;

	private BigDecimal commissionpct;

	private String name;

	//bi-directional many-to-one association to Employee
	@OneToMany(mappedBy="job")
	@JsonIgnoreProperties("job")
	private List<Employee> employees;

	//bi-directional many-to-one association to Jobhistory
	@OneToMany(mappedBy="job")
	@JsonIgnoreProperties("job")
	private List<Jobhistory> jobhistories;

	public Job() {
	}

	public long getJobid() {
		return this.jobid;
	}

	public void setJobid(long jobid) {
		this.jobid = jobid;
	}

	public BigDecimal getCommissionpct() {
		return this.commissionpct;
	}

	public void setCommissionpct(BigDecimal commissionpct) {
		this.commissionpct = commissionpct;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}


	

}