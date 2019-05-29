import { JobService } from './../../job.service';
import { EmployeeService } from './../../employee.service';
import { Employee } from './../../model/employee.model';
import { Job } from './../../model/job.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-updatejob',
  templateUrl: './updatejob.component.html',
  styleUrls: ['./updatejob.component.css']
})
export class UpdatejobComponent implements OnInit {
  job: Job;
  jobs;
  submitted = false;
  name;
  id;

  employee: Employee;
  employees;
jobname;

  constructor(private employeeService: EmployeeService,private jobService:JobService,private route: ActivatedRoute,  private router: Router,private cookieService: CookieService) {
    this.id = this.route.parent.snapshot.paramMap.get('id');

  }

  ngOnInit() {
    this.jobs = this.jobService.getJobsList();
    this.employees = this.employeeService.getEmployeesList();
    this.jobname= this.cookieService.get('jobname');
  }


  save() {
    console.log(this.job.name);
    this.jobService.updateJob(this.job, this.id).subscribe(data => {

      this.router.navigate(['../jobhistory'],  { relativeTo: this.route });

    });
  }

  onSubmit() {
    this.submitted = true;
    this.save();
    console.log(this.job);
  }


}