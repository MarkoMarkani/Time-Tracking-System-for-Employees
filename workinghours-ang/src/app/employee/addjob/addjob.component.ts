import { JobService } from './../../job.service';
import { Job } from './../../model/job.model';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-addjob',
  templateUrl: './addjob.component.html',
  styleUrls: ['./addjob.component.css']
})
export class AddjobComponent implements OnInit {

 
  job: Job=new Job;
  submitted = false;
  jobid: number;
  commissionpct: number;
  name: string;
  employees?;
  jobhistories?;

  constructor(private jobService: JobService, private router: Router, private route:ActivatedRoute) { }

  ngOnInit() {


  }

  save() {
    console.log(this.job);
    this.jobService.createJob(this.job).subscribe(data => { this.router.navigate(['../joblist'], {relativeTo: this.route}); });

  }

  onSubmit() {
    this.submitted = true;
    this.save();
    
    console.log(this.job);
  }

 
}
