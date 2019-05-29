import { Component, OnInit } from '@angular/core';
import { Employee } from '../../model/employee.model';

@Component({
  selector: 'app-employeewelcome',
  templateUrl: './employeewelcome.component.html',
  styleUrls: ['./employeewelcome.component.css']
})
export class EmployeewelcomeComponent implements OnInit {
employee:Employee;
  constructor() { }

  ngOnInit() {
  }

checkIn(){}

checkOut(){}

pauseOn(){}

pauseOff(){}

}
