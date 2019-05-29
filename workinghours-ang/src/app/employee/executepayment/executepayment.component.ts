import { Component, OnInit } from '@angular/core';

import { ActivatedRoute, Router } from '@angular/router';
import { Payment } from '../../model/payment.model';
import { Employee } from '../../model/employee.model';
import { EmployeeService } from '../../employee.service';
import { Executepayment } from '../../model/executepayment.model';
import { PaymentService } from '../../payment.service';
import { CookieService } from 'ngx-cookie-service';


@Component({
  selector: 'app-executepayment',
  templateUrl: './executepayment.component.html',
  styleUrls: ['./executepayment.component.css']
})
export class ExecutepaymentComponent implements OnInit {

  executepayment:Executepayment;
  submitted = false;
  month;
  year:string;
  id;
  employee: Employee;
  employees;
  message;
  jobname;
  constructor(private paymentService: PaymentService, private router:Router, private route: ActivatedRoute,private cookieService: CookieService) {
    this.id= this.route.parent.snapshot.paramMap.get('id');
   
   }

   

  ngOnInit() {
    this.executepayment = new Executepayment();
    this.jobname= this.cookieService.get('jobname');
  }

  save() {

    console.log(this.executepayment);
    console.log("ID " + this.id);
    this.paymentService.savePayment(this.executepayment, this.id).subscribe(data => { 
      console.log("Data: " +data);
      if(data===null){
        this.submitted = true;
        this.router.navigate(['../payment'],  { relativeTo: this.route });
        
      }else{
        this.submitted = false;
        this.message = "Not valid date or payment exists";
      }
    });

  }
  onSubmit() {
    this.submitted = true;
   
    this.fullNameOfMonth();

    console.log(this.year);
    console.log(this.month);
    this.executepayment.month = this.month;
    this.executepayment.year = this.year;
    console.log(this.executepayment.month);
    console.log(this.executepayment.year);
    
    this.save();
    
  }

  fullNameOfMonth(){
    console.log("Full " + this.month);
    switch(this.month) { 
      case 1:  
        this.month = "January";
         break; 
      
      case 2:  
        this.month = "February";
        break; 
      
      case 3:  
        this.month = "March";
      break; 
       
      case 4:  
        this.month = "April";
      break; 
       
      case 5:  
        this.month = "May";   
        break; 
       
      case 6:  
        this.month = "June";   
        break; 
       
      case 7:  
        
        this.month = "July";    
       
        break; 
      
      case 8: 
        this.month = "August";   
        break; 
       
      case 9:  
        this.month = "September";   
        break; 
       
      case 10:  
        this.month = "October";   
        break; 
      
      case 11:  
        this.month = "November";   
        break; 
      
      case 12:  
        this.month = "December";   
        break; 

    } 
   }
}
