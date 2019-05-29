import { PaymentService } from './../../payment.service';
import { Component, OnInit } from '@angular/core';

import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-paymentlist',
  templateUrl: './paymentlist.component.html',
  styleUrls: ['./paymentlist.component.css']
})
export class PaymentlistComponent implements OnInit {
  payments;

  constructor(private paymentService:PaymentService,  private route:ActivatedRoute) { 
    this.payments=this.paymentService.getPaymentList();
  }

  ngOnInit() {
  }

}
