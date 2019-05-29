import { VacationService } from './../../vacation.service';

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-vacationlist',
  templateUrl: './vacationlist.component.html',
  styleUrls: ['./vacationlist.component.css']
})
export class VacationlistComponent implements OnInit {
  vacations;
  constructor(private vacationService:VacationService,  private route:ActivatedRoute) { 
    this.vacations=this.vacationService.getVacations();
  }

  ngOnInit() {
  }

}
