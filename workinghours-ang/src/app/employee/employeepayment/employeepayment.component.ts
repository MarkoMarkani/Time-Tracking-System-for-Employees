import { EmployeeService } from './../../employee.service';
import { Component, OnInit } from '@angular/core';
import { PaymentService } from '../../payment.service';
import { ActivatedRoute } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-employeepayment',
  templateUrl: './employeepayment.component.html',
  styleUrls: ['./employeepayment.component.css']
})
export class EmployeepaymentComponent implements OnInit {
  payments;
  id;
  jobname;
  constructor(private paymentService: PaymentService,private route: ActivatedRoute, private cookieService: CookieService) { 
    this.id= this.route.parent.snapshot.paramMap.get('id');
  }

  ngOnInit() {
    this.payments=this.paymentService.getSinglePayment(this.id);
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
