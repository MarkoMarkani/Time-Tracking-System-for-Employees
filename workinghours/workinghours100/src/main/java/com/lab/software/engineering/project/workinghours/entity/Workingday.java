package com.lab.software.engineering.project.workinghours.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the WORKINGDAY database table.
 * 
 */
@Entity
@Table(name="workingday")
public class Workingday implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String DATE_PATTERN = "yyyy-MM";
	@Id
	@SequenceGenerator(name="WORKINGDAY_WORKINGDAYID_GENERATOR", sequenceName="WORKINGDAY_WORKINGDAYID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="WORKINGDAY_WORKINGDAYID_GENERATOR")
	private long workingdayid;

	private LocalDateTime checkin;

	private LocalDateTime checkout;
	@Transient
	private double workDuration;
	@Transient
	private double overtime;
	@Transient
	private double overtimeEarnings;
	@Transient
	private double perDayEarnings;
	@Transient
	private double totalEarnings;
	
	//bi-directional many-to-one association to Break
	@OneToMany(mappedBy="workingday")
	@JsonIgnoreProperties("workingday")
	private List<Break> breaks;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JsonIgnoreProperties("workingdays")
	@JoinColumn(name="EMPLOYEEID")
	private Employee employee;

	//bi-directional many-to-one association to Weekday
	@ManyToOne
	@JsonIgnoreProperties("workingdays")
	@JoinColumn(name="WEEKDAYID")
	private Weekday weekday;

	public Workingday() {
	}

	public long getWorkingdayid() {
		return this.workingdayid;
	}

	public void setWorkingdayid(long workingdayid) {
		this.workingdayid = workingdayid;
	}

	public LocalDateTime getCheckin() {
		return this.checkin;
	}

	public void setCheckin(LocalDateTime checkin) {
		this.checkin = checkin;
	}

	public LocalDateTime getCheckout() {
		return this.checkout;
	}

	public void setCheckout(LocalDateTime checkout) {
		this.checkout = checkout;
	}

	public double getTotalEarnings() throws ParseException {
		
		this.totalEarnings = this.getOvertimeEarnings() + this.getPerDayEarnings();
		
		return totalEarnings;
	}
//get earnings per day per employee
	public double getPerDayEarnings() throws ParseException {
		
		LocalDateTime localtDateAndTime = this.getCheckin();
		String tostring = localtDateAndTime.toString();
		String subString = tostring.substring(0, 7);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		int lastDayOfMonth = getLastDayOfMonth(subString);
		String firstDayOfMonth = subString + "-01";
		String lastDayInMonth = subString + "-"+lastDayOfMonth;
		
		Date startDate = sdf.parse(firstDayOfMonth);
		
		Date endDate = sdf.parse(lastDayInMonth);
		
		this.perDayEarnings = this.getEmployee().getSalary().doubleValue()/calculateDuration(startDate, endDate);
		
		return perDayEarnings;
	}

	//get last day in month of given date
	public static int getLastDayOfMonth(String dateString) {
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern(DATE_PATTERN);
		YearMonth yearMonth = YearMonth.parse(dateString, pattern);
		LocalDate date = yearMonth.atEndOfMonth();
		return date.lengthOfMonth();
	}

	//return work days between two given dates
	public static int calculateDuration(Date startDate, Date endDate) {
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDate);

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);

		int workDays = 0;

		if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
			startCal.setTime(endDate);
			endCal.setTime(startDate);
		}

		while (startCal.getTimeInMillis() <= endCal.getTimeInMillis()) {
			if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
					&& startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {

				workDays++;
			}

			startCal.add(Calendar.DAY_OF_MONTH, 1);
		}


		return workDays;
	}
	
	//return earning for overtime 
	public double getOvertimeEarnings() {
		
		double commissionPCT = this.getEmployee().getJob().getCommissionpct().doubleValue();
		
		this.overtimeEarnings = this.getOvertime() * commissionPCT;
		
		return overtimeEarnings;
	}

	public List<Break> getBreaks() {
		return this.breaks;
	}
	
	//get work duration for one working day between check in and check out
	public double getWorkDuration() {
		if(this.checkout != null) {
			Duration duration = Duration.between(this.checkin, this.checkout);
		
			double diff = Math.abs(duration.toMinutes());
			this.workDuration = diff/60;
			return this.workDuration;
		}
		return 0;
	}

	public void setWorkDuration() {
		if(this.checkout != null) {
			Duration duration = Duration.between(this.checkin, this.checkout);
			System.out.println(duration);
			long diff = Math.abs(duration.toMinutes());
			this.workDuration = diff/60;
		}
		
	}
	
	//return overtime in hours per employee's working day
	public double getOvertime() {
		if(this.getWorkDuration() > 8.0) {
			return this.getWorkDuration()-8;
		}else {
			return 0;
		}
		
	}

	public void setBreaks(List<Break> breaks) {
		this.breaks = breaks;
	}

	public Break addBreak(Break br) {
		getBreaks().add(br);
		br.setWorkingday(this);

		return br;
	}

	public Break removeBreak(Break br) {
		getBreaks().remove(br);
		br.setWorkingday(null);

		return br;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Weekday getWeekday() {
		return this.weekday;
	}

	public void setWeekday(Weekday weekday) {
		this.weekday = weekday;
	}

	@Override
	public String toString() {
		return "Workingday [workingdayid=" + workingdayid + ", checkin=" + checkin + ", checkout=" + checkout
				+ ", breaks=" + breaks + ", employee=" + employee + ", weekday=" + weekday + "]";
	}
	

}