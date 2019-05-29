import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { EmployeeService } from '../../employee.service';
import { Employee } from '../../model/employee.model';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-employeedetails',
  templateUrl: './employeedetails.component.html',
  styleUrls: ['./employeedetails.component.css']
})
export class EmployeedetailsComponent implements OnInit {
  employee: Employee;
  id;
  jobname;
  constructor(private employeeService: EmployeeService, private route: ActivatedRoute,
    private router: Router, private cookieService: CookieService) {
    this.id = this.route.parent.snapshot.paramMap.get('id');
    console.log(this.id);
  }

  ngOnInit() {
    this.employeeService.getEmployee(this.id).subscribe((employee) => {
      this.employee = employee;
      this.jobname = this.cookieService.get('jobname');

    });


  }
  onEditInfo() {
    this.router.navigate(['../editinfo'], { relativeTo: this.route });
  }


}
