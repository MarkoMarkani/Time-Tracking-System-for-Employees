import { HolidayService } from './../../holiday.service';

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-holidaylist',
  templateUrl: './holidaylist.component.html',
  styleUrls: ['./holidaylist.component.css']
})
export class HolidaylistComponent implements OnInit {

  holidays;
  id;

  constructor(private holidayService:HolidayService, private route:ActivatedRoute) {
    this.id= Number(this.route.parent.snapshot.paramMap.get('id')); 
 
    this.holidayService.getHolidays().subscribe(data => { 
      
      data.forEach(function (value) {
            if(value.holidaytype === null){
              value.holidaytype = "No type";
              // this.holidays.push(value);
            }
            console.log(value.holidaytype);
      }); 
      console.log(data);
      this.holidays = data;
   

   });

   }

  ngOnInit() {
  }

}
