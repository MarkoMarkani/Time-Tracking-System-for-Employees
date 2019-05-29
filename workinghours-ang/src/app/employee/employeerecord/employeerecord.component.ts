import { EmployeeService } from './../../employee.service';
import { Employee } from './../../model/employee.model';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Workingday } from '../../model/workingday.model';
import { WorkingdayService } from '../../workingday.service';
import { Break } from '../../model/break.model';


@Component({
  selector: 'app-employeerecord',
  templateUrl: './employeerecord.component.html',
  styleUrls: ['./employeerecord.component.css']
})
export class EmployeerecordComponent implements OnInit {
  employee:Employee;
  workingday:Workingday=new Workingday();
  workingday2:Workingday=new Workingday();
  workingdays;
  id;
message;
break:Break = new Break();
breaks;
  constructor(private workingdayService:WorkingdayService,private router: Router, private route: ActivatedRoute,private employeeService:EmployeeService){
    this.id= this.route.parent.snapshot.paramMap.get('id');
    console.log(this.id);
  }

  ngOnInit() {
     this.workingdays=this.workingdayService.getWorkingdaysByEmployee(this.id);
     this.breaks=this.employeeService.getBreaksByEmployeeID(this.id);
      
  }

pauseOn(){
  this.break.breakstarted=new Date();
  console.log(this.id)
    if(this.break.breakstarted!=null){
      console.log("Dodavanje break");
      this.employeeService.createBreak(this.break,this.id).subscribe(data=>{
        if(data===null){
          
         
          this.message = "Break message";
        }else{
          this.router.navigateByUrl("employee/" + this.id + "/stats", { skipLocationChange: true }).then(() =>
    this.router.navigate(["employee/" + this.id + "/record"]));
          
        }
      });
    }else{
      console.log("Nesto nije dobro");
    }
    
    console.log(this.break.breakstarted);
}
pauseOff(){
  this.break.breakended=new Date();
  if(this.break.breakended!=null){
    console.log("Ended break");
    this.employeeService.updateBreak(this.break,this.id).subscribe(data=>{
      if(data===null){
        
       
        this.message = "Break message 2";
      }else{
        this.router.navigateByUrl("employee/" + this.id + "/stats", { skipLocationChange: true }).then(() =>
  this.router.navigate(["employee/" + this.id + "/record"]));
        
      }
    });
  }else{
    console.log("Nesto nije dobro");
  }
}
  checkIn(){
    this.workingday.checkin=new Date();
   
    console.log(this.id)
    if(this.workingday.checkin!=null){
      console.log("Dodavanje workingday");
      this.employeeService.createWorkingDay(this.workingday,this.id).subscribe(data=>{
        if(data===null){
          
         
          this.message = "Workingday for today exists";
        }else{
          this.router.navigateByUrl("employee/" + this.id + "/stats", { skipLocationChange: true }).then(() =>
    this.router.navigate(["employee/" + this.id + "/record"]));
          
        }
      });
    }else{
      console.log("Nesto nije dobro");
    }
    
    console.log(this.workingday.checkin);
  }
  checkOut(){
    this.workingday2.checkout=new Date();
    if(this.workingday2.checkout!=null){
      console.log("Dodavanje workingday");
    console.log(this.id);
    this.employeeService.updateWorkingDay(this.workingday2,this.id).subscribe(data=>{
      if(data===null){
        
       
        this.message = "Checkout for today exists or you didn't check in first";
      }else{
        this.router.navigateByUrl("employee/" + this.id + "/stats", { skipLocationChange: true }).then(() =>
    this.router.navigate(["employee/" + this.id + "/record"]));
      }
    });
    console.log(this.workingday2.checkout);
  }else{
    console.log("Nesto nije dobroout");
  }
}
}