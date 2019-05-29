import { EmployeeService } from './../../employee.service';
import { Employee } from './../../model/employee.model';
import { Component, OnInit } from '@angular/core';
import { VacationService } from '../../vacation.service';
import { ActivatedRoute } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';



@Component({
  selector: 'app-employeevacation',
  templateUrl: './employeevacation.component.html',
  styleUrls: ['./employeevacation.component.css']
})
export class EmployeevacationComponent implements OnInit {
  vacations;
  id;
  employee:Employee;
  jobname;
  constructor(private vacationService:VacationService,  private route:ActivatedRoute,private employeeService:EmployeeService,  private cookieService: CookieService) { 
    this.id= this.route.parent.snapshot.paramMap.get('id');
    this.vacations=this.vacationService.getVacationList(this.id);
  }

  ngOnInit() {
    console.log(this.id);
    if (this.id){
    this.employeeService.getEmployee(this.id).subscribe((employee) => {
      this.employee = employee;
      this.jobname= this.cookieService.get('jobname');
    
    });
  }
}

}
