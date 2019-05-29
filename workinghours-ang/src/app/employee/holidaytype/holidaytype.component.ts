import { HolidayService } from './../../holiday.service';
import { Holidaytype } from './../../model/holidaytype.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-holidaytype',
  templateUrl: './holidaytype.component.html',
  styleUrls: ['./holidaytype.component.css']
})
export class HolidaytypeComponent implements OnInit {
  holidaytypes;
  input;
  id;
  holidaytype:Holidaytype;
  holidaytypeid;
  
  constructor(private holidayService:HolidayService, private route:ActivatedRoute,private router: Router) { 
    this.id= this.route.parent.snapshot.paramMap.get('id');
    this.holidaytypes=this.holidayService.getHolidaytype();
    
  }
 
  ngOnInit() {
  }

  deleteHolidaytype(holidatytype){
    this.holidaytype = holidatytype;
    console.log("Delete " + this.holidaytype);
    this.holidayService.deleteHolidaytype(this.holidaytype).subscribe(data => { 
      
      this.router.navigateByUrl("employee/" + this.id + "/stats", { skipLocationChange: true }).then(() =>
    this.router.navigate(["employee/" + this.id + "/holidaytype"]));
    });
    
  }

}
