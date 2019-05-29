import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Holidayperemployee } from './model/holidayperemployee.model';
import { Holiday } from './model/holiday.model';

@Injectable({
  providedIn: 'root'
})
export class HolidayService {

  constructor(private http:HttpClient) { }

  getHolidays():Observable<Holiday[]>{
    return this.http.get<Holiday[]>('http://localhost:8080/api/holidays');
   }
  getHolidaytype() {
    return this.http.get('http://localhost:8080/api/holidaytypes');
  }
  createHolidaytype(holidaytype){
    return this.http.post('http://localhost:8080/api/holidaytype', holidaytype);
   }
  createHolidays(holiday){
    console.log(holiday);
   return this.http.post('http://localhost:8080/api/holiday', holiday);
  }

  getHolidayperemployee(id):Observable<Holidayperemployee[]> {
    return this.http.get<Holidayperemployee[]>(`http://localhost:8080/api/employees/${id}/holidays`);
  }
  deleteHolidaytype(holidaytype){
    
    console.log("Delete holidaytype: " + holidaytype.holidaytypeid);  
    return this.http.delete(`http://localhost:8080/api/holidaytypes/${holidaytype.holidaytypeid}`);
  }
  createHolidayperemployee(holidayperemployee,id){
    return this.http.post(`http://localhost:8080/api/employees/${id}/holiday`,holidayperemployee);
  }
}
