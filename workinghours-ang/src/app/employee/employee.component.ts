import { Component, OnInit } from '@angular/core';
import { Employee } from '../model/employee.model';
import { EmployeeService } from '../employee.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { longStackSupport } from 'q';
@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {

  employee:Employee;
  id;
  jobname;
  employeeid;

   constructor( private employeeService:EmployeeService,private route: ActivatedRoute,
     private router: Router, private cookieService: CookieService) {
      this.id= this.route.snapshot.paramMap.get('id');
      this.employeeid = this.cookieService.get('employeeid');
      }

  ngOnInit() {
    console.log(this.id);

    // if(!this.cookieService.check('jobname')){
    //   this.jobname = false;
    //   this.router.navigate(['../../login'],  { relativeTo: this.route });
    // }else{
    //   this.jobname = this.cookieService.get('jobname');
    //   this.employeeid = this.cookieService.get('employeeid');
    // }
    
    //  this.router.navigate(['../../login'],  { relativeTo: this.route });
    
    
    if (this.id){

    this.employeeService.getEmployee(this.id).subscribe((employee) => {
      this.employee = employee;
      this.jobname= this.employee.job.name;
    
    });


  }

 
  }

  

  onLogOut(){
    this.cookieService.deleteAll();
    this.jobname = null;
    this.router.navigate(['../../login'],  { relativeTo: this.route });
    
  }
}
