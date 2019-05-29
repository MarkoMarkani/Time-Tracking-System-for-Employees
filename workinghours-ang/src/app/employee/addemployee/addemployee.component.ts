import { Job } from './../../model/job.model';
import { Employee } from './../../model/employee.model';
import { EmployeeService } from './../../employee.service';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-addemployee',
  templateUrl: './addemployee.component.html',
  styleUrls: ['./addemployee.component.css']
})
export class AddemployeeComponent implements OnInit {

  employee: Employee = new Employee();
  submitted = false;
  jobs;
  job: Job;
  name;
  firstname;
  employeeid

  active
  username
  hiredate
  password
  email
  lastname
  salary
  message;

  vacations?
  workingdays?

  constructor(private employeeService: EmployeeService, private router:Router,private route: ActivatedRoute) {
    
  }

  ngOnInit() {
    this.jobs = this.employeeService.getJobsList();
  }

changeroute(){
 
}
  save() {

    console.log(this.employee.hiredate);

    this.employeeService.createEmployee(this.employee).subscribe(data => { 

      //if employee exists In Spring

      console.log("Data: " +data);
      if(data===null){
        this.submitted = true;
        this.router.navigate(['../employeelist'],  { relativeTo: this.route });
        
      }else{
        this.submitted = false;
        this.message = "User with that username or email exists";
      }
      

     });
  }



  onSubmit() {
    
    this.save();
    console.log(this.employee);
  }


}