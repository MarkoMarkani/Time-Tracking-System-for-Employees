import { HolidayService } from './../../holiday.service';
import { EmployeeService } from './../../employee.service';
import { Holidaytype } from '../../model/holidaytype.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-addholidaytype',
  templateUrl: './addholidaytype.component.html',
  styleUrls: ['./addholidaytype.component.css']
})
export class AddholidaytypeComponent implements OnInit {

  holidaytype: Holidaytype=new Holidaytype();

  holidaytypeid;
  name;
  submitted = false;

  constructor(private employeeService: EmployeeService, private router: Router, private route:ActivatedRoute) { }


  ngOnInit() {
  }

  save() {
    
    console.log(this.holidaytype);
  
    this.employeeService.createHolidaytype(this.holidaytype).subscribe(data=>this.router.navigate(['../holidaytype'], {relativeTo: this.route}));
  
  }
 
  onSubmit() {
    this.submitted = true;
    this.save();

    console.log(this.holidaytype);
  }
}
