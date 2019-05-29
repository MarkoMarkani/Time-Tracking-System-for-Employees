import { ActivatedRoute, Params, Router } from '@angular/router';
import { EmployeeService } from './../../employee.service';
import { Employee } from '../../model/employee.model';
import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { CookieService } from 'ngx-cookie-service';


@Component({
  selector: 'app-editinfo',
  templateUrl: './editinfo.component.html',
  styleUrls: ['./editinfo.component.css']
})
export class EditinfoComponent implements OnInit {
  employee;
  submitted = false;
  id;
  jobname;
  constructor(private employeeService: EmployeeService, private location: Location, private route: ActivatedRoute,private cookieService: CookieService,private router:Router) {
    this.id= this.route.parent.snapshot.paramMap.get('id');
    console.log(this.id);
  }

  ngOnInit() {
 
    this.employee = this.employeeService.getEmployee(this.id).subscribe((success) => {
      this.employee = success;
      this.jobname= this.cookieService.get('jobname');
  })};

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.employeeService.updateEmployee(this.employee,this.id)
    .subscribe(() => {
      this.cookieService.set('firstname',this.employee.firstname);
      this.cookieService.set('lastname',this.employee.lastname);
      
      this.cookieService.set( 'username', this.employee.username );
          this.cookieService.set( 'employeeid', this.employee.employeeid.toString() );
         
          this.cookieService.set('jobname', this.employee.job.name)
          this.router.navigateByUrl("employee/" + this.id + "/stats", { skipLocationChange: true }).then(() =>
    this.router.navigate(["employee/" + this.id + "/info"]));
    }
    );
    
  }


  onSubmit() {
    this.submitted = true;
    this.save();
  }
}
