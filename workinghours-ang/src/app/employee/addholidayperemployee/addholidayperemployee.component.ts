import { Holidayperemployee } from '../../model/holidayperemployee.model';
import { EmployeeService } from './../../employee.service';
import { HolidayService } from './../../holiday.service';
import { Employee } from './../../model/employee.model';
import { Holidaytype } from '../../model/holidaytype.model';
import { Weekday } from './../../model/weekday.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-addholidayperemployee',
  templateUrl: './addholidayperemployee.component.html',
  styleUrls: ['./addholidayperemployee.component.css']
})
export class AddholidayperemployeeComponent implements OnInit {

  holidayperemployee: Holidayperemployee = new Holidayperemployee();
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
  message;
  
   constructor( private holidayService:HolidayService,private route: ActivatedRoute, private employeeService:EmployeeService,
     private router: Router,private cookieService: CookieService) {
      this.id= this.route.parent.snapshot.paramMap.get('id');
      
      }

  ngOnInit() {
    this.holidaytypes = this.holidayService.getHolidaytype();
    this.jobname= this.cookieService.get('jobname');
  }

  save() {

    console.log(this.holidayperemployee);

    this.holidayService.createHolidayperemployee(this.holidayperemployee, this.id).subscribe(data => { 
      if(data===null){
        this.submitted = true;
        this.router.navigate(['../holidayperemployee'],  { relativeTo: this.route });
        
      }else{
        this.submitted = false;
        this.message = "Holiday already exists for that date";
      }

     });

  }
  onSubmit() {
    this.submitted = true;
    this.save();
    console.log(this.holidayperemployee);
  }
}
