package com.lab.software.engineering.project.workinghours.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab.software.engineering.project.workinghours.dao.EmployeeRepository;
import com.lab.software.engineering.project.workinghours.dao.PaymentRepository;
import com.lab.software.engineering.project.workinghours.entity.Employee;
import com.lab.software.engineering.project.workinghours.entity.Payment;
import com.lab.software.engineering.project.workinghours.entity.Workingday;

@Service
public class PaymentServiceImpl implements PaymentService {
	private static final String DATE_PATTERN = "yyyy-MM";
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Payment> getPayment() {

		return paymentRepository.findAll();
	}

	@Override
	public List<Payment> getPaymentByEmployeeId(long employeeid) {
		Optional<Employee> emp = employeeRepository.findById(employeeid);
		return paymentRepository.getPaymentByEmployeeId(emp);
	}

	@Override
	public Payment savePayment(Object payment, long employeeid) throws ParseException {

		//find employee by employee ID
		System.out.println("Employee ID: " + employeeid);
		Optional<Employee> result = employeeRepository.findById(employeeid);
		Employee theEmployee = null;
		if (result.isPresent()) {

			theEmployee = result.get();

		} else {
			throw new RuntimeException("Did not find employee id: " + employeeid);
		}
		
		//Creating Month and Year in format YYYY-MM --- example 2019-03
		System.out.println(payment);
		String ch = payment.toString();

		String[] datum = ch.split(",");
		String substringMonth = datum[0].substring(0, datum[0].length());
		String substringYear = datum[1].substring(0, datum[1].length() - 1).trim();

		String[] getMonth = substringMonth.split("=");
		String[] getYear = substringYear.split("=");

		String month = getMonth[1];
		String year = getYear[1];


		System.out.println("|" + month + "|");
		System.out.println("|" + year + "|");
		System.out.println("|" + getDate(month, year) + "|");

		String date = getDate(month, year);
		
		//checking if the date is valid for payment, it cannot execute payment if month is not finished
		if (!checkIfDateIsValidForPayment(date)) {
			Payment notValid = new Payment();
			notValid.setTotal(BigDecimal.valueOf(100));
			return notValid;
		}

		//parsing date to get first date of the month and the last date of the month
		String startDate = date + "-01 00:00:00";
		String endDate = date + "-" + getLastDayOfMonth(date) + " 23:59:59";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate1 = sdf.parse(date + "-01");

		Date endDate1 = sdf.parse(date + "-" + getLastDayOfMonth(date));

		Optional<Payment> result1 = paymentRepository.findByDate(theEmployee, startDate1);
		Payment thePayment = null;
		if (result1.isPresent()) {

			thePayment = result1.get();

		}

		//if there is no payment, we can execute
		if (thePayment == null) {
			System.out.println("|" + startDate + "|");
			System.out.println("|" + endDate + "|");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			LocalDateTime startDateFromString = LocalDateTime.parse(startDate, formatter);
			LocalDateTime endDateFromString = LocalDateTime.parse(endDate, formatter);

			System.out.println("|" + startDateFromString + "|");
			System.out.println("|" + endDateFromString + "|");
			
			//get all working days for employee
			List<Workingday> wds = paymentRepository.getAllWorkingDaysBetweenTwoDates(theEmployee, startDateFromString,
					endDateFromString);
			//get working days between two dates - example - March has 21 working days
			int workingDaysInMonth = Workingday.calculateDuration(startDate1, endDate1);
			Payment newPayment = new Payment();
			newPayment.setEmployee(theEmployee);
			newPayment.setFromdate(startDate1);
			newPayment.setTodate(endDate1);

			double overtime = 0.0;
			double total = 0.0;
			if (!wds.isEmpty()) {
				double earningsPerDay = wds.get(0).getPerDayEarnings();
				for (Workingday wd : wds) {
					System.out.println("ID: " + wd.getWorkingdayid() + " Checkin: " + wd.getCheckin() + " Checkout: "
							+ wd.getCheckout() + " EmpID: " + wd.getEmployee().getEmployeeid() + " Weekday: "
							+ wd.getWeekday().getName() + " Overtime: " + wd.getOvertime() + " OvertimeEarnings: "
							+ wd.getOvertimeEarnings() + " PerDayEarnings: " + wd.getPerDayEarnings()
							+ " TotalEarnings: " + wd.getTotalEarnings());
					overtime += wd.getOvertime();
					total += wd.getTotalEarnings();
					workingDaysInMonth--;

				}
				System.out.println("workingDaysInMonth = " + workingDaysInMonth + "Left earnings: "
						+ workingDaysInMonth * earningsPerDay);
				total += workingDaysInMonth * earningsPerDay;
				System.out.println("Total Earnings: " + total);

				newPayment.setTotal(BigDecimal.valueOf(total));
			} else {
				newPayment.setTotal(theEmployee.getSalary());
			}

			newPayment.setOvertime(BigDecimal.valueOf(overtime));

			System.out.println("EmpID: " + newPayment.getEmployee().getEmployeeid() + " FromDate: "
					+ newPayment.getFromdate() + " ToDate: " + newPayment.getTodate() + " Overtime: "
					+ newPayment.getOvertime() + " Total: " + newPayment.getTotal());
			paymentRepository.save(newPayment);

		} else {
			System.out.println("Payment exists!");
			return thePayment;

		}
		return thePayment;

	}

	private boolean checkIfDateIsValidForPayment(String date) {
		
		String[] sub = date.split("-");
		String month = sub[1];
		String year = sub[0];
		System.out.println("Payment Month: " + month + " Year: " + year);

		LocalDateTime currentTime = LocalDateTime.now();
		String tostring = currentTime.toString();
		String subString = tostring.substring(0, 7);
		System.out.println("Current DateTime: " + subString);
		String[] sub2 = subString.split("-");
		String month2 = sub2[1];
		String year2 = sub2[0];
		System.out.println("Current Month: " + month + "Year: " + year);
		
		if(Integer.parseInt(year) == Integer.parseInt(year2)){
			System.out.println("It is: " + year + "<=" + year2);
			
			if(Integer.parseInt(month) < Integer.parseInt(month2)){
				System.out.println("It is Month: " + month + "<" + month2);
				return true;
				
			}else {
				System.out.println("It is not Month: " + month + "<" + month2);
				return false;
			}
			
		}else if(Integer.parseInt(year) < Integer.parseInt(year2)){
			System.out.println("It is: " + year + "<" + year2);
			return true;
		}else {
			System.out.println("It is not: Year: " + year + "<=" + year2);
			return false;
		}
		

	}

	public String getDate(String month, String year) {

		String date = year + "-";

		switch (month) {
		case "January":
			date = date + "01";
			break;
		case "February":
			date = date + "02";
			break;
		case "March":
			date = date + "03";
			break;
		case "April":
			date = date + "04";
			break;
		case "May":
			date = date + "05";
			break;
		case "June":
			date = date + "06";
			break;
		case "July":
			date = date + "07";
			break;
		case "August":
			date = date + "08";
			break;
		case "September":
			date = date + "09";
			break;
		case "October":
			date = date + "10";
			break;
		case "November":
			date = date + "11";
			break;
		case "December":
			date = date + "12";
			break;

		}

		return date;
	}

	public static int getLastDayOfMonth(String dateString) {
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern(DATE_PATTERN);
		YearMonth yearMonth = YearMonth.parse(dateString, pattern);
		LocalDate date = yearMonth.atEndOfMonth();
		return date.lengthOfMonth();
	}

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
}
