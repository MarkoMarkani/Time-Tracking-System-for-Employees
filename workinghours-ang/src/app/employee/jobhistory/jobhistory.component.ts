import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../../employee.service';
import { ActivatedRoute } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-jobhistory',
  templateUrl: './jobhistory.component.html',
  styleUrls: ['./jobhistory.component.css']
})
export class JobhistoryComponent implements OnInit {
  jobhistories;
  id;
  jobname;
  constructor(private employeeService: EmployeeService,private route: ActivatedRoute, private cookieService: CookieService) { 
    this.id= this.route.parent.snapshot.paramMap.get('id');
  }

  ngOnInit() {
    this.jobhistories=this.employeeService.getJobHistory(this.id);
    this.jobname= this.cookieService.get('jobname');
    // this.payments = this.paymentService.getSinglePayment(1).subscribe((success) => {
    //   this.payments = success;
    //   console.error('SUCCESSSSS ', success);
    // }, (error) => {
    //   console.error('ERRORRRR ', error);
    // });
    // console.error('EMOLEEEEEE ', this.payments);

  }
}
