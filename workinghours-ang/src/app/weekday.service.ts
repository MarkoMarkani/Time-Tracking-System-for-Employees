import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class WeekdayService {

  constructor(private http:HttpClient) { }
  getWeekdays() {
    return this.http.get('http://localhost:8080/api/weekdays');
  }
}
