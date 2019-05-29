import { EmployeeService } from './../employee.service';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee } from '../model/employee.model';
import { CookieService } from 'ngx-cookie-service';

@Component({
    selector: 'login',
    templateUrl: './login.component.html'
})

export class LoginComponent implements OnInit {
  employee: Employee = new Employee();
  employee1: Employee = new Employee();
  submitted = false;
  cookieValue = 'UNKNOWN';
  password;
  id;
    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private http: HttpClient,
        private employeeService:EmployeeService,
        private cookieService: CookieService
    ) {  this.id= this.route.parent.snapshot.paramMap.get('id');
    console.log(this.id)}
    ngOnInit() {
      this.cookieService.deleteAll();
      this.cookieService.set( 'username', "");
      this.cookieService.set( 'employeeid', "");
      this.cookieService.set( 'lastname', "");
      this.cookieService.set( 'firstname', "");
      this.cookieService.set( 'jobname', "");
    }
    save() {
      this.employeeService.loginEmployee(this.employee).subscribe(employee => {
        this.employee1 = employee;
        if (!this.employee1){
          alert("Pogresan username ili sifra")
        }
        else{
            this.cookieService.set( 'username', this.employee1.username );
            this.cookieService.set( 'employeeid', this.employee1.employeeid.toString() );
            this.cookieService.set( 'lastname', this.employee1.lastname );
            this.cookieService.set( 'firstname', this.employee1.firstname );
            this.cookieService.set('jobname', this.employee1.job.name)
            this.router.navigate(['/employee/'+ this.employee1.employeeid.toString()+'/info']);
          }
          console.log(this.employee1);
        });
    }
    onSubmit() {
      this.submitted = true;
      this.save();
      this.id=this.cookieService.get("employeeid");
      console.log(this.employee);
    }
   
 
 

    
    }
