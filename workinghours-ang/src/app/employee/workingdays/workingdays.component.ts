import { WorkingdayService } from './../../workingday.service';
import { WeekdayService } from './../../weekday.service';
import { EmployeeService } from './../../employee.service';
import { Weekday } from './../../model/weekday.model';
import { Employee } from './../../model/employee.model';
import { Workingday } from './../../model/workingday.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-workingdays',
  templateUrl: './workingdays.component.html',
  styleUrls: ['./workingdays.component.css']
})
export class WorkingdaysComponent implements OnInit {
  workingday: Workingday;
  submitted = false;
  workingdayid;
  workingdays;
  checkin;
  checkout;
  employees;
  employee: Employee;
  weekday: Weekday;
  id;
  weekdays;
  breaks;
  workduration;
  jobname;

  constructor(private employeeService: EmployeeService,private weekdayService: WeekdayService,private workingdayService: WorkingdayService,private route: ActivatedRoute,private cookieService: CookieService) { }

  ngOnInit() {
    this.id = this.route.parent.snapshot.paramMap.get('id');


    this.weekdays = this.weekdayService.getWeekdays();
    this.employees = this.employeeService.getEmployeesList();
    this.workingdays = this.workingdayService.getWorkingdays(this.id);
    this.jobname= this.cookieService.get('jobname');
  }

}