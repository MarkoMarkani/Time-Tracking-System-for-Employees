import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../../employee.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-employeeholiday',
  templateUrl: './employeeholiday.component.html',
  styleUrls: ['./employeeholiday.component.css']
})
export class EmployeeholidayComponent implements OnInit {

  holidays;
  id;

  constructor(private employeeService:EmployeeService, private route:ActivatedRoute) {
    this.id= this.route.parent.snapshot.paramMap.get('id');
    this.holidays=this.employeeService.getHolidays();
   }

  ngOnInit() {
  }

}