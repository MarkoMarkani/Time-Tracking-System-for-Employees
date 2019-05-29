package com.lab.software.engineering.project.workinghours.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the "BREAK" database table.
 * 
 */
@Entity
@Table(name="break")
public class Break implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="'BREAK'_BREAKID_GENERATOR", sequenceName="BREAK_BREAKID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="'BREAK'_BREAKID_GENERATOR")
	private long breakid;


	private LocalDateTime breakended;


	private LocalDateTime breakstarted;

	//bi-directional many-to-one association to Workingday
	@ManyToOne
	@JsonIgnoreProperties("breaks")
	@JoinColumn(name="WORKINGDAYID")
	private Workingday workingday;

	public Break() {
	}

	public long getBreakid() {
		return this.breakid;
	}

	public void setBreakid(long breakid) {
		this.breakid = breakid;
	}

	public LocalDateTime getBreakended() {
		return this.breakended;
	}

	public void setBreakended(LocalDateTime breakended) {
		this.breakended = breakended;
	}

	public LocalDateTime getBreakstarted() {
		return this.breakstarted;
	}

	public void setBreakstarted(LocalDateTime breakstarted) {
		this.breakstarted = breakstarted;
	}

	public Workingday getWorkingday() {
		return this.workingday;
	}

	public void setWorkingday(Workingday workingday) {
		this.workingday = workingday;
	}

	@Override
	public String toString() {
		return "Break [breakid=" + breakid + ", breakended=" + breakended + ", breakstarted=" + breakstarted
				+ ", workingday=" + workingday + "]";
	}
	
	

}