import { JobService } from './../../job.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-joblist',
  templateUrl: './joblist.component.html',
  styleUrls: ['./joblist.component.css']
})
export class JoblistComponent implements OnInit {
  jobs;
  constructor(private jobService:JobService) { }

  ngOnInit() {
    this.jobs=this.jobService.getJobsList();
  }

}
