import { Holidayperemployee } from './../../model/holidayperemployee.model';
import { Holidaytype } from './../../model/holidaytype.model';
import { Weekday } from './../../model/weekday.model';
import { WeekdayService } from './../../weekday.service';
import { EmployeeService } from './../../employee.service';
import { HolidayService } from './../../holiday.service';
import { Employee } from './../../model/employee.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-holidayperemployee',
  templateUrl: './holidayperemployee.component.html',
  styleUrls: ['./holidayperemployee.component.css']
})
export class HolidayperemployeeComponent implements OnInit {
  holidayperemployee:Holidayperemployee = new Holidayperemployee();
  holidayperemployees;
  submitted = false;
  holidaytype: Holidaytype;
  weekday: Weekday;
  date;
  name;
  holidayperemployeeid;
  holidaytypes;
  weekdays;
  baseValue;
  employees;
  employee: Employee;
  id;
  jobname;
  constructor(private holidayService: HolidayService,private weekdayService:WeekdayService,private employeeService:EmployeeService,private route:ActivatedRoute, private cookieService: CookieService) { 
    this.id= this.route.parent.snapshot.paramMap.get('id');

    this.holidayService.getHolidayperemployee(this.id).subscribe(data => { 
      
        data.forEach(function (value) {
              if(value.holidaytype === null){
                value.holidaytype = "No type";
                // this.holidays.push(value);
              }
              console.log(value.holidaytype);
        }); 
        console.log(data);
        this.holidayperemployees = data;
     

     });

  }

  ngOnInit() {
    if (this.id){
      this.employeeService.getEmployee(this.id).subscribe((employee) => {
        this.employee = employee;
        this.jobname= this.cookieService.get('jobname');
      
      });
    }
    
    this.holidaytypes = this.holidayService.getHolidaytype();
    this.weekdays = this.weekdayService.getWeekdays();
    this.employees = this.employeeService.getEmployeesList();
    // this.holidayperemployees=
    
    
  }


}
