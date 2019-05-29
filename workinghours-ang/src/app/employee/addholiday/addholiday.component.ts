import { WeekdayService } from './../../weekday.service';
import { Holidaytype } from '../../model/holidaytype.model';
import { Holiday} from '../../model/holiday.model';
import { HolidayService } from './../../holiday.service';
import { Weekday } from './../../model/weekday.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-addholiday',
  templateUrl: './addholiday.component.html',
  styleUrls: ['./addholiday.component.css']
})
export class AddholidayComponent implements OnInit {
  holiday: Holiday=new Holiday();
  submitted = false;
  holidaytypes;
  holidaytype:Holidaytype;
  holidayid;
  date;
  name;
  weekday:Weekday;
  weekdays;
  message;
  constructor(private holidayService: HolidayService,private weekdayService:WeekdayService,private router: Router,private route:ActivatedRoute) { }

  ngOnInit() {
    this.holidaytypes = this.holidayService.getHolidaytype();
    this.weekdays = this.weekdayService.getWeekdays();
  }
  save() {

    console.log(this.holiday.date);
    console.log(this.holiday);

   this.holidayService.createHolidays(this.holiday).subscribe(data => {
    if(data===null){

      this.submitted = true;

      this.router.navigate(['../holidaylist'],  { relativeTo: this.route });    
      }else{

      this.submitted = false;

      this.message = "Holiday exists";

    }

   });
   
  }

  onSubmit() {
    this.submitted = true;
    this.save();
    console.log(this.holiday);
  }
}
