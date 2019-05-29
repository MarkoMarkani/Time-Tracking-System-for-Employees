import { EmployeeService } from './../../employee.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-byemployee',
  templateUrl: './byemployee.component.html',
  styleUrls: ['./byemployee.component.css']
})
export class ByemployeeComponent implements OnInit {
  employee;
  id;
    constructor(private employeeService:EmployeeService,  private route:ActivatedRoute) {
      this.id= Number(this.route.parent.snapshot.paramMap.get('id')); 
     }
  
    ngOnInit() {
      this.employee=this.employeeService.getEmployee(this.id).subscribe(employee=>this.employee=employee);
    }
  
  }
  