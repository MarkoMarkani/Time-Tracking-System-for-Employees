package com.lab.software.engineering.project.workinghours.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PAYMENT database table.
 * 
 */
@Entity
@Table(name="payment")
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PAYMENT_PAYMENTID_GENERATOR", sequenceName="PAYMENT_PAYMENTID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PAYMENT_PAYMENTID_GENERATOR")
	private long paymentid;

	@Temporal(TemporalType.DATE)
	private Date fromdate;

	private BigDecimal overtime;

	@Temporal(TemporalType.DATE)
	private Date todate;

	private BigDecimal total;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JsonIgnoreProperties("payments")
	@JoinColumn(name="EMPLOYEEID")
	private Employee employee;

	public Payment() {
	}

	public long getPaymentid() {
		return this.paymentid;
	}

	public void setPaymentid(long paymentid) {
		this.paymentid = paymentid;
	}

	public Date getFromdate() {
		return this.fromdate;
	}

	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}

	public BigDecimal getOvertime() {
		return this.overtime;
	}

	public void setOvertime(BigDecimal overtime) {
		this.overtime = overtime;
	}

	public Date getTodate() {
		return this.todate;
	}

	public void setTodate(Date todate) {
		this.todate = todate;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}