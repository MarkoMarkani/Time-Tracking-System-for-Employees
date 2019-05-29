import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { EmployeeService } from '../../employee.service';

@Component({
  selector: 'app-employeestats',
  templateUrl: './employeestats.component.html',
  styleUrls: ['./employeestats.component.css']
})
export class EmployeestatsComponent implements OnInit {
  employee;
  id;
  
    constructor( private employeeService:EmployeeService,private route: ActivatedRoute,
      private router: Router) {   this.id= this.route.parent.snapshot.paramMap.get('id');}
  
      ngOnInit() {
        this.employee = this.employeeService.getEmployee(this.id).subscribe((success) => {
          this.employee = success;
       
          // this.employee=this.employeeService.getEmployee(this.id);
      
      })}
      onEditInfo() {
        this.router.navigate(['../editinfo'], {relativeTo: this.route});
      }
}
