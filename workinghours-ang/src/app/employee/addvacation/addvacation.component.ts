import { VacationService } from './../../vacation.service';
import { EmployeeService } from './../../employee.service';
import { Vacation } from './../../model/vacation.model';
import { Employee } from './../../model/employee.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-addvacation',
  templateUrl: './addvacation.component.html',
  styleUrls: ['./addvacation.component.css']
})
export class AddvacationComponent implements OnInit {
  vacation: Vacation = new Vacation();
  submitted = false;
  fromdate;
  todate;
  employee: Employee;
  id;
  employees;
  message;
  jobname;
  constructor(private employeeService: EmployeeService,private vacationService:VacationService,private route: ActivatedRoute, private router: Router,private cookieService: CookieService) { 
    this.id = this.route.parent.snapshot.paramMap.get('id');
  }
  

  ngOnInit() {
    this.employees = this.employeeService.getEmployeesList();
    this.jobname= this.cookieService.get('jobname');
  }
  
  save() {


    console.log(this.vacation);

    this.vacationService.createVacation(this.vacation,this.id).subscribe(data => { 
      console.log("Data: " +data);
      if(data===null){
        this.submitted = true;
        this.router.navigate(['../vacation'],  { relativeTo: this.route });
        
      }else{
        this.submitted = false;
        this.message = "Vacation already exists between these dates or it is not valid format";
      }
     });

  }

  onSubmit() {
    this.submitted = true;
    this.save();
    console.log(this.vacation);
  }
}