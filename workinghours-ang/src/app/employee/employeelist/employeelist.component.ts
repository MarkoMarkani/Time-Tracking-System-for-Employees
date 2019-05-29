import { EmployeeService } from './../../employee.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-employeelist',
  templateUrl: './employeelist.component.html',
  styleUrls: ['./employeelist.component.css']
})
export class EmployeelistComponent implements OnInit {
  employees;
  input;
 id;
   constructor(private employeeService:EmployeeService,  private route:ActivatedRoute) {
    this.id= Number(this.route.snapshot.paramMap.get('id')); 
    this.employees=this.employeeService.getEmployeesList();
    }
 
   ngOnInit() {
    
   }
 
   overtimeEmployees(){
    console.log("OvertimeEmployee");
   
    this.employees=this.employeeService.getOvertimeEmployeesList();
  }
  listEmployees(){
    
   
    this.employees=this.employeeService.getEmployeesList();
  }


 }